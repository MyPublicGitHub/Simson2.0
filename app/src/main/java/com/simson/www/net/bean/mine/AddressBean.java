package com.simson.www.net.bean.mine;

import java.io.Serializable;

public class AddressBean implements Serializable {

    /**
     * delivery_id : 3acdb0e085944f27837e6aa597d1f9b5
     * customer_id : 2018090115357871316905625
     * address : sdf  df
     * consignee : ff
     * contact_number : 17633717732
     * is_default : 0
     * location : 浙江省杭州市滨江区
     */

    private String delivery_id;
    private String customer_id;
    private String address;
    private String consignee;
    private String contact_number;
    private int is_default;
    private String location;

    public String getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(String delivery_id) {
        this.delivery_id = delivery_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
