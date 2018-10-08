package com.simson.www.net.bean.home;

public class HospitalDeviceBean {

    /**
     * device_id : 123
     * device_name : fue毛囊提取机
     * device_picture : https://images.maofa.com/userfiles/24bf2ba294c2fcca9f415adc990734b4/images/popularization/files/2018/08/35b9fa9c.jpg
     * device_link : http://58.213.14.195:8081/simson/webview/device/device.jsp
     * browse : 3
     */

    private String device_id;
    private String device_name;
    private String device_picture;
    private String device_link;
    private int browse;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_picture() {
        return device_picture;
    }

    public void setDevice_picture(String device_picture) {
        this.device_picture = device_picture;
    }

    public String getDevice_link() {
        return device_link;
    }

    public void setDevice_link(String device_link) {
        this.device_link = device_link;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(int browse) {
        this.browse = browse;
    }
}
