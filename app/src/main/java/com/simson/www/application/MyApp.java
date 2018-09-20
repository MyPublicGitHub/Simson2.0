package com.simson.www.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.simson.www.R;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import cn.jpush.android.api.JPushInterface;

public class MyApp extends Application {
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        refWatcher = setupLeakCanary();//初始化内存泄漏检测工具
        AppContext.initialize(this);//初始化App配置

        JPushInterface.setDebugMode(true);//极光推送
        JPushInterface.init(this);
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }
    public static RefWatcher getRefWatcher(Context context) {
        MyApp application = (MyApp) context.getApplicationContext();
        return application.refWatcher;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    //static 代码段可以防止内存泄露, 全局设置刷新头部及尾部，优先级最低
    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, refreshLayout) -> {
            //全局设置主题颜色
            refreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            //指定为ClassicsHeader
            return new ClassicsHeader(context);
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //默认Footer
            return new ClassicsFooter(context);
        });
    }

}
