package com.simson.www.ui.community;

import android.os.Bundle;
import android.view.View;

import com.simson.www.R;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.core.presenter.BasePresenter;


public class DiaryFragment extends BasePresenterFragment {

    MyDiaryRecyclerViewAdapter mAdapter;

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
        return R.layout.fragment_diary;
    }
}
