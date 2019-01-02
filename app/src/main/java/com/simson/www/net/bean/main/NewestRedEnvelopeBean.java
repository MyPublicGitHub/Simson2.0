package com.simson.www.net.bean.main;

public class NewestRedEnvelopeBean {

    /**
     * crowd_no : Viioe---KpFBLMmvp04U31-swMUKgiTKTvRJiT2Qh_Osn4ge4wBRFkiLOjq1h1Jd
     * origin_crowd_no : 20181222099410190596197632374004
     * coupon_name : 20181222第四轮红包
     * camp_status : PAID
     * result : 0
     * message : 成功
     * timestamp : 1545458918918
     */

    private String crowd_no;
    private String origin_crowd_no;
    private String coupon_name;
    private String camp_status;
    private int result;
    private String message;
    private long timestamp;

    public String getCrowd_no() {
        return crowd_no;
    }

    public void setCrowd_no(String crowd_no) {
        this.crowd_no = crowd_no;
    }

    public String getOrigin_crowd_no() {
        return origin_crowd_no;
    }

    public void setOrigin_crowd_no(String origin_crowd_no) {
        this.origin_crowd_no = origin_crowd_no;
    }

    public String getCoupon_name() {
        return coupon_name;
    }

    public void setCoupon_name(String coupon_name) {
        this.coupon_name = coupon_name;
    }

    public String getCamp_status() {
        return camp_status;
    }

    public void setCamp_status(String camp_status) {
        this.camp_status = camp_status;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
