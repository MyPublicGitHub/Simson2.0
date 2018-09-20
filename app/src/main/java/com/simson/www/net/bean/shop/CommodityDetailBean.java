package com.simson.www.net.bean.shop;

import java.util.List;

public class CommodityDetailBean {

    /**
     * item_id : 11201809
     * item_name : 【头顶加密种植】 【毛囊检测】 5分钟头皮体验，直抵毛囊深处，轻松掌握毛囊健康问题
     * present_price : 11.00
     * original_price : 0.10
     * item_point : 0
     * browse : 0
     * subscribes : 0
     * is_delivery : 0
     * is_point : 0
     * picture : ["http://58.213.14.195:8081/upload/item/毛囊检测2/1.jpg","http://58.213.14.195:8081/upload/item/毛囊检测2/2.jpg","http://58.213.14.195:8081/upload/item/毛囊检测2/3.jpg","http://58.213.14.195:8081/upload/item/毛囊检测2/4.jpg"]
     */

    private String item_id;
    private String item_name;
    private String present_price;
    private String original_price;
    private int item_point;
    private int browse;
    private int subscribes;
    private int is_delivery;
    private int is_point;
    private List<String> picture;

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

    public String getPresent_price() {
        return present_price;
    }

    public void setPresent_price(String present_price) {
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

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }
}
