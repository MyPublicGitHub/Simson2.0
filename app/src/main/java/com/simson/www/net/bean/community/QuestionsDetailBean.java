package com.simson.www.net.bean.community;

import java.util.List;

public class QuestionsDetailBean {

    /**
     * questions_id : 1111
     * customer_id : 2018090115357871316905625
     * customer_name : 176****7732
     * customer_head : http://58.213.14.195:8081/upload/customer/201810/M7nnxi0yWKYR1xL0.png
     * questions_content : 如何保养头发
     * browse : 0
     * create_time : 2018-09-27
     * is_display : 0
     * is_follow : 0
     * praises : 0
     * answerCount : 1
     * pictures : ["http://58.213.14.195:8081/upload/questions/f271c674.jpg"]
     * pictureSize : 1
     * answers : [{"answer_id":"113","answer_content":"黑芝麻泡水喝可以保养头发","doctor_id":"111401","doctor_name":"吴望月","doctor_head":"https://appapi.maofa.com/userfiles/doctor/head/guangzhou/wuwangyue.png","position":"医师","create_time":"2018-09-27 10:49"}]
     */

    private String questions_id;
    private String customer_id;
    private String customer_name;
    private String customer_head;
    private String questions_content;
    private int browse;
    private String create_time;
    private int is_display;
    private int is_follow;
    private int praises;
    private int answerCount;
    private int pictureSize;
    private List<String> pictures;
    private List<AnswersBean> answers;

    public String getQuestions_id() {
        return questions_id;
    }

    public void setQuestions_id(String questions_id) {
        this.questions_id = questions_id;
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

    public String getQuestions_content() {
        return questions_content;
    }

    public void setQuestions_content(String questions_content) {
        this.questions_content = questions_content;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(int browse) {
        this.browse = browse;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getIs_display() {
        return is_display;
    }

    public void setIs_display(int is_display) {
        this.is_display = is_display;
    }

    public int getIs_follow() {
        return is_follow;
    }

    public void setIs_follow(int is_follow) {
        this.is_follow = is_follow;
    }

    public int getPraises() {
        return praises;
    }

    public void setPraises(int praises) {
        this.praises = praises;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public int getPictureSize() {
        return pictureSize;
    }

    public void setPictureSize(int pictureSize) {
        this.pictureSize = pictureSize;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public List<AnswersBean> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswersBean> answers) {
        this.answers = answers;
    }

    public static class AnswersBean {
        /**
         * answer_id : 113
         * answer_content : 黑芝麻泡水喝可以保养头发
         * doctor_id : 111401
         * doctor_name : 吴望月
         * doctor_head : https://appapi.maofa.com/userfiles/doctor/head/guangzhou/wuwangyue.png
         * position : 医师
         * create_time : 2018-09-27 10:49
         */

        private String answer_id;
        private String answer_content;
        private String doctor_id;
        private String doctor_name;
        private String doctor_head;
        private String position;
        private String create_time;

        public String getAnswer_id() {
            return answer_id;
        }

        public void setAnswer_id(String answer_id) {
            this.answer_id = answer_id;
        }

        public String getAnswer_content() {
            return answer_content;
        }

        public void setAnswer_content(String answer_content) {
            this.answer_content = answer_content;
        }

        public String getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(String doctor_id) {
            this.doctor_id = doctor_id;
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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
