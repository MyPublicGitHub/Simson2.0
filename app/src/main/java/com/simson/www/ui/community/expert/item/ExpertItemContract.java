package com.simson.www.ui.community.expert.item;

import com.simson.www.net.bean.community.QuestionsBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;


public interface ExpertItemContract {
    interface Presenter {
        void getQuestionsList();
    }

    interface View extends IView {
        int getPage();

        String getType();

        void goToLogin();

        void showQuestionsList(List<QuestionsBean> bean);

    }
}
