package com.simson.www.net.bean.community;

import java.util.List;

public class QuestionsBean {


    /**
     * questions_id : 1112
     * customer_id : 2018091915373510035276172
     * customer_name : 173****8583
     * customer_head : http://58.213.14.195:8081/upload/user.jpg
     * questions_content : 什么是疤痕脱发
     * browse : 0
     * create_time : 2018-09-27
     * is_display : 1
     * is_follow : 0
     * praises : 0
     * answers : [{"answer_id":"114","answer_content":"疤痕脱发指头部由于外伤、疾病造成皮肤受损，毛囊生长部位不能够自然生长，疤痕部位毛发的生长也就因此受到一定的影响而形成的疤痕脱发。 后泛指其他部位因疤痕而不能够生长毛发的，也统称为疤痕脱发。","doctor_id":"111503","doctor_name":"魏德平","doctor_head":"http://58.213.14.195:8081/upload/doctor/head/chengdu/weideping.png","position":"医师","create_time":"2018-09-27 10:50"}]
     * pictures : []
     * pictureSize : 0
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
    private int pictureSize;
    private List<AnswersBean> answers;
    private List<String> pictures;

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

    public int getPictureSize() {
        return pictureSize;
    }

    public void setPictureSize(int pictureSize) {
        this.pictureSize = pictureSize;
    }

    public List<AnswersBean> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswersBean> answers) {
        this.answers = answers;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public static class AnswersBean {
        /**
         * answer_id : 114
         * answer_content : 疤痕脱发指头部由于外伤、疾病造成皮肤受损，毛囊生长部位不能够自然生长，疤痕部位毛发的生长也就因此受到一定的影响而形成的疤痕脱发。 后泛指其他部位因疤痕而不能够生长毛发的，也统称为疤痕脱发。
         * doctor_id : 111503
         * doctor_name : 魏德平
         * doctor_head : http://58.213.14.195:8081/upload/doctor/head/chengdu/weideping.png
         * position : 医师
         * create_time : 2018-09-27 10:50
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
