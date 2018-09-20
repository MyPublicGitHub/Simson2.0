package com.simson.www.ui.community.diary.item;

import com.simson.www.net.bean.community.DiaryBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;


public interface DiaryItemContract {
    interface Presenter {
        void getDiaryList();
    }

    interface View extends IView {
        int getPage();

        String getType();
        void goToLogin();

        void showDiaryList(List<DiaryBean> bean);

    }
}
