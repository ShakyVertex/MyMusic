package com.ixuea.courses.mymusic.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ScreenUtil {


    public static int getScreenWith(Context context) {
        //获取window管理器
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        //创建显示对象
        DisplayMetrics outDisplayMetrics = new DisplayMetrics();

        //获取默认显示对象
        wm.getDefaultDisplay().getMetrics(outDisplayMetrics);

        //返回屏幕宽度
        return outDisplayMetrics.widthPixels;
    }
}
