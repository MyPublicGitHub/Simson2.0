package com.simson.www.net.bean.community;

import java.util.List;

public class FriendsCircleBean {

    /**
     * friends_circle_id : b5fa5aa79cdc43fd886af9c85ab9032e
     * customer_id : 2018101315394146373078294
     * customer_name : 159****5493
     * customer_head : http://58.213.14.195:8081/upload/customer/201810/xnYm2wch57JEqMMG.png
     * content : 测试一下
     * create_time : 2018-10-15
     * is_follow : 0
     * comments : 0
     * praises : 0
     * pictures : ["http://58.213.14.195:8081/upload/friendsCircle/2018101315394146373078294/201810/Xb15jgpR9nBI5meb.png"]
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

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
