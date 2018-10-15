package com.simson.www.net.bean.home;

import java.util.List;

public class IndexSynchysisBean {


    /**
     * popularization_id : 59f4ef3491f3451493621cdc7b3ee55f
     * item_type_id : 1
     * hospital_id : 1
     * hospital_name : 南京新生植发
     * hospital_icon : http://58.213.14.195:8081/upload/hospital/logo@2x.png
     * title : 植发效果好吗?植发治疗脱发有哪些优势
     * popularization_link : http://58.213.14.195:8081/simson/webview/popularization/popularization.jsp
     * create_time : 2018-10-10
     * browse : 0
     * collects : 0
     * praises : 0
     * is_follow : 0
     * pictures : []
     * data_status : popularization
     */

    private String popularization_id;
    private String item_type_id;
    private String hospital_id;
    private String hospital_name;
    private String hospital_icon;
    private String title;
    private String popularization_link;
    private String create_time;
    private int browse;
    private int collects;
    private int praises;
    private int is_follow;
    private String data_status;
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

    public String getPopularization_link() {
        return popularization_link;
    }

    public void setPopularization_link(String popularization_link) {
        this.popularization_link = popularization_link;
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

    public String getData_status() {
        return data_status;
    }

    public void setData_status(String data_status) {
        this.data_status = data_status;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
