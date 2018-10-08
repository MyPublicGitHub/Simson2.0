package com.simson.www.net.bean.mine;

public class CustomerInfoBean {

    /**
     * customer_id : 2018090115357871316905625
     * customer_name : 176****7732
     * customer_head : http://58.213.14.195:8081/upload/user.jpg
     * birthday :
     * gender : 0
     * location :
     */

    private String customer_id;
    private String customer_name;
    private String customer_head;
    private String birthday;
    private int gender;
    private String location;

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
