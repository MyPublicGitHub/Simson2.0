package com.simson.www.ui.community.expert.detail;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.community.QuestionsDetailBean;
import com.simson.www.ui.adapter.QuestDetailAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.utils.GlideUtils;
import com.simson.www.utils.LogUtils;
import com.simson.www.widget.CircleImageView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;

public class QuestionDetailActivity extends BasePresenterActivity<QuestionDetailPresenter, QuestionDetailContract.View>
        implements QuestionDetailContract.View {


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
    @BindView(R.id.tv_follow)
    TextView tvFollow;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_question_detail;
    }

    QuestDetailAdapter adapter;

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuestDetailAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        //adapter.bindToRecyclerView(recyclerView);
        //adapter.setEmptyView(R.layout.list_empty_view);
    }

    @Override
    protected void initData() {
        mPresenter.questionsDetail();
    }

    @Override
    protected void getIntent(Intent intent) {
        questionsId = intent.getStringExtra("questionsId");
    }

    String questionsId;

    @Override
    public String questionsId() {
        return questionsId;
    }

    @Override
    public void questionsDetail(QuestionsDetailBean bean) {

        GlideUtils.with(bean.getCustomer_head(), ivHeader);
        tvName.setText(bean.getCustomer_name() + "");
        bga.setData((ArrayList<String>) bean.getPictures());
        bga.setDelegate(delegate);
        tvDate.setText(bean.getCreate_time() + "");
        tvContent.setText(bean.getQuestions_content() + "");
        tvComments.setText(bean.getAnswerCount() + "回答");
        tvPraises.setText(bean.getPraises() + "赞");
        tvFollow.setText(isFollow == true ? "已关注" : "+ 关注");
        adapter.replaceData(bean.getAnswers());
        id = bean.getCustomer_id();
        isFollow = bean.getIs_follow() == 0 ? false : true;
    }

    //bga.setDelegate(delegate);
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
                        File downloadDir = new File(Environment.getExternalStorageDirectory(), "SimsonDownload");
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

    boolean isFollow = false;

    @Override
    public void follow(BaseBean bean) {
        tvFollow.setText(isFollow == true ? "已关注" : "+ 关注");
        isFollow = !isFollow;
    }

    @Override
    protected QuestionDetailPresenter createPresenter() {
        return new QuestionDetailPresenter();
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("提问详情");
        return true;
    }

    String id, method;

    @OnClick({R.id.tv_follow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_follow:
                method = isFollow == true ? "delete" : "save";
                mPresenter.follow(id, method, Const.FOLLOW_TYPE.USER);
                break;
        }
    }
}
