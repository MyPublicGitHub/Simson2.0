package com.simson.www.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.simson.www.R;
import com.simson.www.application.AppContext;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.CommunityFragment;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.home.HomeFragment;
import com.simson.www.ui.hospital.HospitalFragment;
import com.simson.www.ui.mine.MineFragment;
import com.simson.www.ui.shop.ShopFragment;
import com.simson.www.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BasePresenterActivity {

    @BindView(R.id.btn_main)
    Button btnMain;
    @BindView(R.id.btn_community)
    Button btnCommunity;
    @BindView(R.id.btn_hospital)
    Button btnHospital;
    @BindView(R.id.btn_shop)
    Button btnShop;
    @BindView(R.id.btn_mine)
    Button btnMine;
    public ArrayList<Fragment> mFragments;
    private Button[] btns;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        initButton();
        initFragment();
    }

    private int currentPosition;
    private int index;

    private void initButton() {
        btns = new Button[5];
        btns[0] = btnMain;
        btns[1] = btnCommunity;
        btns[2] = btnHospital;
        btns[3] = btnShop;
        btns[4] = btnMine;
        btns[0].setSelected(true);

        for (int i = 0; i < btns.length; i++) {
            if (i != currentPosition) {
                btns[i].setScaleX(0.9f);
                btns[i].setScaleY(0.9f);
            }
        }
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new CommunityFragment());
        mFragments.add(new HospitalFragment());
        mFragments.add(new ShopFragment());
        mFragments.add(new MineFragment());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, mFragments.get(0)).show(mFragments.get(0)).commitAllowingStateLoss();
    }

    /**
     * 切换显示当前Fragment
     *
     * @param index
     */
    private void showCurrentFragment(int index) {
        if (currentPosition != index) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.hide(mFragments.get(currentPosition));
            if (!mFragments.get(index).isAdded()) {
                ft.add(R.id.container, mFragments.get(index));
            }
            ft.show(mFragments.get(index)).commitAllowingStateLoss();
            btns[currentPosition].setSelected(false);
            btns[index].setSelected(true);
            scaleView();
            currentPosition = index;
        }
    }

    /**
     * view放大缩小
     */
    private void scaleView() {
        btns[currentPosition].animate().scaleX(0.9f).scaleY(0.9f)
                .setDuration(150).start();
        btns[index].animate().scaleX(1.0f).scaleY(1.0f)
                .setDuration(150).start();
    }

    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if (System.currentTimeMillis() - mExitTime < 2000) {
                finish();
            } else {
                mExitTime = System.currentTimeMillis();
                ToastUtils.showToast(AppContext.getContext(), "请再按一次退出程序");
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.iv_hospital, R.id.btn_main, R.id.btn_community, R.id.btn_hospital, R.id.btn_shop, R.id.btn_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_main:
                index = 0;
                break;
            case R.id.btn_community:
                index = 1;
                break;
            case R.id.iv_hospital:
            case R.id.btn_hospital:
                index = 2;
                break;
            case R.id.btn_shop:
                index = 3;
                break;
            case R.id.btn_mine:
                index = 4;
                break;
        }
        showCurrentFragment(index);
    }

}
