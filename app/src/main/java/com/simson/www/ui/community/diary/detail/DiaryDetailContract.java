package com.simson.www.ui.community.diary.detail;

import com.simson.www.net.bean.community.DiaryDetailAppendBean;
import com.simson.www.net.bean.community.DiaryDetailBean;
import com.simson.www.net.bean.community.DiaryDetailRecommendBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;


public interface DiaryDetailContract {
    interface Presenter {
        void getDiaryDetail();
        void getDiaryDetailAppend();
        void getDiaryDetailRecommend();
    }

    interface View extends IView {

        String getId();
        String getPX();
        String getItemTypeId();//itemTypeId：日记类型：日记类型

        void showDiaryDetail(DiaryDetailBean bean);
        void showDiaryDetailAppend(List<DiaryDetailAppendBean> bean);
        void showDiaryDetailRecommend(List<DiaryDetailRecommendBean> bean);

    }
}
