package com.simson.www.ui.mine;


import com.simson.www.net.bean.home.HomeDataBean;
import com.simson.www.net.bean.home.HomeHeaderBean;
import com.simson.www.net.callback.RxConsumer;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.impl.HomeModel;
import com.simson.www.ui.core.presenter.BasePresenter;

import java.util.List;

/**
 * Home Presenter
 * author:
 * date: 2018/2/11
 */

public class MinePresenter extends BasePresenter<MineContract.IMineView> implements MineContract.IMinePresenter {
    private HomeModel mHomeModel;
    private MineContract.IMineView homeView;

    MinePresenter() {
        this.mHomeModel = new HomeModel();
    }

    /**
     */
//    @Override
//    public void getHomeList() {
//        homeView = getView();
//        RxObserver<List<HomeDataBean>> mHomeRxPageListObserver = new RxObserver<List<HomeDataBean>>(this) {
//
//            @Override
//            public void onSuccess(List<HomeDataBean> mData) {
//                homeView.setData(mData);
//                if (homeView.getData().size() == 0)
//                    homeView.showEmpty();
//                else
//                    homeView.showContent();
//            }
//
//            @Override
//            public void onFail(int code, String errorMsg) {
//                homeView.showFail(errorMsg);
//            }
//        };
//        mHomeModel.getHomeData(homeView.getPage(), new RxConsumer<HomeHeaderBean>() {
//            @Override
//            protected void onFail(String errorMsg) {
//                homeView.showFail(errorMsg);
//            }
//
//            @Override
//            protected void onSuccess(HomeHeaderBean data) {
//                homeView.setBannerData(data.getBroadcasts());
//            }
//        }, mHomeRxPageListObserver);
//
//        addDisposable(mHomeRxPageListObserver);
//    }

}
