package com.simson.www.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.simson.www.R;
import com.simson.www.inter.OnHomeListItemClickListener;
import com.simson.www.net.bean.home.BroadcastsBean;
import com.simson.www.net.bean.home.HomeDataBean;
import com.simson.www.ui.adapter.BannerAdapter;
import com.simson.www.ui.adapter.BaseListAdapter;
import com.simson.www.ui.adapter.HomeListAdapter;
import com.simson.www.ui.base.BaseAbListFragment;
import com.simson.www.ui.base.BasePresenterFragment;
import com.simson.www.utils.ToastUtils;
import com.simson.www.widget.BannerViewPager;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页文章
 * author:
 * date: 2018/2/12
 */

public class MineFragment extends BasePresenterFragment<MinePresenter, MineContract.IMineView> implements MineContract.IMineView {


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

}
