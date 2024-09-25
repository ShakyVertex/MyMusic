package com.ixuea.courses.mymusic.component.splash.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.util.SuperDateUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 设置沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);

        // 状态栏文字颜色
        QMUIStatusBarHelper.setStatusBarLightMode(this);

        // 设置版本年份
        int year = SuperDateUtil.currentYear();
        TextView copyrightView = findViewById(R.id.copyright);
        copyrightView.setText(getResources().getString(R.string.copyright, year));
    }
}