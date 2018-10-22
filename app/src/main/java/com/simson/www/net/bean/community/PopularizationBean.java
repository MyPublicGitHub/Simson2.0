package com.simson.www.net.bean.community;

import java.util.List;

public class PopularizationBean {

    /**
     * popularization_id : 8813b0b2e97d41ed9040ebbb255b1236
     * item_type_id : 3
     * hospital_id : 1
     * hospital_name : 南京新生植发
     * hospital_icon : http://58.213.14.195:8081/upload/hospital/nanjing.png
     * title : 睫毛种植效果效果多久?
     * link_url : http://58.213.14.195:8082/userfiles/24bf2ba294c2fcca9f415adc990734b4/popularization/201810/8813b0b2e97d41ed9040ebbb255b1236.html
     * create_time : 2018-10-15
     * browse : 0
     * collects : 0
     * praises : 0
     * is_follow : 1
     * pictures : ["http://58.213.14.195:8082/userfiles/24bf2ba294c2fcca9f415adc990734b4/images/popularization/picture/2018/10/5f7a0c53.jpg"]
     */

    private String popularization_id;
    private String item_type_id;
    private String hospital_id;
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

    public String getPopularization_id() {
        return popularization_id;
    }

    public void setPopularization_id(String popularization_id) {
        this.popularization_id = popularization_id;
    }

    public String getItem_type_id() {
        return item_type_id;
    }

    public void setItem_type_id(String item_type_id) {
        this.item_type_id = item_type_id;
    }

    public String getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(String hospital_id) {
        this.hospital_id = hospital_id;
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

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
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
