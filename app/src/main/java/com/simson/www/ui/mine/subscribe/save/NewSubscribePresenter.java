package com.simson.www.ui.mine.subscribe.save;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.NewSubscribeModel;
import com.simson.www.ui.core.presenter.CommonPresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;
import com.simson.www.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;


public class NewSubscribePresenter extends CommonPresenter<NewSubscribeContract.View> implements NewSubscribeContract.Presenter {
    private NewSubscribeModel mModel;
    private NewSubscribeContract.View mView;

    public NewSubscribePresenter() {
        this.mModel = new NewSubscribeModel();
    }

    @Override
    public void saveSubscribe() {
        mView = getView();
        if (TextUtils.isEmpty(mView.subscribeType())){
            ToastUtils.showToast("请选择预约类型");
            return;
        }
        if (TextUtils.isEmpty(mView.hospitalId())){
            ToastUtils.showToast("请选择预约医院");
            return;
        }
        RxObserver<BaseBean> observer = new RxObserver<BaseBean>(this) {

            @Override
            public void onSuccess(BaseBean mData) {
                mView.saveSubscribe(mData);
            }

            @Override
            public void onFail(int code, String errorMsg) {
                mView.showFail(errorMsg);
            }
        };
        Map<String, String> map = new HashMap<>();
        map.put("hospitalId", mView.hospitalId());//医院id必填
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));
        map.put("subscribeType", mView.subscribeType());//预约类型；1预约项目；2脱发检测预约 必填
        map.put("itemTypeId", mView.itemTypeId());//项目id必填；脱发检测0
        map.put("mobile", mView.mobile());//手机号必填
        map.put("subscribeDate", mView.subscribeDate());//到院日期必填
        map.put("subscribeTime", mView.subscribeTime());//到院时间必填
        map.put("accompanyFriends", mView.accompanyFriends());//有无好友陪同，多个逗号隔开
        map.put("isCar", mView.isCar());//是否开车，0否；1是
        map.put("isSpecialCar", mView.isSpecialCar());//是否预约专车，0否；1是
        map.put("detailedAddress", mView.detailedAddress());//详细地址
        map.put("remark", mView.remark());//备注
        map.put("timestamp", DateUtils.getStringDate());
        String json = new Gson().toJson(map);
        mModel.saveSubscribe(json,observer);
        addDisposable(observer);
    }

}
