package com.ixuea.courses.mymusic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ixuea.courses.mymusic.activity.BaseCommonActivity;

/**
 * 所有DialogFragment对话框父类
 */
public abstract class BaseDialogFragment extends DialogFragment {

    protected void initViews() {
        // 找控件
    }

    protected void initDatum() {
        // 设置数据
    }

    protected void initListeners() {
        // 设置监听器
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 获取view
        View view = getLayoutView(inflater, container, savedInstanceState);
        // 返回view
        return view;
    }

    /**
     * 获取view
     */
    protected abstract View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * View 创建了
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initDatum();
        initListeners();
    }

    public <T extends View> T findViewById(@IdRes int id) {
        return getView().findViewById(id);
    }

    /**
     * 获取界面方法
     */
    protected BaseCommonActivity getHostActivity() {
        return (BaseCommonActivity) getActivity();
    }
}
