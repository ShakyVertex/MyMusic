package com.ixuea.courses.mymusic.component.splash.fragment;

import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;
import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.databinding.FragmentDialogTermServiceBinding;
import com.ixuea.courses.mymusic.fragment.BaseDialogFragment;
import com.ixuea.courses.mymusic.fragment.BaseViewModelDialogFragment;
import com.ixuea.courses.mymusic.util.ScreenUtil;
import com.ixuea.courses.mymusic.util.SuperTextUtil;
import com.ixuea.superui.process.SuperProcessUtil;

/**
 * 服务条款和隐私协议对话框
 */
public class TermServiceDialogFragment extends BaseViewModelDialogFragment<FragmentDialogTermServiceBinding> {

    private static final String TAG = "TermServiceDialogFragment";
    private View.OnClickListener onAgreementClickListener;

    @Override
    protected void initViews() {
        super.initViews();

        setCancelable(false);

        SuperTextUtil.setLinkColor(binding.content, getActivity().getColor(R.color.link));
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        Spanned content = Html.fromHtml(getString(R.string.term_service_privacy_content));
        SpannableStringBuilder result = SuperTextUtil.setHtmlLinkClick(content, new SuperTextUtil.OnLinkClickListener() {
            @Override
            public void onLinkClick(String data) {
                Log.d(TAG, "onLinkClick: " + data);
            }
        });
        binding.content.setText(result);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        binding.primary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                onAgreementClickListener.onClick(view);
            }
        });

        binding.disagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                SuperProcessUtil.killApp();
            }
        });
    }

    public static TermServiceDialogFragment newInstance() {

        Bundle args = new Bundle();

        TermServiceDialogFragment fragment = new TermServiceDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 显示对话框
     * @param fragmentManager
     * @param onAgreementClickListener 同意按钮点击回调
     */
    public static void show(FragmentManager fragmentManager, View.OnClickListener onAgreementClickListener) {
        TermServiceDialogFragment fragment = newInstance();
        fragment.onAgreementClickListener = onAgreementClickListener;
        fragment.show(fragmentManager, "TermServiceDialogFragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        // change the width of term service fragment
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = (int) (ScreenUtil.getScreenWith(getContext()) * 0.9);
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
    }
}
