package com.simson.www.ui.community.knowledge.search;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.common.Const;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.community.PopularizationBean;
import com.simson.www.ui.adapter.KnowledgeItemAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.ui.community.knowledge.detail.WebViewActivity;
import com.simson.www.ui.community.knowledge.item.KnowledgeItemContract;
import com.simson.www.ui.community.knowledge.item.KnowledgeItemPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchKnowledgeActivity extends BasePresenterActivity<KnowledgeItemPresenter, KnowledgeItemContract.View>
        implements KnowledgeItemContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    private String type;//type：1推荐，2关注，空不传是全部
    private int mPage = 1;
    private KnowledgeItemAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_knowledge;
    }

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new KnowledgeItemAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.list_empty_view);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List<PopularizationBean> bean = (List<PopularizationBean>) adapter.getData();
            startActivity(new Intent(this, WebViewActivity.class)
                    .putExtra(Const.WEB_VIEW_TITLE, "科普详情")
                    .putExtra(Const.WEB_VIEW_URL, bean.get(position).getLink_url()));
        });
        adapter.setOnItemChildClickListener((adapter, views, position) -> {
            mPosition = position;
            PopularizationBean bean = (PopularizationBean) adapter.getData().get(position);
            String mFollowMethod = bean.getIs_follow() == 0 ? "save" : "delete";
            switch (views.getId()) {
                case R.id.tv_follow:
                    mPresenter.follow(bean.getHospital_id(), mFollowMethod, Const.FOLLOW_TYPE.HOSPITAL);
                    break;
            }
        });
        setRefresh();
    }

    int mPosition;

    @Override
    public void follow(BaseBean bean) {
        List<PopularizationBean> data = adapter.getData();
        PopularizationBean beans = data.get(mPosition);
        beans.setIs_follow(beans.getIs_follow() == 0 ? 1 : 0);
        adapter.notifyItemChanged(mPosition, beans);
    }

    @Override
    public void showPopularizationList(List<PopularizationBean> bean) {
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

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.getPopularizationList();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getPopularizationList();
            refreshLayout.finishLoadMore();
        });
    }

    @Override
    public int getPage() {
        return mPage;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String itemTypeId() {
        return null;
    }

    @Override
    public String search() {
        return etSearch.getText().toString();
    }

    @Override
    protected KnowledgeItemPresenter createPresenter() {
        return new KnowledgeItemPresenter();
    }


    @OnClick({R.id.iv_back, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                mPresenter.getPopularizationList();
                break;
        }
    }
}
