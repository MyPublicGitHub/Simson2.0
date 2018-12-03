package com.simson.www.application;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.simson.www.BuildConfig;
import com.simson.www.R;
import com.stickercamera.App;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import cn.jpush.android.api.JPushInterface;

public class MyApp extends App {
    //private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        //refWatcher = setupLeakCanary();//初始化内存泄漏检测工具
        AppContext.initialize(this);//初始化App配置

        JPushInterface.setDebugMode(BuildConfig.DEBUG);//极光推送
        JPushInterface.init(this);
        initUM();
    }

    private void initUM() {
        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(BuildConfig.DEBUG);
        UMConfigure.init(this, "5af56b9cf43e4855a9000053", "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");//友盟分享
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

    }

    //    private RefWatcher setupLeakCanary() {
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return RefWatcher.DISABLED;
//        }
//        return LeakCanary.install(this);
//    }
//    public static RefWatcher getRefWatcher(Context context) {
//        MyApp application = (MyApp) context.getApplicationContext();
//        return application.refWatcher;
//    }
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
