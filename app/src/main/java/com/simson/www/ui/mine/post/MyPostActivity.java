package com.simson.www.ui.mine.post;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.simson.www.R;
import com.simson.www.ui.adapter.MyViewPageAdapter;
import com.simson.www.ui.base.BaseFragment;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.diary.DiaryFragment;
import com.simson.www.ui.community.expert.ExpertFragment;
import com.simson.www.ui.community.knowledge.KnowledgeFragment;
import com.simson.www.ui.core.presenter.BasePresenter;
import com.simson.www.ui.mine.post.comment.MyCommentFragment;
import com.simson.www.ui.mine.post.release.MyReleaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyPostActivity extends BasePresenterActivity {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    protected void initViews() {
        ArrayList<Fragment> frag = new ArrayList<>();
        frag.add(new MyReleaseFragment());
        frag.add(new MyCommentFragment());
        ArrayList<String> titleData = new ArrayList<>();
        titleData.add("我发布的");
        titleData.add("我评论的");
        MyViewPageAdapter adapter = new MyViewPageAdapter(getSupportFragmentManager(),titleData,frag);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_post;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("我的帖子");
        return true;
    }
}
