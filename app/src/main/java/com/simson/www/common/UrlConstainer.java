package com.simson.www.common;

import com.simson.www.BuildConfig;

/**
 * Api接口地址
 */
public class UrlConstainer {
    public static final boolean DEBUG = BuildConfig.DEBUG;
    // public static final String baseUrl_DEBUG = "http://58.213.14.195:8081/simson/";
    //public static final String baseUrl_DEBUG = "http://58.213.14.195:8082/simson_admin/";
    //public static final String baseUrl_DEBUG = "https://images.maofa.com/simson/";
    //public static final String baseUrl_DEBUG = "http://58.213.14.195:8082/simson/";
    public static final String baseUrl_DEBUG = "https://appapi.maofa.com/simson/";
    public static final String baseUrl_RELEASE = "https://appapi.maofa.com/simson/";

    public static final String LOGIN = "loginController/login";//登录
    public static final String REGISTER = "loginController/register";//注册
    public static final String ITEM_TYPE = "diaryController/itemType";//首页日记查询条件
    public static final String HOME_ITEM = "diaryController/diaryList";

    public static String getBaseUrl() {
        if (DEBUG) {
            return baseUrl_DEBUG;
        } else {
            return baseUrl_RELEASE;
        }
    }

}
