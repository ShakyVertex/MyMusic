package com.ixuea.superui.text;

import android.text.TextPaint;
import android.text.style.ClickableSpan;

import androidx.annotation.NonNull;

/**
 * 自定义Span来将超文本链接中的下划线去除
 */
public abstract class SuperClickableSpan extends ClickableSpan {
    /**
     * 更新绘制状态
     */
    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        // only set color
        ds.setColor(ds.linkColor);
        // remove underline
        ds.setUnderlineText(false);
    }
}
