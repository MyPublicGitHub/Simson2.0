package com.simson.www.common;

/**
 * Api接口地址
 */
public class UrlConstainer {
    public static final String baseUrl_DEBUG = "http://58.213.14.195:8081/simson/";
    public static final String baseUrl1_RELEASE = "https://appapi.maofa.com/simson_app/";

    public static final String LOGIN = "loginController/login";//登录
    public static final String GET_CODE = "loginController/code";//获取验证码
    public static final String REGISTER = "loginController/register";//注册

    public static final String HOME_LIST = "loginController/indexBlendData";

}
