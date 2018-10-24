package com.simson.www.ui.community.expert.save;

import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;


public interface NewQuestionContract {
    interface Presenter {

        void questions();
    }

    interface View extends IView {
        int is_display();

        String content();

        List<String> pictures();

        void questions(BaseBean bean);

    }
}
