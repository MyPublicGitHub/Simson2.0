package com.simson.www.net.bean.home;

import java.util.List;

public class HospitalDetailBean {

    /**
     * hospital_id : 1
     * hospital_name : 南京新生植发
     * hospital_star : 5
     * hospital_icon : http://58.213.14.195:8081/upload/hospital/nanjing.png
     * consulting_phone : 400-666-7272
     * hospital_address : 江苏省南京市鼓楼区福建路31-1号
     * is_authentication : 1
     * hospital_longitude : 118.764180
     * hospital_latitude : 32.083646
     * subscribes : 4
     * is_follow : 1
     * doctors : 9
     * devices : 6
     * pictures : ["http://58.213.14.195:8081/upload/hospital/nanjing/nj1.jpg","http://58.213.14.195:8081/upload/hospital/nanjing/nj2.jpg","http://58.213.14.195:8081/upload/hospital/nanjing/nj3.jpg","http://58.213.14.195:8081/upload/hospital/nanjing/nj4.jpg","http://58.213.14.195:8081/upload/hospital/nanjing/nj5.jpg"]
     */

    private String hospital_id;
    private String hospital_name;
    private int hospital_star;
    private String hospital_icon;
    private String consulting_phone;
    private String hospital_address;
    private int is_authentication;
    private String hospital_longitude;
    private String hospital_latitude;
    private int subscribes;
    private int is_follow;
    private int doctors;
    private int devices;
    private List<String> pictures;

    public String getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(String hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public int getHospital_star() {
        return hospital_star;
    }

    public void setHospital_star(int hospital_star) {
        this.hospital_star = hospital_star;
    }

    public String getHospital_icon() {
        return hospital_icon;
    }

    public void setHospital_icon(String hospital_icon) {
        this.hospital_icon = hospital_icon;
    }

    public String getConsulting_phone() {
        return consulting_phone;
    }

    public void setConsulting_phone(String consulting_phone) {
        this.consulting_phone = consulting_phone;
    }

    public String getHospital_address() {
        return hospital_address;
    }

    public void setHospital_address(String hospital_address) {
        this.hospital_address = hospital_address;
    }

    public int getIs_authentication() {
        return is_authentication;
    }

    public void setIs_authentication(int is_authentication) {
        this.is_authentication = is_authentication;
    }

    public String getHospital_longitude() {
        return hospital_longitude;
    }

    public void setHospital_longitude(String hospital_longitude) {
        this.hospital_longitude = hospital_longitude;
    }

    public String getHospital_latitude() {
        return hospital_latitude;
    }

    public void setHospital_latitude(String hospital_latitude) {
        this.hospital_latitude = hospital_latitude;
    }

    public int getSubscribes() {
        return subscribes;
    }

    public void setSubscribes(int subscribes) {
        this.subscribes = subscribes;
    }

    public int getIs_follow() {
        return is_follow;
    }

    public void setIs_follow(int is_follow) {
        this.is_follow = is_follow;
    }

    public int getDoctors() {
        return doctors;
    }

    public void setDoctors(int doctors) {
        this.doctors = doctors;
    }

    public int getDevices() {
        return devices;
    }

    public void setDevices(int devices) {
        this.devices = devices;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
