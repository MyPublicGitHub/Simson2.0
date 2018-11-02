package com.simson.www.ui.home.test;

import com.simson.www.net.bean.home.AlopeciaTestBean;
import com.simson.www.net.bean.home.QuestionBean;
import com.simson.www.ui.core.view.IView;


public interface TestContract {
    interface Presenter {
        void getQuestion();

        void saveAlopeciaTesting();

    }

    interface View extends IView {
        void getQuestion(QuestionBean bean);

        void saveAlopeciaTesting(AlopeciaTestBean bean);

        String correspondingId();

        String optionId();

        String deviceToken();

        String[] questions();

        String[] options();

        String[] pictures();

    }
}
