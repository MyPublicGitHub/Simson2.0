package com.simson.www.ui.mine.test.save;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.simson.www.common.Const;
import com.simson.www.net.NetConfig;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.mine.HospitalTestBean;
import com.simson.www.net.callback.RxBaseObserver;
import com.simson.www.net.callback.RxObserver;
import com.simson.www.ui.core.model.NewHospitalTestModel;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.SPUtils;
import com.simson.www.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewHospitalTestPresenter extends BasePresenter<NewHospitalTestContract.View> implements NewHospitalTestContract.Presenter {
    private NewHospitalTestModel mModel;
    private NewHospitalTestContract.View mView;

    public NewHospitalTestPresenter() {
        this.mModel = new NewHospitalTestModel();
    }


    @Override
    public void saveHospitalTesting() {
        mView = getView();
        if (TextUtils.isEmpty(mView.hospitalId())){
            ToastUtils.showToast("请选择医院");
            return;
        }
        if (TextUtils.isEmpty(mView.subscribeTime())){
            ToastUtils.showToast("请选择到院时间");
            return;
        }
        if (TextUtils.isEmpty(mView.mobile())){
            ToastUtils.showToast("请输入手机号码");
            return;
        }
        RxBaseObserver<BaseBean> observer = new RxBaseObserver<BaseBean>(this) {

            @Override
            public void onNext(BaseBean bean) {
                //请求成功
                if (bean.result == NetConfig.REQUEST_SUCCESS) {
                    mView.saveHospitalTesting(bean);
                } else {
                    //失败
                    mView.showFail(bean.message);
                }
            }
        };
        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtils.getStringDate());
        map.put("customerId", (String) SPUtils.get(Const.USER_INFO.CUSTOMER_ID, ""));//当前登录人
        map.put("hospitalId", mView.hospitalId());//医院id必填
        map.put("mobile", mView.mobile());//手机号码必填
        map.put("subscribeTime", mView.subscribeTime());//到院时间必填
        map.put("customerName", mView.customerName());//名称
        map.put("customerSex", mView.customerSex());//性别
        map.put("customerAge", mView.customerAge());//年龄
        map.put("remark", mView.remark());//备注
        String json = new Gson().toJson(map);
        mModel.saveHospitalTesting(json, observer);
        addDisposable(observer);
    }

}
