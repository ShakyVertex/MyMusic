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
import com.ixuea.courses.mymusic.fragment.BaseDialogFragment;
import com.ixuea.courses.mymusic.util.ScreenUtil;
import com.ixuea.courses.mymusic.util.SuperTextUtil;
import com.ixuea.superui.process.SuperProcessUtil;

/**
 * 服务条款和隐私协议对话框
 */
public class TermServiceDialogFragment extends BaseDialogFragment {

    private static final String TAG = "TermServiceDialogFragment";
    private TextView contentView;
    private MaterialButton primaryView;
    private Button disagreeView;
    private View.OnClickListener onAgreementClickListener;

    @Override
    protected void initViews() {
        super.initViews();

        setCancelable(false);

        contentView = findViewById(R.id.content);
        primaryView = findViewById(R.id.primary);
        disagreeView = findViewById(R.id.disagree);

        SuperTextUtil.setLinkColor(contentView, getActivity().getColor(R.color.link));
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
        contentView.setText(result);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        primaryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                onAgreementClickListener.onClick(view);
            }
        });

        disagreeView.setOnClickListener(new View.OnClickListener() {
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
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_term_service, container, false);
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
