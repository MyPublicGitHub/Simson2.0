package com.simson.www.ui.community.expert.detail;

import com.simson.www.net.bean.community.QuestionsDetailBean;
import com.simson.www.ui.core.view.IView;


public interface QuestionDetailContract {
    interface Presenter {

        void questionsDetail();
    }

    interface View extends IView {

        String questionsId();

        void questionsDetail(QuestionsDetailBean bean);

    }
}
