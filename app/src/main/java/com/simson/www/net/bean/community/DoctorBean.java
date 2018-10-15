package com.simson.www.net.bean.community;

import java.util.List;

public class DoctorBean {


    private List<RecommendBean> recommend;
    private List<DoctorItemBean> list;

    public List<RecommendBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendBean> recommend) {
        this.recommend = recommend;
    }

    public List<DoctorItemBean> getList() {
        return list;
    }

    public void setList(List<DoctorItemBean> list) {
        this.list = list;
    }

    public static class RecommendBean {
        /**
         * doctor_id : 111105
         * doctor_picture : http://58.213.14.195:8081/upload/doctor/picture/yangzhenqian.jpg
         */

        private String doctor_id;
        private String doctor_picture;

        public String getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(String doctor_id) {
            this.doctor_id = doctor_id;
        }

        public String getDoctor_picture() {
            return doctor_picture;
        }

        public void setDoctor_picture(String doctor_picture) {
            this.doctor_picture = doctor_picture;
        }
    }

    public static class DoctorItemBean {
        /**
         * doctor_id : 111300
         * hospital_id : 3
         * hospital_name : 福州新生植发
         * doctor_name : 陈男男
         * doctor_head : http://58.213.14.195:8081/upload/doctor/head/fuzhou/chennannan.png
         * position : 主任医师
         * be_good_at : 毛发种植
         * doctor_star : 5
         * is_authentication : 1
         * is_follow : 0
         */

        private String doctor_id;
        private String hospital_id;
        private String hospital_name;
        private String doctor_name;
        private String doctor_head;
        private String position;
        private String be_good_at;
        private int doctor_star;
        private int is_authentication;
        private int is_follow;

        public String getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(String doctor_id) {
            this.doctor_id = doctor_id;
        }

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

        public String getDoctor_name() {
            return doctor_name;
        }

        public void setDoctor_name(String doctor_name) {
            this.doctor_name = doctor_name;
        }

        public String getDoctor_head() {
            return doctor_head;
        }

        public void setDoctor_head(String doctor_head) {
            this.doctor_head = doctor_head;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getBe_good_at() {
            return be_good_at;
        }

        public void setBe_good_at(String be_good_at) {
            this.be_good_at = be_good_at;
        }

        public int getDoctor_star() {
            return doctor_star;
        }

        public void setDoctor_star(int doctor_star) {
            this.doctor_star = doctor_star;
        }

        public int getIs_authentication() {
            return is_authentication;
        }

        public void setIs_authentication(int is_authentication) {
            this.is_authentication = is_authentication;
        }

        public int getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(int is_follow) {
            this.is_follow = is_follow;
        }
    }
}
