package com.simson.www.ui.main;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.simson.www.R;
import com.simson.www.application.AppContext;
import com.simson.www.common.Const;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.CommunityFragment;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.home.HomeFragment;
import com.simson.www.ui.hospital.HospitalFragment;
import com.simson.www.ui.mine.MineFragment;
import com.simson.www.ui.shop.ShopFragment;
import com.simson.www.utils.SPUtils;
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
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
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
        //设置Home旋转开关按钮
        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_opens,
                R.string.navigation_drawer_close);
        mToggle.syncState();
        mDrawerLayout.addDrawerListener(mToggle);
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);
        //侧滑菜单
        initNavigationHeaderView();

//        TextView textView = helper.getView(R.id.tv_original);
//        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        initButton();
        initFragment();
    }

    private void initNavigationHeaderView() {
//        View mHeaderView = mNavigationView.getHeaderView(0);
//        mAvatarView = (ImageView) mHeaderView.findViewById(R.id.img_avatar);
//        mNameView = (TextView) mHeaderView.findViewById(R.id.tv_name);
    }

    //设置侧滑item click
    private NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {
//            case R.id.menu_favorite_article: {
//                if (!UserInfoManager.isLogin()) {
//                    startActivity(new Intent(MainActivity.this, LogonActivity.class));
//                } else {
//                    startActivity(new Intent(MainActivity.this, CollectArticleActivity.class));
//                }
//            }
//            break;
//            case R.id.menu_about:
//                startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
//                break;
//            case R.id.menu_exit:
//                exitToLogin();
//                break;
        }
        return true;
    };
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

    public void drawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawers();
        }else {
            mDrawerLayout.openDrawer(GravityCompat.START);
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

            if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
                mDrawerLayout.closeDrawer(Gravity.START);
                return true;
            }

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
