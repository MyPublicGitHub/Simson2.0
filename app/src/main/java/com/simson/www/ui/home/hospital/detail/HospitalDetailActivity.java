package com.simson.www.ui.home.hospital.detail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.adapter.HDACaseDiaryAdapter;
import com.simson.www.ui.adapter.HDAExpertAdapter;
import com.simson.www.ui.adapter.HDAHospitalInfoAdapter;
import com.simson.www.ui.adapter.HDAInstrumentAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HospitalDetailActivity extends BasePresenterActivity {

    private HDAExpertAdapter mHDAExpertAdapter;
    private HDAInstrumentAdapter mHDAInstrumentAdapter;
    private HDACaseDiaryAdapter mHDACaseDiaryAdapter;
    private HDAHospitalInfoAdapter mHDAHospitalInfoAdapter;

    @BindView(R.id.rv_experts)
    RecyclerView rvExperts;
    @BindView(R.id.rv_instrument)
    RecyclerView rvInstrument;
    @BindView(R.id.rv_case_diary)
    RecyclerView rvCaseDiary;
    @BindView(R.id.rv_hospital_info)
    RecyclerView rvHospitalInfo;

    @Override
    protected void initViews() {
        List<BaseBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new BaseBean());
        }
        mHDAExpertAdapter = new HDAExpertAdapter(list);
        rvExperts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvExperts.setAdapter(mHDAExpertAdapter);

        mHDAInstrumentAdapter = new HDAInstrumentAdapter(list);
        rvInstrument.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvInstrument.setAdapter(mHDAInstrumentAdapter);

        mHDACaseDiaryAdapter = new HDACaseDiaryAdapter(list);
        rvCaseDiary.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvCaseDiary.setAdapter(mHDACaseDiaryAdapter);

        mHDAHospitalInfoAdapter = new HDAHospitalInfoAdapter(list);
        rvHospitalInfo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvHospitalInfo.setAdapter(mHDAHospitalInfoAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hospital_detail;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("医院详情");
        return true;
    }
}
