package com.simson.www.net.bean.shop;

import java.util.List;

public class CommodityDetailBean {


    /**
     * item_id : 2018009
     * item_name : 【种植睫毛】 【私信领红包 送保险】 无痕种植，效果自然，过程无痛安全
     * present_price : 23000.00
     * original_price : 27600.00
     * item_point : 0
     * hair_follicles_number : 500
     * browse : 0
     * subscribes : 0
     * is_delivery : 0
     * is_point : 0
     * is_technology : 1
     * is_collect : 0
     * picture : ["https://appapi.maofa.com/userfiles/item/kunmingjiemao/kunmingjiemaozhongzhitoutu1.jpg","https://appapi.maofa.com/userfiles/item/kunmingjiemao/kunmingjiemaozhongzhitoutu2.jpg","https://appapi.maofa.com/userfiles/item/kunmingjiemao/kunmingjiemaozhongzhitoutu3.jpg","https://appapi.maofa.com/userfiles/item/kunmingjiemao/kunmingjiemaozhongzhitoutu4.jpg"]
     */

    private String item_id;
    private String item_name;
    private double present_price;
    private String original_price;
    private int item_point;
    private int hair_follicles_number;
    private int browse;
    private int subscribes;
    private int is_delivery;
    private int is_point;
    private int is_technology;
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

    public int getHair_follicles_number() {
        return hair_follicles_number;
    }

    public void setHair_follicles_number(int hair_follicles_number) {
        this.hair_follicles_number = hair_follicles_number;
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

    public int getIs_technology() {
        return is_technology;
    }

    public void setIs_technology(int is_technology) {
        this.is_technology = is_technology;
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
