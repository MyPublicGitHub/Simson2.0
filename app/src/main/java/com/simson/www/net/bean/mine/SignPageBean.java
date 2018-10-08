package com.simson.www.net.bean.mine;

import java.util.List;

public class SignPageBean {
    /**
     * sign_days : 0
     * days : [{"day":"1","sign":"0"},{"day":"2","sign":"0"},{"day":"3","sign":"0"},{"day":"4","sign":"0"},{"day":"5","sign":"0"},{"day":"6","sign":"0"},{"day":"7","sign":"0"},{"day":"8","sign":"0"},{"day":"9","sign":"0"},{"day":"10","sign":"0"},{"day":"11","sign":"0"},{"day":"12","sign":"0"},{"day":"13","sign":"0"},{"day":"14","sign":"0"},{"day":"15","sign":"0"},{"day":"16","sign":"0"},{"day":"17","sign":"0"},{"day":"18","sign":"0"},{"day":"19","sign":"0"},{"day":"20","sign":"0"},{"day":"21","sign":"0"},{"day":"22","sign":"0"},{"day":"23","sign":"0"},{"day":"24","sign":"0"},{"day":"25","sign":"0"},{"day":"26","sign":"0"},{"day":"27","sign":"0"},{"day":"28","sign":"0"},{"day":"29","sign":"0"},{"day":"30","sign":"0"},{"day":"31","sign":"0"}]
     */

    private String sign_days;
    private List<DaysBean> days;

    public String getSign_days() {
        return sign_days;
    }

    public void setSign_days(String sign_days) {
        this.sign_days = sign_days;
    }

    public List<DaysBean> getDays() {
        return days;
    }

    public void setDays(List<DaysBean> days) {
        this.days = days;
    }

    public static class DaysBean {
        /**
         * day : 1
         * sign : 0
         */

        private String day;
        private String sign;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
