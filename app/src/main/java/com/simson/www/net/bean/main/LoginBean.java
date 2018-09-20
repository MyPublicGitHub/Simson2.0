package com.simson.www.net.bean.main;

public class LoginBean {

    /**
     * customerId : 2018090115357871316905625
     * mobile : 17633717732
     * customerName : 176****7732
     * nickName : 176****7732
     * customerHead : http://58.213.14.195:8081/upload/user.jpg
     * deviceToken : 安卓
     * appType : 2
     */

    private String customerId;
    private String mobile;
    private String customerName;
    private String nickName;
    private String customerHead;
    private String deviceToken;
    private int appType;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCustomerHead() {
        return customerHead;
    }

    public void setCustomerHead(String customerHead) {
        this.customerHead = customerHead;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }
}
