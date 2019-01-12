package com.simson.www.common;

/**
 * App 常量类
 */

public class Const {
    public static final String WE_CHAT_APP_ID = "wx228b74e2c2ea50c4";
    public static boolean RED = false;

    public static final String BUGLY_APP_ID = "c280c5ebb7";

    public static final String PAGE_SIZE = "10";  //单页显示的数量

    //事件Action
    public static class EVENT_ACTION {
        public static final String LOGIN = "LOGIN";
    }

    //用户相关
    public static class USER_INFO {
        public static final String CUSTOMER_ID = "CUSTOMER_ID";  //
        public static final String CUSTOMER_MOBLE = "CUSTOMER_MOBLE";  //
        public static final String CUSTOMER_IS_INTERNAL_STAFF = "IS_INTERNAL_STAFF";  //
        public static final String CUSTOMER_HEAD = "CUSTOMER_HEAD";  //
        public static final String CUSTOMER_NAME = "CUSTOMER_NAME";  //
        public static final String CUSTOMER_NICK_NAME = "CUSTOMER_NICK_NAME";  //
        public static final String CUSTOMER_SEX = "CUSTOMER_SEX";  //
        public static final String CUSTOMER_LOCATION = "CUSTOMER_LOCATION";  //
    }

    /**
     * WebView相关
     */
    public static final String WEB_VIEW_TITLE = "WEB_VIEW_TITLE";
    public static final String WEB_VIEW_URL = "WEB_VIEW_URL";

    //关注type：  1：医院；2医生；3顾客；商品4
    public static class FOLLOW_TYPE {
        public static final String HOSPITAL = "1";
        public static final String DOCTOR = "2";
        public static final String USER = "3";
        public static final String SHOP = "4";
    }

    //收藏type：   1：日记；2科普；3问答；4商品；5脱发原因
    public static class COLLECT_TYPE {
        public static final String DIARY = "1";
        public static final String POPULARIZATION = "2";
        public static final String QUESTION = "3";
        public static final String COMMODITY = "4";
        public static final String CAUSE = "5";
    }

    //点赞type：   1：日记；2科普；3问答；4商品；5脱发原因
    public static class PRAISE_TYPE {
        public static final String DIARY = "1";
        public static final String POPULARIZATION = "2";
        public static final String QUESTION = "3";
        public static final String COMMODITY = "4";
        public static final String CAUSE = "5";
    }
}
