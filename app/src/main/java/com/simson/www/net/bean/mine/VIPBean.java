package com.simson.www.net.bean.mine;

public class VIPBean {

    /**
     * vip_level : V0
     * required_amount : 1000
     * discount : 1.00
     */

    private String vip_level;
    private String required_amount;
    private double discount;

    public String getVip_level() {
        return vip_level;
    }

    public void setVip_level(String vip_level) {
        this.vip_level = vip_level;
    }

    public String getRequired_amount() {
        return required_amount;
    }

    public void setRequired_amount(String required_amount) {
        this.required_amount = required_amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
