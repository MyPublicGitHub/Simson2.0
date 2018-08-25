package com.simson.www.ui.home;

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
import com.simson.www.utils.ToastUtils;
import com.simson.www.widget.BannerViewPager;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页文章
 * author:
 * date: 2018/2/12
 */

public class HomeFragment extends BaseAbListFragment<HomePresenter, HomeContract.IHomeView, HomeDataBean> implements HomeContract.IHomeView, OnHomeListItemClickListener {
    private int position;
    private List<BroadcastsBean> mBannerList = new ArrayList<>();
    private BannerViewPager mViewPager;
    private BannerAdapter mBannerAdapter;

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected boolean isCanLoadMore() {
        return true;
    }

    //初始化HeaderView
    @Override
    protected View initHeaderView() {
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.main_header_banner, mRecyclerView, false);
        mViewPager = (BannerViewPager) headerView.findViewById(R.id.viewPager);
        return headerView;
    }


    //设置Banner选中item
    private void setCurrentItem(final int position) {
        mViewPager.setCurrentItem(position, false);
    }

    //加载列表数据
    @Override
    protected void loadDatas() {
        mPresenter.getHomeList();
    }

    @Override
    protected BaseListAdapter getListAdapter() {
        return new HomeListAdapter(this);
    }

    //Banner数据
    @Override
    public void setBannerData(List<BroadcastsBean> banner) {
        mBannerList.clear();
        mBannerList.addAll(banner);
        ToastUtils.showToast(banner.size()+"个轮播图");
    }

    //列表数据
    @Override
    public void setData(List<HomeDataBean> data) {
        mListData.addAll(data);
    }

    //显示内容
    @Override
    public void showContent() {
        notifyDatas();
        super.showContent();
    }

    //刷新所有数据
    public void notifyDatas() {
        if (mBannerAdapter == null) {
            mBannerAdapter = new BannerAdapter(mBannerList);
            mViewPager.setAdapter(mBannerAdapter);
            //设置预加载两个页面
            mViewPager.setOffscreenPageLimit(2);
            setCurrentItem(1000 * mBannerList.size());
        }
        mBannerAdapter.notifyDatas(mBannerList);
    }


    //刷新单条Item
    private void notifyItemData(boolean isCollect, String result) {
        //mListData.get(position).setCollect(isCollect);
        position++;
        mListAdapter.notifyItemDataChanged(position, mRecyclerView);
        ToastUtils.showToast(getActivity(), result);
    }


    //进入详情
    @Override
    public void onItemClick(HomeDataBean bean) {
        ToastUtils.showToast("条目类型："+bean.getData_status());
    }


    @Override
    public void onResume() {
        super.onResume();
        mViewPager.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewPager.stop();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            if (mViewPager!=null)
            mViewPager.stop();
        } else {
            if (mViewPager!=null)
            mViewPager.start();
        }
    }

    @Override
    protected void receiveEvent(Object object) {

    }

    @Override
    protected String registerEvent() {
        return null;
    }

}
