package com.simson.www.net.bean.mine;

import com.google.gson.annotations.SerializedName;

public class PaymentOrderBean {

    /**
     * appid : wx228b74e2c2ea50c4
     * partnerid : 1504082071
     * prepayid : wx241403210852988443f81f283289826422
     * package : Sign=WXPay
     * noncestr : 1258883bce0241ce9d3cf147e621e661
     * timestamp : 1527141807
     * sign : 8C73B502CC13B927F7FE8DF1BC8DBC1B
     */

    private String appid;
    private String partnerid;
    private String prepayid;
    @SerializedName("package")
    private String packageX;
    private String noncestr;
    private String timestamp;
    private String sign;//微信使用
    private String singn;//支付宝使用


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSingn() {
        return singn;
    }

    public void setSingn(String singn) {
        this.singn = singn;
    }
}

