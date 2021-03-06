package com.simson.www.ui.community.circle.detail;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.community.FriendsCircleBean;
import com.simson.www.net.bean.community.FriendsCircleCommentBean;
import com.simson.www.net.bean.community.FriendsCircleDetailBean;
import com.simson.www.ui.adapter.FriendCircleCommentAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.utils.GlideUtils;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.ToastUtils;
import com.simson.www.widget.CircleImageView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;

public class FriendCircleDetailActivity extends BasePresenterActivity<FriendCircleDetailPresenter, FriendCircleDetailContract.View>
        implements FriendCircleDetailContract.View {

    @BindView(R.id.iv_header)
    CircleImageView ivHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.bga)
    BGANinePhotoLayout bga;
    @BindView(R.id.tv_comments)
    TextView tvComments;
    @BindView(R.id.tv_praises)
    TextView tvPraises;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    private int mPage = 1;
    private FriendCircleCommentAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_friend_circle_detail;
    }

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FriendCircleCommentAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        //adapter.bindToRecyclerView(recyclerView);
        //adapter.setEmptyView(R.layout.list_empty_view);
       /* adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<FriendsCircleCommentBean> bean = (List<FriendsCircleCommentBean>) adapter.getData();
            String id = bean.get(position).getFriends_circle_id();
            startActivity(new Intent(this, FriendCircleDetailActivity.class).putExtra("id", id));
        });*/
        setRefresh();
        btnCommit.setFocusable(true);
    }

    @Override
    protected void initData() {
        mPresenter.getFriendsCircle();
        mPresenter.fiendsCircleCommentList();
    }

    @Override
    protected FriendCircleDetailPresenter createPresenter() {
        return new FriendCircleDetailPresenter();
    }

    String friendsCircleId;

    @Override
    public String friendsCircleId() {
        return friendsCircleId;
    }

    @Override
    public int pageIndex() {
        return mPage;
    }

    @Override
    public String content() {
        return etContent.getText().toString();
    }

    @Override
    public void getFriendsCircle(FriendsCircleDetailBean bean) {
        GlideUtils.with(bean.getCustomer_head(), ivHeader);
        tvName.setText(bean.getCustomer_name() + "");
        bga.setDelegate(delegate);
        bga.setData((ArrayList<String>) bean.getPictures());
        tvDate.setText(bean.getCreate_time() + "");
        tvContent.setText(bean.getContent() + "");
        tvComments.setText(bean.getComments() + "评论");
        tvPraises.setText(bean.getPraises() + "赞");
    }
    private BGANinePhotoLayout mCurrentClickNpl;
    BGANinePhotoLayout.Delegate delegate = new BGANinePhotoLayout.Delegate() {
        @Override
        public void onClickNinePhotoItem(BGANinePhotoLayout ninePhotoLayout, View view, int position, String model, List<String> models) {
            mCurrentClickNpl = ninePhotoLayout;
            photoPreviewWrapper();
        }
    };
    private void photoPreviewWrapper() {
        if (mCurrentClickNpl == null) {
            return;
        }
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        File downloadDir = new File(Environment.getExternalStorageDirectory(), "Simson");
                        BGAPhotoPreviewActivity.IntentBuilder photoPreviewIntentBuilder = new BGAPhotoPreviewActivity.IntentBuilder(this)
                                .saveImgDir(downloadDir); // 保存图片的目录，如果传 null，则没有保存图片功能

                        if (mCurrentClickNpl.getItemCount() == 1) {
                            // 预览单张图片
                            photoPreviewIntentBuilder.previewPhoto(mCurrentClickNpl.getCurrentClickItem());
                        } else if (mCurrentClickNpl.getItemCount() > 1) {
                            // 预览多张图片
                            photoPreviewIntentBuilder.previewPhotos(mCurrentClickNpl.getData())
                                    .currentPosition(mCurrentClickNpl.getCurrentClickItemPosition()); // 当前预览图片的索引
                        }
                        startActivity(photoPreviewIntentBuilder.build());
                    } else {
                        LogUtils.e("权限被拒绝");
                    }
                });
    }
    @Override
    public void fiendsCircleCommentList(List<FriendsCircleCommentBean> bean) {
        if (bean == null) {
            return;
        }
        if (mPage == 1) {
            adapter.replaceData(bean);
        } else {
            adapter.addData(bean);
        }
        if (bean.size() == 0) {
            mRefreshLayout.setNoMoreData(true);

        }
    }

    @Override
    public void saveFriendsCircleComment(BaseBean bean) {
        ToastUtils.showToast(bean.message+"");
        mPage = 1;
        mPresenter.fiendsCircleCommentList();
        mPresenter.getFriendsCircle();
    }

    @Override
    protected void getIntent(Intent intent) {
        friendsCircleId = intent.getStringExtra("id");
    }


    @OnClick({R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_commit:
                mPresenter.saveFriendsCircleComment();
                break;
        }
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.fiendsCircleCommentList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.fiendsCircleCommentList();
            refreshLayout.finishLoadMore();
        });
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("详情");
        return true;
    }
}
