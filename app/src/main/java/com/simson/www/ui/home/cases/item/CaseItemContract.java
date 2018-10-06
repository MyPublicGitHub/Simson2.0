package com.simson.www.ui.home.cases.item;

import com.simson.www.net.bean.mine.CaseBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;

public interface CaseItemContract {
    interface Presenter {

        void getCase();

    }

    interface View extends IView {
        String getPage();
        String getHospitalId();
        String getItemTypeId();

        void showCase(List<CaseBean> bean);
    }
}
