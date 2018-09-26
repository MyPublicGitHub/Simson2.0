package com.simson.www.net.bean.community;

import java.util.List;

public class PopularizationBean {

    /**
     * popularization_id : 123123
     * item_type_id :
     * hospital_name :
     * hospital_icon :
     * title :
     * popularization_type :
     * issue_time :
     * browse : 0
     * is_collect : 0
     * is_follow : 0
     * pictures : ["https://images.maofa.com/userfiles/24bf2ba294c2fcca9f415adc990734b4/images/popularization/files/2018/08/35b9fa9c.jpg"]
     */

    private String popularization_id;
    private String item_type_id;
    private String hospital_name;
    private String hospital_icon;
    private String title;
    private String popularization_type;
    private String issue_time;
    private int browse;
    private int is_collect;
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

    public String getPopularization_type() {
        return popularization_type;
    }

    public void setPopularization_type(String popularization_type) {
        this.popularization_type = popularization_type;
    }

    public String getIssue_time() {
        return issue_time;
    }

    public void setIssue_time(String issue_time) {
        this.issue_time = issue_time;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(int browse) {
        this.browse = browse;
    }

    public int getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(int is_collect) {
        this.is_collect = is_collect;
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
