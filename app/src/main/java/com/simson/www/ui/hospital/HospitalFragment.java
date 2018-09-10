package com.simson.www.ui.hospital;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simson.www.R;
import com.simson.www.ui.adapter.HospitalFragmentAdapter;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.core.presenter.BasePresenter;

public class HospitalFragment extends BasePresenterFragment {

    HospitalFragmentAdapter adapter;
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void getBundle(Bundle bundle) {

    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hospital;
    }
}
