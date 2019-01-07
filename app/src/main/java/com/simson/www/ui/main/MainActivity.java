package com.simson.www.ui.main;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.application.AppContext;
import com.simson.www.common.Const;
import com.simson.www.net.bean.main.NewestRedEnvelopeBean;
import com.simson.www.net.bean.main.ReceiveRedEnvelopeBean;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.CommunityFragment;
import com.simson.www.ui.home.HomeFragment;
import com.simson.www.ui.hospital.HospitalFragment;
import com.simson.www.ui.main.login.LoginActivity;
import com.simson.www.ui.main.vote.VoteActivity;
import com.simson.www.ui.mine.MineFragment;
import com.simson.www.ui.shop.ShopFragment;
import com.simson.www.utils.DateUtils;
import com.simson.www.utils.GlideUtils;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.Rotate3dAnimation;
import com.simson.www.utils.SPUtils;
import com.simson.www.utils.ToastUtils;
import com.simson.www.widget.CommonPopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BasePresenterActivity<MainPresenter, MainContract.View> implements MainContract.View {

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
    @BindView(R.id.iv_red_envelope)
    ImageView ivRedEnvelope;
    @BindView(R.id.iv_vote)
    ImageView ivVote;
    public ArrayList<Fragment> mFragments;
    private Button[] btns;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorMain));
        }
        super.onCreate(bundle);

    }

    @Override
    protected void initViews() {
        initButton();
        initFragment();
        GlideUtils.with(R.mipmap.ic_main_red_packet, ivRedEnvelope);
        GlideUtils.with(R.mipmap.ic_main_vote, ivVote);
    }

    boolean isRun = true;

    @Override
    protected void initData() {
        init();
    }

    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRun) {
                    try {
                        Thread.sleep(10000);
                        Message msgMessage = new Message();
                        msgMessage.arg1 = 1;
                        handler.sendMessage(msgMessage);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Message msgMessage = new Message();
                    msgMessage.arg1 = 2;
                    handler.sendMessage(msgMessage);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (isRun && popupWindowCountDown != null && popupWindowCountDown.isShowing()) {
                        try {
                            Thread.sleep(5000);
                            Message msgMessage = new Message();
                            msgMessage.arg1 = 3;
                            handler.sendMessage(msgMessage);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();
        initVoteView();
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.arg1) {
                case 1:
                    initVoteView();
                    break;
                case 2:
                    initCountDown();
                    break;
                case 3:
                    if (popupWindowCountDown != null && popupWindowCountDown.isShowing()) {
                        popupWindowCountDown.dismiss();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public void redClick(View view) {
        if (TextUtils.isEmpty((String) SPUtils.get(Const.USER_INFO.CUSTOMER_MOBLE, ""))) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            mPresenter.newestRedEnvelope();
        }
    }

    public void voteClick(View view) {
        if (TextUtils.isEmpty((String) SPUtils.get(Const.USER_INFO.CUSTOMER_MOBLE, ""))) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, VoteActivity.class));
        }
    }

    CommonPopupWindow popupWindow, popupWindowFail;

    @Override
    public void newestRedEnvelope(NewestRedEnvelopeBean bean) {
        //crowd_no = AESUtils.decrypt(bean.getCrowd_no());
        //LogUtils.e(crowd_no);
        crowd_no = bean.getCrowd_no();
        crowd_name = bean.getCoupon_name();
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.pop_red_envelope) //设置PopupWindow布局
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) //设置宽高
                //.setAnimationStyle(R.style.AnimDown) //设置动画
                .setBackGroundLevel(0.5f) //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                .setViewOnclickListener((view1, layoutResId) -> {
                    TextView title = view1.findViewById(R.id.tv_title);
                    content = view1.findViewById(R.id.tv_content);
                    open = view1.findViewById(R.id.iv_open);
                    ImageView close = view1.findViewById(R.id.iv_close);
                    title.setText("" + crowd_name);
                    open.setOnClickListener(v -> {
                        float centerX = open.getWidth() / 2.0f;
                        float centerY = open.getHeight() / 2.0f;
                        float depthZ = 0f;
                        Rotate3dAnimation rotate3dAnimationX = new Rotate3dAnimation(0, 360,
                                centerX, centerY, depthZ, Rotate3dAnimation.ROTATE_Y_AXIS, true);
                        rotate3dAnimationX.setDuration(1000);
                        open.startAnimation(rotate3dAnimationX);
                        mPresenter.receiveRedEnvelope();
                    });
                    close.setOnClickListener(v -> {
                        popupWindow.dismiss();
                        popupWindow = null;
                    });
                })
                .setOutsideTouchable(false) //设置外部是否可点击 默认是true
                .create(); //开始构建
        popupWindow.showAtLocation(ivRedEnvelope, Gravity.TOP | Gravity.START, 0, 0);//弹出PopupWindow
    }

    @Override
    public void newestRedEnvelopeFail() {
        popupWindowFail = new CommonPopupWindow.Builder(this)
                .setView(R.layout.pop_red_envelope_fail) //设置PopupWindow布局
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) //设置宽高
                //.setAnimationStyle(R.style.AnimDown) //设置动画
                .setBackGroundLevel(0.5f) //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                .setViewOnclickListener((view1, layoutResId) -> {
                    ImageView close = view1.findViewById(R.id.iv_close);
                    close.setOnClickListener(v -> {
                        popupWindowFail.dismiss();
                    });
                })
                .setOutsideTouchable(true) //设置外部是否可点击 默认是true
                .create(); //开始构建
        popupWindowFail.showAtLocation(ivRedEnvelope, Gravity.TOP | Gravity.START, 0, 0);//弹出PopupWindow
    }

    @Override
    public void receiveRedEnvelope(ReceiveRedEnvelopeBean bean) {
        content.setText(bean.getMessage());
        open.setVisibility(View.GONE);
    }

    public void initVoteView() {
        String data = DateUtils.getDateTimeHHmm();
        if (DateUtils.yearMonthDayBetween("2019-01-22 18:00", data, "2019-01-23 08:00")) {
            if (index == 0) {
                ivRedEnvelope.setVisibility(View.VISIBLE);
                ivVote.setVisibility(View.VISIBLE);
            } else {
                ivRedEnvelope.setVisibility(View.GONE);
                ivVote.setVisibility(View.GONE);
            }
        } else {
            ivRedEnvelope.setVisibility(View.GONE);
            ivVote.setVisibility(View.GONE);
        }
    }

    //用来控制应用前后台切换的逻辑
    private boolean isCurrentRunningForeground = true;

    @Override
    protected void onResume() {
        super.onResume();
        if (Const.RED) {
            mPresenter.newestRedEnvelope();
            Const.RED = false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        isRun = true;
        if (!isCurrentRunningForeground) {
            LogUtils.e("1111111111111111111111111111切到前台 MainActivity process");
            initCountDown();
            initVoteView();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRun = false;
        isCurrentRunningForeground = isRunningForeground();
        if (!isCurrentRunningForeground) {
            LogUtils.d("1111111111111111111切到后台 activity process");
        }
    }

    public boolean isRunningForeground() {
        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessInfos = activityManager.getRunningAppProcesses();
        // 枚举进程
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfos) {
            if (appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                if (appProcessInfo.processName.equals(this.getApplicationInfo().processName)) {
                    LogUtils.e("EntryActivity isRunningForeGround");
                    return true;
                }
            }
        }
        LogUtils.e("EntryActivity isRunningBackGround");
        return false;
    }

    CommonPopupWindow popupWindowCountDown;

    public void initCountDown() {
        if (popupWindowCountDown != null && popupWindowCountDown.isShowing()) {
            popupWindowCountDown.dismiss();
        }
        ivRedEnvelope.setVisibility(View.GONE);
        ivVote.setVisibility(View.GONE);
        String data = DateUtils.getDateTimeHHmm();
        if (DateUtils.yearMonthDayBetween("2019-01-17 00:00", data, "2019-01-21 23:59")) {//显示倒计时
            popupWindowCountDown = new CommonPopupWindow.Builder(this)
                    .setView(R.layout.pop_count_down) //设置PopupWindow布局
                    .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) //设置宽高
                    //.setAnimationStyle(R.style.AnimDown) //设置动画
                    .setBackGroundLevel(0.5f) //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                    .setViewOnclickListener((view1, layoutResId) -> {
                        ImageView close = view1.findViewById(R.id.iv_close);
                        ImageView image = view1.findViewById(R.id.iv_image);
                        String datas = DateUtils.getDateTime();
                        if (datas.equals("2019-01-17")) {
                            image.setImageResource(R.mipmap.ic_main_count_down_5);
                        } else if (datas.equals("2019-01-18")) {
                            image.setImageResource(R.mipmap.ic_main_count_down_4);
                        } else if (datas.equals("2019-01-19")) {
                            image.setImageResource(R.mipmap.ic_main_count_down_3);
                        } else if (datas.equals("2019-01-20")) {
                            image.setImageResource(R.mipmap.ic_main_count_down_2);
                        } else if (datas.equals("2019-01-21")) {
                            image.setImageResource(R.mipmap.ic_main_count_down_1);
                        } else {
                            image.setImageResource(R.mipmap.ic_main_count_down_now);
                        }
                        close.setOnClickListener(v -> {
                            popupWindowCountDown.dismiss();
                        });
                    })
                    .setOutsideTouchable(true) //设置外部是否可点击 默认是true
                    .create(); //开始构建
            popupWindowCountDown.showAtLocation(ivRedEnvelope, Gravity.TOP | Gravity.START, 0, 0);//弹出PopupWindow
            ivRedEnvelope.setVisibility(View.GONE);
            ivVote.setVisibility(View.GONE);
        } else if (DateUtils.yearMonthDayBetween("2019-01-22 00:00", data, "2019-01-22 17:59")) {//显示即将开始
            popupWindowCountDown = new CommonPopupWindow.Builder(this)
                    .setView(R.layout.pop_count_down) //设置PopupWindow布局
                    .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) //设置宽高
                    //.setAnimationStyle(R.style.AnimDown) //设置动画
                    .setBackGroundLevel(0.5f) //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                    .setViewOnclickListener((view1, layoutResId) -> {
                        ImageView close = view1.findViewById(R.id.iv_close);
                        close.setOnClickListener(v -> {
                            popupWindowCountDown.dismiss();
                        });
                    })
                    .setOutsideTouchable(true) //设置外部是否可点击 默认是true
                    .create(); //开始构建
            popupWindowCountDown.showAtLocation(ivRedEnvelope, Gravity.TOP | Gravity.START, 0, 0);//弹出PopupWindow
            ivRedEnvelope.setVisibility(View.GONE);
            ivVote.setVisibility(View.GONE);
        } else if (DateUtils.yearMonthDayBetween("2019-01-22 18:00", data, "2019-01-23 08:00")) {//显示红包按钮
            if (index == 0) {
                ivRedEnvelope.setVisibility(View.VISIBLE);
                ivVote.setVisibility(View.VISIBLE);
            } else {
                ivRedEnvelope.setVisibility(View.GONE);
                ivVote.setVisibility(View.GONE);
            }
        } else {//隐藏按钮
            ivRedEnvelope.setVisibility(View.GONE);
            ivVote.setVisibility(View.GONE);
        }

    }

    TextView content;
    ImageView open;
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
        initVoteView();
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
                //启动一个意图,回到桌面
                Intent backHome = new Intent(Intent.ACTION_MAIN);
                backHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                backHome.addCategory(Intent.CATEGORY_HOME);
                startActivity(backHome);
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

    String crowd_no, crowd_name;

    @Override
    public String crowd_no() {
        return crowd_no;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

}
