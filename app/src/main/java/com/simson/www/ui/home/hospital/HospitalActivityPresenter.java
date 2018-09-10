package com.simson.www.ui.home.hospital;


import com.simson.www.net.bean.home.HomeBannerBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.impl.HomeModel;
import com.simson.www.ui.core.model.impl.HospitalActivityModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.home.HomeContract;

public class HospitalActivityPresenter extends BasePresenter<HospitalActivityContract.View> implements HospitalActivityContract.Presenter {
    private HospitalActivityModel mModel;
    private HospitalActivityContract.View mView;

    HospitalActivityPresenter() {
        this.mModel = new HospitalActivityModel();
    }

    @Override
    public void getBanner() {
        mView = getView();
        RxObserver<HomeBannerBean> mHomeRxPageListObserver = new RxObserver<HomeBannerBean>(this) {

            @Override
            public void onSuccess(HomeBannerBean mData) {
                mView.setBannerData(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };
        mModel.getHomeBannerData(mHomeRxPageListObserver);
        addDisposable(mHomeRxPageListObserver);
    }

}
