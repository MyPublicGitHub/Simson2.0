package com.simson.www.net.bean.home;

import java.util.List;

public class QuestionBean {

    /**
     * questionnaire_id : 110
     * title : 您的性别？
     * the_first_few : 1
     * options : [{"option_id":"1000","questionnaire_option":"男","corresponding_id":"120","is_picture":0,"option_picture":""},{"option_id":"1001","questionnaire_option":"女","corresponding_id":"120","is_picture":0,"option_picture":""}]
     */

    private String questionnaire_id;
    public boolean isSelect;
    private String title;
    private int the_first_few;
    private List<OptionsBean> options;

    public String getQuestionnaire_id() {
        return questionnaire_id;
    }

    public void setQuestionnaire_id(String questionnaire_id) {
        this.questionnaire_id = questionnaire_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getThe_first_few() {
        return the_first_few;
    }

    public void setThe_first_few(int the_first_few) {
        this.the_first_few = the_first_few;
    }

    public List<OptionsBean> getOptions() {
        return options;
    }

    public void setOptions(List<OptionsBean> options) {
        this.options = options;
    }

    public static class OptionsBean {
        /**
         * option_id : 1000
         * questionnaire_option : 男
         * questionnaire_id : 男
         * corresponding_id : 120
         * is_picture : 0
         * option_picture :
         */

        public boolean isChech;
        private String option_id;

        public String getQuestionnaire_id() {
            return questionnaire_id;
        }

        public void setQuestionnaire_id(String questionnaire_id) {
            this.questionnaire_id = questionnaire_id;
        }

        private String questionnaire_id;
        private String questionnaire_option;
        private String corresponding_id;
        private int is_picture;
        private String option_picture;

        public String getOption_id() {
            return option_id;
        }

        public void setOption_id(String option_id) {
            this.option_id = option_id;
        }

        public String getQuestionnaire_option() {
            return questionnaire_option;
        }

        public void setQuestionnaire_option(String questionnaire_option) {
            this.questionnaire_option = questionnaire_option;
        }

        public String getCorresponding_id() {
            return corresponding_id;
        }

        public void setCorresponding_id(String corresponding_id) {
            this.corresponding_id = corresponding_id;
        }

        public int getIs_picture() {
            return is_picture;
        }

        public void setIs_picture(int is_picture) {
            this.is_picture = is_picture;
        }

        public String getOption_picture() {
            return option_picture;
        }

        public void setOption_picture(String option_picture) {
            this.option_picture = option_picture;
        }
    }
}
