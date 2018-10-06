package com.simson.www.net.bean.mine;

import java.util.List;

public class CaseBean {

    /**
     * case_id : 123
     * case_link : http://58.213.14.195:8081/simson/webview/case/case.jsp
     * case_name : 张先生
     * item_name : 发际线种植
     * hair_follicles_number : 2500U
     * hair_planting_technique : FUE
     * browse : 0
     * pictures : ["https://images.maofa.com/userfiles/24bf2ba294c2fcca9f415adc990734b4/images/popularization/files/2018/08/35b9fa9c.jpg"]
     */

    private String case_id;
    private String case_link;
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

    public String getCase_link() {
        return case_link;
    }

    public void setCase_link(String case_link) {
        this.case_link = case_link;
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
