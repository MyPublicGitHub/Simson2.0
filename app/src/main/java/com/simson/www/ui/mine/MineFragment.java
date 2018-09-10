package com.simson.www.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.simson.www.R;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.ui.main.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 首页文章
 * author:
 * date: 2018/2/12
 */

public class MineFragment extends BasePresenterFragment<MinePresenter, MineContract.IMineView> implements MineContract.IMineView {


    @BindView(R.id.iv_setting)
    ImageView ivSetting;

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter();
    }


    @Override
    protected void initViews(View view) {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void getBundle(Bundle bundle) {

    }

    @OnClick({R.id.iv_setting, R.id.iv_header})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.iv_header:
                break;
        }
    }
}
