package com.simson.www.net.bean.mine;

import java.util.List;

public class CaseBean {

    /**
     * case_id : 50d657c5fcca435d98345bd8773217f3
     * link_url : http://58.213.14.195:8082/userfiles/24bf2ba294c2fcca9f415adc990734b4/case/201810/50d657c5fcca435d98345bd8773217f3.html
     * case_name : 疤痕植发的优点有哪些
     * item_name : 疤痕种植
     * hair_follicles_number : 500U
     * hair_planting_technique : TDDP-E
     * browse : 0
     * pictures : ["http://58.213.14.195:8082/userfiles/24bf2ba294c2fcca9f415adc990734b4/images/case/picture/2018/10/242ffb31.jpg","http://58.213.14.195:8082/userfiles/24bf2ba294c2fcca9f415adc990734b4/images/case/picture/2018/10/242ffb31.jpg"]
     */

    private String case_id;
    private String link_url;
    private String case_name;
    private String item_name;
    private String hair_follicles_number;
    private String hair_planting_technique;
    private int browse;
    private List<String> pictures;

    public String getCase_id() {
        return case_id;
    }

    public void setCase_id(String case_id) {
        this.case_id = case_id;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public String getCase_name() {
        return case_name;
    }

    public void setCase_name(String case_name) {
        this.case_name = case_name;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getHair_follicles_number() {
        return hair_follicles_number;
    }

    public void setHair_follicles_number(String hair_follicles_number) {
        this.hair_follicles_number = hair_follicles_number;
    }

    public String getHair_planting_technique() {
        return hair_planting_technique;
    }

    public void setHair_planting_technique(String hair_planting_technique) {
        this.hair_planting_technique = hair_planting_technique;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(int browse) {
        this.browse = browse;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
