package com.simson.www.ui.home;


import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.impl.HomeModel;
import com.simson.www.ui.core.presenter.CommonItemTypePresenter;

/**
 * Home Presenter
 * author:
 * date: 2018/2/11
 */

public class HomePresenter extends CommonItemTypePresenter<HomeContract.IHomeView> implements HomeContract.IHomePresenter {
    private HomeModel mHomeModel;
    private HomeContract.IHomeView homeView;

    HomePresenter() {
        this.mHomeModel = new HomeModel();
    }

    /**
     * 获取首页Bannder
     */
    @Override
    public void getBanner() {
        homeView = getView();
        RxObserver<HomeBannerBean> mHomeRxPageListObserver = new RxObserver<HomeBannerBean>(this) {

            @Override
            public void onSuccess(HomeBannerBean mData) {
                homeView.setBannerData(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                homeView.showFail(errorMsg);
            }
        };
        mHomeModel.getHomeBannerData(mHomeRxPageListObserver);
        addDisposable(mHomeRxPageListObserver);
    }

}
