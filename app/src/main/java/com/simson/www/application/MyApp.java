package com.simson.www.application;

import android.app.Application;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.simson.www.R;
import com.squareup.leakcanary.LeakCanary;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化内存泄漏检测工具
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        //初始化App配置
        AppContext.initialize(this);
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
