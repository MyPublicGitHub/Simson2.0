package com.simson.www.ui.core.presenter;


import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.main.ItemTypeBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.CommonModel;
import com.simson.www.ui.core.view.IView;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonPresenter<V extends IView> extends BasePresenter<V> implements ICommonPresenter {
    private CommonModel mModel;

    public CommonPresenter() {
        this.mModel = new CommonModel();
    }


    @Override
    public void getItemType() {
        RxObserver<List<ItemTypeBean>> mObserver = new RxObserver<List<ItemTypeBean>>(this) {

            @Override
            protected void onSuccess(List<ItemTypeBean> data) {
                view.setItemType(data);
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                view.showFail(errorMsg);
            }

        };

        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        String json = new Gson().toJson(map);
        mModel.getItemType(json,mObserver);
        addDisposable(mObserver);
    }

    @Override
    public void collect(String bizId,String method,String type) {
        RxBaseObserver<BaseBean> mObserver = new RxBaseObserver<BaseBean>(this) {

            @Override
            public void onNext(BaseBean bean) {
                //请求成功
                if (bean.result == NetConfig.REQUEST_SUCCESS) {
                    view.collect(bean);
                } else {
                    //失败
                    view.showFail(bean.message);
                }
            }

        };
        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("bizId", bizId);//bizId：收藏的业务id
        map.put("method", method);//method：方法 save或者delete
        map.put("type", type);//type： 1：日记；2科普；3问答；4商品
        String json = new Gson().toJson(map);
        mModel.collect(json,mObserver);
        addDisposable(mObserver);
    }

    @Override
    public void follow(String followCustomerId, String method, String type) {
        RxBaseObserver<BaseBean> mObserver = new RxBaseObserver<BaseBean>(this) {
            @Override
            public void onNext(BaseBean bean) {
                //请求成功
                if (bean.result == NetConfig.REQUEST_SUCCESS) {
                    view.follow(bean);
                } else {
                    //失败
                    view.showFail(bean.message);
                }
            }

        };
        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("followCustomerId", followCustomerId);//followCustomerId：关注的业务id
        map.put("method", method);//method：方法 save或者delete
        map.put("type", type);//type：  1：医院；2医生；3顾客；商品
        String json = new Gson().toJson(map);
        mModel.follow(json,mObserver);
        addDisposable(mObserver);
    }

    @Override
    public void praise(String bizId, String method, String type) {
        RxBaseObserver<BaseBean> mObserver = new RxBaseObserver<BaseBean>(this) {
            @Override
            public void onNext(BaseBean bean) {
                //请求成功
                if (bean.result == NetConfig.REQUEST_SUCCESS) {
                    view.praise(bean);
                } else {
                    //失败
                    view.showFail(bean.message);
                }
            }


        };
        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("bizId", bizId);//bizId：收藏的业务id
        map.put("method", method);//method：方法 save或者delete
        map.put("type", type);//type： 1：日记；2科普；3问答；4商品
        String json = new Gson().toJson(map);
        mModel.collect(json,mObserver);
        addDisposable(mObserver);
    }

}
