package com.simson.www.ui.shop.detail.technology;

import com.simson.www.net.bean.home.TechnologyBean;
import com.simson.www.ui.core.view.IView;

import java.util.List;


public interface TechnologyContract {
    interface Presenter {

        void getPlantingTechnology();

    }

    interface View extends IView {


        String hospitalId();

        void getPlantingTechnology(List<TechnologyBean> bean);

    }
}
