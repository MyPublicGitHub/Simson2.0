package com.simson.www.net.bean.shop;

import java.util.List;

public class CommodityDetailBean {


    /**
     * item_id : 2018007
     * item_name : 【头顶加密种植】【私信领红包 送保险】无痕种植，个性定制，美丽从"头"做起
     * present_price : 888.00
     * original_price : 1065.60
     * item_point : 0
     * browse : 0
     * subscribes : 0
     * is_delivery : 0
     * is_point : 0
     * is_collect : 0
     * picture : ["http://58.213.14.195:8081/upload/item/kunmingtoudingjiami/1.jpg","http://58.213.14.195:8081/upload/item/kunmingtoudingjiami/2.jpg","http://58.213.14.195:8081/upload/item/kunmingtoudingjiami/3.jpg","http://58.213.14.195:8081/upload/item/kunmingtoudingjiami/4.jpg"]
     */

    private String item_id;
    private String item_name;
    private double present_price;
    private String original_price;
    private int item_point;
    private int browse;
    private int subscribes;
    private int is_delivery;
    private int is_point;
    private int is_collect;
    private List<String> picture;
    public List<String> pictures;

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getPresent_price() {
        return present_price;
    }

    public void setPresent_price(double present_price) {
        this.present_price = present_price;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public int getItem_point() {
        return item_point;
    }

    public void setItem_point(int item_point) {
        this.item_point = item_point;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(int browse) {
        this.browse = browse;
    }

    public int getSubscribes() {
        return subscribes;
    }

    public void setSubscribes(int subscribes) {
        this.subscribes = subscribes;
    }

    public int getIs_delivery() {
        return is_delivery;
    }

    public void setIs_delivery(int is_delivery) {
        this.is_delivery = is_delivery;
    }

    public int getIs_point() {
        return is_point;
    }

    public void setIs_point(int is_point) {
        this.is_point = is_point;
    }

    public int getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(int is_collect) {
        this.is_collect = is_collect;
    }

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }
}
