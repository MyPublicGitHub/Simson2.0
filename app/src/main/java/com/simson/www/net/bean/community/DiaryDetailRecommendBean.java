package com.simson.www.net.bean.community;

public class DiaryDetailRecommendBean {

    /**
     * diary_id : 2
     * customer_id : 2018083115357026708136690
     * customer_name : 131****2345
     * customer_head : http://58.213.14.195:8081/upload/user.jpg
     * item_type_id : 1
     * titile : 我的大光头没有了
     * content : 我在也不用担心秃头了，啦啦啦啦啦绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿
     * browse : 0
     * counts : 1
     * diary_picture : http://58.213.14.195:8081/upload/diary/7.png
     */

    private String diary_id;
    private String customer_id;
    private String customer_name;
    private String customer_head;
    private String item_type_id;
    private String titile;
    private String content;
    private int browse;
    private int counts;
    private String diary_picture;

    public String getDiary_id() {
        return diary_id;
    }

    public void setDiary_id(String diary_id) {
        this.diary_id = diary_id;
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

    public String getItem_type_id() {
        return item_type_id;
    }

    public void setItem_type_id(String item_type_id) {
        this.item_type_id = item_type_id;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(int browse) {
        this.browse = browse;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public String getDiary_picture() {
        return diary_picture;
    }

    public void setDiary_picture(String diary_picture) {
        this.diary_picture = diary_picture;
    }
}
