package com.simson.www.net.bean.home;


import java.io.Serializable;
import java.util.List;

public class HomeDataBean implements Serializable {

    /**
     * id : 9
     * name : 测试支付金额
     * item_type : 1
     * present_price : 0.01
     * original_price : 10000
     * point : 0
     * img_path : http://192.168.99.151:8080/upload/eyebrow.png
     * hospital_name : 南京新生医院
     * buyNum : 0
     * data_status : item
     * item_name : 疤痕脱发
     * popularizationId : kp002
     * customerId : 2018060715283371998337736
     * user_name : 17633717732
     * user_head_img : ww
     * type : 2
     * issue_time : 2018/05/14
     * title : 生姜生发的道理
     * content : 你知道吗，之前
     * link_url : http://www.baidu.com
     * browse : 45
     * comments : 1
     * collect : 1
     * likes : 1
     * isFollow : 0
     * isCollect : 0
     * isLike : 0
     * pictures : [{"id":"tkp002","url":"http://58.213.14.195:8081/upload/hospital.png"},{"id":"tkp003","url":"http://58.213.14.195:8081/upload/hsss.png"}]
     * videos : []
     */

    private String id;
    private String diaryId;
    private String name;
    private int item_type;
    private double present_price;
    private double original_price;
    private int point;
    private String img_path;
    private String hospital_name;
    private int buyNum;
    private String data_status;
    private String item_name;
    private String popularizationId;
    private String customerId;
    private String user_name;
    private String user_head_img;
    private int type;
    private String issue_time;
    private String title;
    private String content;
    private String link_url;
    private int browse;
    private int comments;
    private int collect;
    private int likes;
    private int isFollow;
    private int isCollect;
    private int isLike;
    private String questionId;
    private List<PicturesBean> pictures;
    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
    public String getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getItem_type() {
        return item_type;
    }

    public void setItem_type(int item_type) {
        this.item_type = item_type;
    }

    public double getPresent_price() {
        return present_price;
    }

    public void setPresent_price(double present_price) {
        this.present_price = present_price;
    }

    public double getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(double original_price) {
        this.original_price = original_price;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public String getData_status() {
        return data_status;
    }

    public void setData_status(String data_status) {
        this.data_status = data_status;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getPopularizationId() {
        return popularizationId;
    }

    public void setPopularizationId(String popularizationId) {
        this.popularizationId = popularizationId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_head_img() {
        return user_head_img;
    }

    public void setUser_head_img(String user_head_img) {
        this.user_head_img = user_head_img;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIssue_time() {
        return issue_time;
    }

    public void setIssue_time(String issue_time) {
        this.issue_time = issue_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(int browse) {
        this.browse = browse;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }

    public int getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(int isCollect) {
        this.isCollect = isCollect;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public List<PicturesBean> getPictures() {
        return pictures;
    }

    public void setPictures(List<PicturesBean> pictures) {
        this.pictures = pictures;
    }


}
