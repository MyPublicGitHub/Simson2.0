package com.simson.www.ui.community.diary.detail;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.simson.www.R;
import com.simson.www.net.bean.community.DiaryDetailAppendBean;
import com.simson.www.net.bean.community.DiaryDetailBean;
import com.simson.www.net.bean.community.DiaryDetailRecommendBean;
import com.simson.www.ui.adapter.DiaryDetailAdapter;
import com.simson.www.ui.adapter.DiaryDetailRecommendAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.diary.detail.append.AppendDiaryDetailActivity;
import com.simson.www.utils.GlideUtils;
import com.simson.www.utils.LogUtils;
import com.simson.www.widget.CircleImageView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;

public class DiaryDetailActivity extends BasePresenterActivity<DiaryDetailPresenter, DiaryDetailContract.View>
        implements DiaryDetailContract.View {


    @BindView(R.id.iv_header)
    CircleImageView ivHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.iv_before)
    ImageView ivBefore;
    @BindView(R.id.iv_after)
    ImageView ivAfter;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_item)
    TextView tvItem;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.rate)
    RatingBar rate;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.tv_effect)
    TextView tvEffect;
    @BindView(R.id.tv_evaluate)
    TextView tvEvaluate;
    @BindView(R.id.tv_browse)
    TextView tvBrowse;
    @BindView(R.id.tv_comments)
    TextView tvComments;
    @BindView(R.id.tv_praises)
    TextView tvPraises;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;

    DiaryDetailAdapter adapter;
    DiaryDetailRecommendAdapter adapterRecommend;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_diary_detail;
    }

    @Override
    protected void initData() {
        adapter = new DiaryDetailAdapter(null, delegate);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        adapter.setOnItemClickListener((adapter, view, position) ->
                startActivity(new Intent(DiaryDetailActivity.this, AppendDiaryDetailActivity.class)));
        adapterRecommend = new DiaryDetailRecommendAdapter(null);
        rvRecommend.setLayoutManager(new GridLayoutManager(this, 2));
        rvRecommend.setAdapter(adapterRecommend);

        adapterRecommend.setOnItemClickListener((adapter, view, position) -> {
            //startActivity(new Intent(DiaryDetailActivity.this, AppendDiaryDetailActivity.class));
        });

        mPresenter.getDiaryDetail();
        mPresenter.getDiaryDetailAppend();
        mPresenter.getDiaryDetailRecommend();
    }

    @Override
    public void showDiaryDetail(DiaryDetailBean bean) {
        GlideUtils.with(bean.getCustomer_head(), ivHeader);
        tvName.setText(bean.getCustomer_name() + "");
        tvDate.setText(bean.getIssue_time() + "");
        if (bean.getIs_follow() == 0) {
            tvFollow.setText("+关注");
        } else {
            tvFollow.setText("已关注");
        }
        GlideUtils.with(bean.getShu_qian(), ivBefore);
        GlideUtils.with(bean.getShu_hou(), ivAfter);

        tvContent.setText(bean.getContent() + "");
        tvItem.setText(bean.getItem_name() + "");
        tvPrice.setText("￥" + bean.getPrice());

        tvService.setText("服务：" + bean.getService_score() + "");
        tvEffect.setText("手术效果：" + bean.getEffect_score() + "");
        tvEvaluate.setText("医生评价：" + bean.getDoctor_score() + "");

        tvBrowse.setText(bean.getBrowse() + "阅读");
        tvComments.setText(bean.getComments() + "评论");
        tvPraises.setText(bean.getPraises() + "赞");
    }

    @Override
    public void showDiaryDetailAppend(List<DiaryDetailAppendBean> bean) {
        adapter.replaceData(bean);
    }

    @Override
    public void showDiaryDetailRecommend(List<DiaryDetailRecommendBean> bean) {
        adapterRecommend.replaceData(bean);
    }

    BGANinePhotoLayout.Delegate delegate = new BGANinePhotoLayout.Delegate() {
        @Override
        public void onClickNinePhotoItem(BGANinePhotoLayout ninePhotoLayout, View view, int position, String model, List<String> models) {
            mCurrentClickNpl = ninePhotoLayout;
            photoPreviewWrapper();
        }
    };
    private BGANinePhotoLayout mCurrentClickNpl;

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

    @Override
    protected DiaryDetailPresenter createPresenter() {
        return new DiaryDetailPresenter();
    }

    String mId;

    @Override
    protected void getIntent(Intent intent) {
        mId = intent.getStringExtra("id");
    }

    @Override
    public String getId() {
        return mId;
    }

    @Override
    public String getPX() {
        return "";
    }

    @Override
    public String getItemTypeId() {
        return "1";
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("日记详情");
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
