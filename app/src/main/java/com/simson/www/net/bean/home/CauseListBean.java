package com.simson.www.net.bean.home;

import java.util.List;

public class CauseListBean {

    /**
     * alopecia_cause_id : 123
     * hospital_name : 南京新生植发
     * hospital_icon : http://58.213.14.195:8081/upload/hospital/logo@2x.png
     * title : 了解一下
     * alopecia_cause_link : http://58.213.14.195:8081/simson/webview/alopeciaCause/alopeciaCause.jsp
     * create_time : 2018-09-27
     * browse : 7
     * collects : 0
     * praises : 0
     * is_follow : 0
     * pictures : ["https://images.maofa.com/userfiles/24bf2ba294c2fcca9f415adc990734b4/images/popularization/files/2018/08/35b9fa9c.jpg"]
     */

    private String alopecia_cause_id;
    private String hospital_name;
    private String hospital_icon;
    private String title;
    private String link_url;
    private String create_time;
    private int browse;
    private int collects;
    private int praises;
    private int is_follow;
    private List<String> pictures;

    public String getAlopecia_cause_id() {
        return alopecia_cause_id;
    }

    public void setAlopecia_cause_id(String alopecia_cause_id) {
        this.alopecia_cause_id = alopecia_cause_id;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getHospital_icon() {
        return hospital_icon;
    }

    public void setHospital_icon(String hospital_icon) {
        this.hospital_icon = hospital_icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlopecia_cause_link() {
        return link_url;
    }

    public void setAlopecia_cause_link(String alopecia_cause_link) {
        this.link_url = alopecia_cause_link;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(int browse) {
        this.browse = browse;
    }

    public int getCollects() {
        return collects;
    }

    public void setCollects(int collects) {
        this.collects = collects;
    }

    public int getPraises() {
        return praises;
    }

    public void setPraises(int praises) {
        this.praises = praises;
    }

    public int getIs_follow() {
        return is_follow;
    }

    public void setIs_follow(int is_follow) {
        this.is_follow = is_follow;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
