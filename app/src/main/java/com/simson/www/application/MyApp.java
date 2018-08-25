package com.simson.www.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * 玩Android
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化内存泄漏检测工具
        if (LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
        //初始化App配置
        AppContext.initialize(this);
    }

}
