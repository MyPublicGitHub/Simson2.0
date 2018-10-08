package com.simson.www.ui.mine.sign;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.net.bean.mine.SignBean;
import com.simson.www.net.bean.mine.SignPageBean;
import com.simson.www.ui.adapter.SignInAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.mine.integral.detail.IntegralDetailActivity;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class SignActivity extends BasePresenterActivity<SignPresenter, SignContract.View>
        implements SignContract.View {
    @BindView(R.id.sign_in_list)
    RecyclerView recyclerView;
    @BindView(R.id.sign_in_day)
    TextView signInDayTv;
    @BindView(R.id.sign_in_btn)
    Button signInBtn;
    LinearLayoutManager manager;
    private SignInAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign;
    }

    @OnClick({R.id.sign_in_btn, R.id.integral_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sign_in_btn:
                mPresenter.signIn();
                break;
            case R.id.integral_detail:
                startActivity(new Intent(this,IntegralDetailActivity.class));
                break;
        }
    }

    @Override
    protected void initViews() {
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        adapter = new SignInAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(false);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
    }

    @Override
    protected void initData() {
        mPresenter.signInPage();
    }

    @Override
    public void signInPage(SignPageBean response) {
        if (response == null)
            return;
        adapter.replaceData(response.getDays());
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        manager.scrollToPositionWithOffset(aCalendar.get(Calendar.DAY_OF_MONTH) - 1, 0);
        manager.setStackFromEnd(true);
        signInDayTv.setText(response.getSign_days());
    }

    @Override
    public void signIn(SignBean bean) {
        mPresenter.signInPage();
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("签到");
        return true;
    }

    @Override
    protected SignPresenter createPresenter() {
        return new SignPresenter();
    }

}
