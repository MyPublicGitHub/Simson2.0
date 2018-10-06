package com.simson.www.net.bean.mine;

public class ShopCartBean {
    public boolean isCheck;
    public double priceUser;
    public int pointUser;
    /**
     * cart_id : 372a4fd9b3fc43679ebe29b8ce7d5d15
     * item_id : 2018007
     * item_name : 【头顶加密种植】【私信领红包 送保险】无痕种植，个性定制，美丽从"头"做起
     * item_icon : http://58.213.14.195:8081/upload/item/kunmingtoudingjiami/1.jpg
     * buy_num : 3
     * original_price : 1065.60
     * present_price : 888.00
     * item_point : 0
     * is_point : 0
     * create_time : 2018-09-11
     * is_off : 1
     */

    private String cart_id;
    private String item_id;
    private String item_name;
    private String item_icon;
    private int buy_num;
    private String original_price;
    private double present_price;
    private int item_point;
    private int is_point;
    private String create_time;
    private String is_off;

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

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

    public String getItem_icon() {
        return item_icon;
    }

    public void setItem_icon(String item_icon) {
        this.item_icon = item_icon;
    }

    public int getBuy_num() {
        return buy_num;
    }

    public void setBuy_num(int buy_num) {
        this.buy_num = buy_num;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public double getPresent_price() {
        return present_price;
    }

    public void setPresent_price(double present_price) {
        this.present_price = present_price;
    }

    public int getItem_point() {
        return item_point;
    }

    public void setItem_point(int item_point) {
        this.item_point = item_point;
    }

    public int getIs_point() {
        return is_point;
    }

    public void setIs_point(int is_point) {
        this.is_point = is_point;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getIs_off() {
        return is_off;
    }

    public void setIs_off(String is_off) {
        this.is_off = is_off;
    }
}
