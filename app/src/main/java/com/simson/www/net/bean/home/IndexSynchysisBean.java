package com.simson.www.net.bean.home;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class IndexSynchysisBean implements MultiItemEntity {
    public static final int friendsCircle = 0;
    public static final int popularization = 1;
    public static final int OTHER = 2;

    @Override
    public int getItemType() {
        if ("friendsCircle".equals(data_status)) {//日记
            return friendsCircle;
        } else if ("popularization".equals(data_status)) {//商品
            return popularization;
        } else {
            return OTHER;
        }
    }

    /**
     * friends_circle_id : 451a300aad66449e86bf76212f0d0352
     * customer_id : 2018091915373510035276172
     * customer_name : 173****8583
     * customer_head : http://58.213.14.195:8081/upload/user.jpg
     * content : 发友圈ヾﾉ≧∀≦)o
     * create_time : 2018-10-14
     * is_follow : 0
     * comments : 0
     * praises : 0
     * pictures : ["http://58.213.14.195:8081/upload/friendsCircle/2018091915373510035276172/201810/Jg5NYr8O5l2VN7li.png"]
     * data_status : friendsCircle
     * popularization_id : 59f4ef3491f3451493621cdc7b3ee55f
     * item_type_id : 1
     * hospital_id : 1
     * hospital_name : 南京新生植发
     * hospital_icon : http://58.213.14.195:8081/upload/hospital/nanjing.png
     * title : 植发效果好吗?植发治疗脱发有哪些优势
     * link_url : http://58.213.14.195:8082/userfiles/24bf2ba294c2fcca9f415adc990734b4/popularization/201810/59f4ef3491f3451493621cdc7b3ee55f.html
     * browse : 0
     * collects : 0
     */

    private String friends_circle_id;
    private String customer_id;
    private String customer_name;
    private String customer_head;
    private String content;
    private String create_time;
    private int is_follow;
    private int comments;
    private int praises;
    private String data_status;
    private String popularization_id;
    private String item_type_id;
    private String hospital_id;
    private String hospital_name;
    private String hospital_icon;
    private String title;
    private String link_url;
    private int browse;
    private int collects;
    private List<String> pictures;

    public String getFriends_circle_id() {
        return friends_circle_id;
    }

    public void setFriends_circle_id(String friends_circle_id) {
        this.friends_circle_id = friends_circle_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_head() {
        return customer_head;
    }

    public void setCustomer_head(String customer_head) {
        this.customer_head = customer_head;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getIs_follow() {
        return is_follow;
    }

    public void setIs_follow(int is_follow) {
        this.is_follow = is_follow;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getPraises() {
        return praises;
    }

    public void setPraises(int praises) {
        this.praises = praises;
    }

    public String getData_status() {
        return data_status;
    }

    public void setData_status(String data_status) {
        this.data_status = data_status;
    }

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

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
