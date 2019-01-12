package com.simson.www.net.bean.shop;

import java.util.List;

public class BigEventBean {

    /**
     * big_event_id : 4e5190d618bc4cd4ae8f6157a67ccd90
     * event_date : 2018-09-15
     * title : 【群星荟萃  共享新生】“TVB一哥”罗嘉良、影视歌三栖明星景岗山、新生代小花郭艳、知名主持人赵岩松惊艳登场中国医疗美容行业发展调查万里行启动会暨第七届“关注效果平安植发”提议活动。
     * link_url : https://images.maofa.com/userfiles/24bf2ba294c2fcca9f415adc990734b4/bigEvent/201901/4e5190d618bc4cd4ae8f6157a67ccd90.html
     * browse : 0
     * praises : 0
     * is_praise : 0
     * pictures : ["https://images.maofa.com/userfiles/24bf2ba294c2fcca9f415adc990734b4/images/bigEvent/picture/2019/01/84bddac7.png"]
     */

    private String big_event_id;
    private String event_date;
    private String title;
    private String link_url;
    private int browse;
    private int praises;
    private int is_praise;
    private List<String> pictures;

    public String getBig_event_id() {
        return big_event_id;
    }

    public void setBig_event_id(String big_event_id) {
        this.big_event_id = big_event_id;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getPraises() {
        return praises;
    }

    public void setPraises(int praises) {
        this.praises = praises;
    }

    public int getIs_praise() {
        return is_praise;
    }

    public void setIs_praise(int is_praise) {
        this.is_praise = is_praise;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
