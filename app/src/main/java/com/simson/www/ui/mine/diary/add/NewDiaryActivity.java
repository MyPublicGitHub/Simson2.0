package com.simson.www.ui.mine.diary.add;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRatingBar;
import android.widget.RatingBar;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.simson.www.R;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.core.presenter.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class NewDiaryActivity extends BasePresenterActivity {


    @BindView(R.id.npl_item_moment_photos)
    BGANinePhotoLayout nplItemMomentPhotos;
    @BindView(R.id.rate)
    AppCompatRatingBar rate;
    @BindView(R.id.rate1)
    SimpleRatingBar rate1;
    @BindView(R.id.rate2)
    MaterialRatingBar rate2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_diary;
    }

    @Override
    protected void initViews() {
        rate2.setProgressDrawable(getResources().getDrawable(R.drawable.ratingbar_smile));
        rate2.setNumStars(5);
        rate2.setMax(5);
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("写日记");
        mToolbar.inflateMenu(R.menu.toolbar_menu_diary);
        mToolbar.setOnMenuItemClickListener(item -> {
            startActivity(new Intent(this, NewDiaryActivity.class));
            return false;
        });
        return true;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
