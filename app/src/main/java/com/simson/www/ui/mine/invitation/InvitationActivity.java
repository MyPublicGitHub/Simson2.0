package com.simson.www.ui.mine.invitation;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.ui.adapter.InviteAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InvitationActivity extends BasePresenterActivity {

    @BindView(R.id.tv_invite)
    TextView tvInvite;
    @BindView(R.id.tv_invite_qr)
    TextView tvInviteQr;
    @BindView(R.id.tv_my_invite)
    TextView tvMyInvite;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    InviteAdapter mAdapter;
    @OnClick({R.id.tv_invite, R.id.tv_invite_qr, R.id.tv_my_invite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_invite:
                break;
            case R.id.tv_invite_qr:
                break;
            case R.id.tv_my_invite:
                break;
        }
    }

    @Override
    protected void initViews() {
        List<BaseBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new BaseBean());
        }
        mAdapter = new InviteAdapter(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        mAdapter.replaceData(list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_invitation;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("邀请好友");
        return true;
    }

}
