package com.ixuea.courses.mymusic.component.splash.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.activity.BaseLogicActivity;
import com.ixuea.courses.mymusic.activity.BaseViewModelActivity;
import com.ixuea.courses.mymusic.component.guide.activity.GuideActivity;
import com.ixuea.courses.mymusic.component.splash.fragment.TermServiceDialogFragment;
import com.ixuea.courses.mymusic.databinding.ActivitySplashBinding;
import com.ixuea.courses.mymusic.util.DefaultPreferenceUtil;
import com.ixuea.courses.mymusic.util.SuperDarkUtil;
import com.ixuea.courses.mymusic.util.SuperDateUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * 启动界面
 */
@RuntimePermissions
public class SplashActivity extends BaseViewModelActivity<ActivitySplashBinding> {

    private static final String TAG = "SplashActivity";

    @Override
    protected void initViews() {
        super.initViews();
        // 设置沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);

        // 状态栏文字颜色
        if (SuperDarkUtil.isDark(this)) {
            QMUIStatusBarHelper.setStatusBarDarkMode(this);
        } else {
            QMUIStatusBarHelper.setStatusBarLightMode(this);
        }
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        // 设置版本年份
        int year = SuperDateUtil.currentYear();
        binding.copyright.setText(getResources().getString(R.string.copyright, year));

        if (DefaultPreferenceUtil.getInstance(getHostActivity()).isAcceptTermsServiceAgreement()) {
            // 已经同意了用户协议
            checkPermission();
        } else {
            showTermsServiceAgreementDialog();
        }
    }

    /**
     * 显示同意服务条款对话框
     */
    private void showTermsServiceAgreementDialog() {
        TermServiceDialogFragment.show(getSupportFragmentManager(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DefaultPreferenceUtil.getInstance(getHostActivity()).setAcceptTermsServiceAgreement();
                checkPermission();
            }
        });
    }

    private void prepareNext() {
        Log.d(TAG, "prepareNext");
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
        // 在启动引导界面之后，无法再次回到Splash界面
        finish();
    }

    /**
     * 检查是否有需要的权限
     * <p>
     * <p>
     * 只有部分权限才需要动态授权
     * 例如：网络权限就不需要动态授权，但相机就需要动态授权
     * <p>
     * <p>
     * Google推荐在用到权限的时候才请求用户
     * 但真实项目中，如果在每个用到的位置请求权限可能比较麻烦
     * 例如：项目中有多个位置都用到了相机
     * <p>
     * <p>
     * 所以说大部分项目，像淘宝，京东等软件
     * 是在启动页请求项目所有需要的权限
     * <p>
     * <p>
     * 但如果大家的项目中有足够的时间
     * 肯定还是推荐在用到的时候再请求权限
     */
    private void checkPermission() {
        //让动态框架检查是否授权了

        //如果不使用框架就使用系统提供的API检查
        //它内部也是使用系统API检查
        //只是使用框架就更简单了
        SplashActivityPermissionsDispatcher.onPermissionGrantedWithPermissionCheck(this);
    }

    /**
     * 权限授权了就会调用该方法
     * 请求相机权限目的是扫描二维码，拍照
     */
    @NeedsPermission({
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    })
    void onPermissionGranted() {
        //如果有权限就进入下一步
        prepareNext();
    }

    /**
     * 显示权限授权对话框
     * 目的是提示用户
     */
    @OnShowRationale({
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    })
    void showRequestPermission(PermissionRequest request) {
        new AlertDialog.Builder(getHostActivity())
                .setMessage(R.string.permission_hint)
                .setPositiveButton(R.string.allow, (dialog, which) -> request.proceed())
                .setNegativeButton(R.string.deny, (dialog, which) -> request.cancel()).show();
    }

    /**
     * 拒绝了权限调用
     */
    @OnPermissionDenied({
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    })
    void showDenied() {
        //退出应用
        finish();
    }

    /**
     * 再次获取权限的提示
     */
    @OnNeverAskAgain({
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    })
    void showNeverAsk() {
        //继续请求权限
        checkPermission();
    }


    /**
     * 授权后回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //将授权结果传递到框架
        SplashActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}