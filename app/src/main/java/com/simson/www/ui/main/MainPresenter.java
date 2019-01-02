package com.simson.www.ui.main;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.main.NewestRedEnvelopeBean;
import com.simson.www.net.bean.main.ReceiveRedEnvelopeBean;
import com.simson.www.net.callback.RxRedObserver;
import com.simson.www.ui.core.model.MainModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;


public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    private MainModel mModel;
    private MainContract.View mView;

    MainPresenter() {
        this.mModel = new MainModel();
    }

    @Override
    public void newestRedEnvelope() {
        mView = getView();
        RxRedObserver<NewestRedEnvelopeBean> observer = new RxRedObserver<NewestRedEnvelopeBean>(this) {
            @Override
            public void onNext(NewestRedEnvelopeBean bean) {
                //请求成功
                if (bean.getResult() == NetConfig.REQUEST_SUCCESS) {
                    mView.newestRedEnvelope(bean);
                } else {
                    //失败
                    mView.newestRedEnvelopeFail();
                }
            }

            @Override
            public void onError(Throwable e) {
                mView.hideLoading();
                mView.newestRedEnvelopeFail();
            }
        };
        String url = "https://images.maofa.com/simson_admin/redEnvelopes/newestRedEnvelope";
        mModel.newestRedEnvelope(url, observer);
        addDisposable(observer);
    }

    @Override
    public void receiveRedEnvelope() {
        mView = getView();
        RxRedObserver<ReceiveRedEnvelopeBean> observer = new RxRedObserver<ReceiveRedEnvelopeBean>(this) {
            @Override
            public void onNext(ReceiveRedEnvelopeBean bean) {
                //请求成功
                mView.receiveRedEnvelope(bean);
                /*if (bean.getResult() == NetConfig.REQUEST_SUCCESS) {
                    mView.receiveRedEnvelope(bean);
                } else {
                    //失败
                    mView.showFail(bean.getMessage());
                }*/
            }

            @Override
            protected void onStart() {
            }
        };
        Map map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("crowd_no", mView.crowd_no());
        map.put("login_id", SPUtils.get(Const.USER_INFO.CUSTOMER_MOBLE, ""));
        String json = new Gson().toJson(map);
        String url = "https://images.maofa.com/simson_admin/redEnvelopes/receiveRedEnvelope";
        mModel.receiveRedEnvelope(url, json, observer);
        addDisposable(observer);
    }
}
