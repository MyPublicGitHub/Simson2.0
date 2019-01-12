package com.simson.www.net.bean.mine;

public class CustomerBasicBean {

    /**
     * card_amount : 345335535.20
     * points : 9996000
     * customer_name : 176****7732
     * customer_head : http://58.213.14.195:8081/upload/customer/201810/M7nnxi0yWKYR1xL0.png
     * birthday : 1990-03-02
     */

    private String card_amount;
    private int points;
    private String customer_name;
    private String customer_head;
    private String birthday;

    public String getIs_internal_staff() {
        return is_internal_staff;
    }

    public void setIs_internal_staff(String is_internal_staff) {
        this.is_internal_staff = is_internal_staff;
    }

    private String is_internal_staff;

    public String getCard_amount() {
        return card_amount;
    }

    public void setCard_amount(String card_amount) {
        this.card_amount = card_amount;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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
}
