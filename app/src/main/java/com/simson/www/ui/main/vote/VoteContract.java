package com.simson.www.ui.main.vote;

import com.simson.www.net.bean.main.ProgramBean;
import com.simson.www.net.bean.main.VoteBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface VoteContract {
    interface Presenter {

        void program();

        void vote();


    }

    interface View extends IView {

        String phone();

        String id();

        void program(List<ProgramBean> bean);

        void vote(VoteBean bean);

    }
}
