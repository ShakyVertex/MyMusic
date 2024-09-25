package com.ixuea.courses.mymusic.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 所有Activity父类
 */
public class BaseActivity extends AppCompatActivity {

    protected void initViews() {
        // 找控件
    }

    protected void initDatum() {
        // 设置数据
    }

    protected void initListeners() {
        // 设置监听器
    }

    /**
     * 在onCreate方法后面调用
     * @param savedInstanceState
     */
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initViews();
        initDatum();
        initListeners();
    }
}
