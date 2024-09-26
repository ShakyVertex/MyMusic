package com.ixuea.courses.mymusic.component.splash.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.activity.BaseLogicActivity;
import com.ixuea.courses.mymusic.component.splash.fragment.TermServiceDialogFragment;
import com.ixuea.courses.mymusic.util.DefaultPreferenceUtil;
import com.ixuea.courses.mymusic.util.SuperDarkUtil;
import com.ixuea.courses.mymusic.util.SuperDateUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

/**
 * 启动界面
 */
public class SplashActivity extends BaseLogicActivity {

    private static final String TAG = "SplashActivity";
    private TextView copyrightView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

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

        copyrightView = findViewById(R.id.copyright);
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        // 设置版本年份
        int year = SuperDateUtil.currentYear();
        copyrightView.setText(getResources().getString(R.string.copyright, year));

        if (DefaultPreferenceUtil.getInstance(getHostActivity()).isAcceptTermsServiceAgreement()) {
            // 已经同意了用户协议
            prepareNext();
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
                prepareNext();
            }
        });
    }

    private void prepareNext() {
        Log.d(TAG, "prepareNext");
    }
}