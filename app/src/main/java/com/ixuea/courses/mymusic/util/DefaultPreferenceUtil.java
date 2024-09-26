package com.ixuea.courses.mymusic.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 偏好设置工具类
 * 是否登录了，是否显示引导界面，用户Id
 */
public class DefaultPreferenceUtil {
    private static final String TERMS_SERVICE = "TERMS_SERVICE";
    private static DefaultPreferenceUtil instance;
    private final Context context;
    private final SharedPreferences preference;

    /**
     * 构造方法
     * @param context
     */
    public DefaultPreferenceUtil(Context context) {
        // 保存上下文
        this.context = context.getApplicationContext();

        // 获取系统默认偏好设置，在设置界面保存的值就可以这样获取
        preference = PreferenceManager.getDefaultSharedPreferences(this.context);

        // 自定义名称
        // preference = this.context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    /**
     * 获取偏好设置单例
     */
    public synchronized static DefaultPreferenceUtil getInstance(Context context) {
        if (instance == null) {
            instance = new DefaultPreferenceUtil(context);
        }
        return instance;
    }

    /**
     * 设置同意了用户协议
     */
    public void setAcceptTermsServiceAgreement() {
        putBoolean(TERMS_SERVICE, true);
    }

    /**
     * 获取是否同意了用户协议，默认为false
     */
    public boolean isAcceptTermsServiceAgreement() {
        return preference.getBoolean(TERMS_SERVICE, false);
    }

    private void putBoolean(String key, boolean value) {
        preference.edit().putBoolean(key, value).apply();
    }
}
