package com.simson.www.net.bean.community;

import java.util.List;

public class DiaryDetailAppendBean {

    /**
     * append_id : append2
     * diary_id : 1
     * days : 75
     * content : 术后2个月了
     * diary_picture : ["http://58.213.14.195:8081/upload/diary/6.png","http://58.213.14.195:8081/upload/diary/6.png","http://58.213.14.195:8081/upload/diary/6.png","http://58.213.14.195:8081/upload/diary/6.png","http://58.213.14.195:8081/upload/diary/6.png","http://58.213.14.195:8081/upload/diary/6.png"]
     * picture_size : 6
     */

    private String append_id;
    private String diary_id;
    private String days;
    private String content;
    private int picture_size;
    private List<String> diary_picture;

    public String getAppend_id() {
        return append_id;
    }

    public void setAppend_id(String append_id) {
        this.append_id = append_id;
    }

    public String getDiary_id() {
        return diary_id;
    }

    public void setDiary_id(String diary_id) {
        this.diary_id = diary_id;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPicture_size() {
        return picture_size;
    }

    public void setPicture_size(int picture_size) {
        this.picture_size = picture_size;
    }

    public List<String> getDiary_picture() {
        return diary_picture;
    }

    public void setDiary_picture(List<String> diary_picture) {
        this.diary_picture = diary_picture;
    }
}
