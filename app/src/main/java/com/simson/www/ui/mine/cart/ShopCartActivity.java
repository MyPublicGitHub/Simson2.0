package com.simson.www.ui.mine.cart;

import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simson.www.R;
import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.mine.ShopCartBean;
import com.simson.www.net.bean.mine.SubmitShopCartBean;
import com.simson.www.ui.adapter.ShopCartAdapter;
import com.simson.www.ui.base.BasePresenterActivity;
import com.simson.www.utils.LogUtils;
import com.simson.www.utils.ToastUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopCartActivity extends BasePresenterActivity<ShopCartPresenter, ShopCartContract.View>
        implements ShopCartContract.View {
    @BindView(R.id.recyclerView)
    SwipeMenuRecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.iv_check_all)
    public ImageView ivCheckAll;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_point)
    TextView tvPoint;
    @BindView(R.id.tv_settlement)
    TextView tvSettlement;
    private ShopCartAdapter adapter;
    int mPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_cart;
    }

    @Override
    protected void initViews() {
        setRefresh();
        // 设置监听器。
        mRecyclerView.setSwipeMenuCreator(mSwipeMenuCreator);
        // 菜单点击监听。
        mRecyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);
        //mRecyclerView.setItemViewSwipeEnabled(true); // 侧滑删除，默认关闭。
        adapter = new ShopCartAdapter(null, this);
        //adapter.bindToRecyclerView(mRecyclerView);
        //adapter.setEmptyView(R.layout.list_empty_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnItemChildClickListener((adapters, view, position) -> {
            TextView number = (TextView) adapters.getViewByPosition(mRecyclerView, position, R.id.tv_number);
            String trim = number.getText().toString().trim();
            int num = Integer.parseInt(trim);
            switch (view.getId()) {
                case R.id.tv_reduce:
                    num = num - 1;
                    break;
                case R.id.tv_add:
                    num = num + 1;
                    break;
            }
            number.setText(num + "");
            money();
            ShopCartAdapter adapter = (ShopCartAdapter) adapters;
            itemIds = adapter.getData().get(position).getItem_id();
            cardId = adapter.getData().get(position).getCart_id();
            buyNums = trim;
            mPresenter.updateShopCart();
        });
        money();
    }

    @Override
    protected void initData() {
        mPresenter.getShopCart();
    }

    @Override
    public void showShopCart(List<ShopCartBean> bean) {
        if (bean == null) {
            return;
        }
        if (mPage == 1) {
            adapter.replaceData(bean);
            money();
        } else {
            adapter.addData(bean);
        }
        if (bean.size() == 0) {
            mRefreshLayout.setNoMoreData(true);
        }
    }

    @Override
    public void showRemoveShopCart(BaseBean bean) {
        if (removePosition != -1)
            adapter.remove(removePosition);
    }

    @Override
    public void showUpdateShopCart(BaseBean bean) {

    }

    @Override
    public void showSubmitOrder(SubmitShopCartBean bean) {
        /*if (bean.getOrderId() != null) {
            Intent intent = new Intent(this, PayActivity.class);
            intent.putExtra("transactionMoney", money);
            intent.putExtra("transactionPoint", point);
            intent.putExtra("orderId", bean.getOrderId());
            startActivity(intent);
            finish();
        }*/
    }

    @OnClick({R.id.tv_settlement, R.id.iv_check_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_check_all:
                isCheckAll = !isCheckAll;
                checkAll();
                break;
            case R.id.tv_settlement:
                List<ShopCartBean> data = adapter.getData();
                boolean isCheckOne = false;
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).isCheck) {
                        isCheckOne = true;
                        if (TextUtils.isEmpty(itemIds)) {
                            itemIds = data.get(i).getItem_id();
                        } else {
                            itemIds = itemIds + "," + data.get(i).getItem_id();
                        }
                        if (TextUtils.isEmpty(buyNums)) {
                            buyNums = data.get(i).getBuy_num() + "";
                        } else {
                            buyNums = buyNums + "," + data.get(i).getBuy_num();
                        }
                        if (TextUtils.isEmpty(cardId)) {
                            cardId = data.get(i).getCart_id() + "";
                        } else {
                            cardId = cardId + "," + data.get(i).getCart_id();
                        }
                    }
                }
                if (!isCheckOne) {
                    ToastUtils.showToast("请至少选择一个商品");
                    return;
                }
                mPresenter.submitOrder();
                break;
        }
    }

    String itemIds, buyNums, cardId;
    public boolean isCheckAll = false;

    private void checkAll() {
        setCheckAll(isCheckAll);
        List<ShopCartBean> data = adapter.getData();
        for (int i = 0; i < data.size(); i++) {
            data.get(i).isCheck = isCheckAll;
        }
        money();
        adapter.notifyDataSetChanged();
    }

    public void setCheckAll(boolean isCheck) {
        if (isCheck) {
            ivCheckAll.setImageResource(R.drawable.ic_check_box_black_24dp);
        } else {
            ivCheckAll.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp);
        }
    }

    double money;
    int point;

    public void money() {
        money = 0;
        point = 0;
        List<ShopCartBean> data = adapter.getData();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isCheck) {
                BigDecimal bigDecimal1 = new BigDecimal(Double.toString(money));
                BigDecimal bigDecimal2 = new BigDecimal(Double.toString(data.get(i).priceUser));
                BigDecimal add = bigDecimal1.add(bigDecimal2);
                money = add.doubleValue();

                BigDecimal bigPoint1 = new BigDecimal(Double.toString(point));
                BigDecimal bigPoint2 = new BigDecimal(Double.toString(data.get(i).pointUser));
                BigDecimal addPoint = bigPoint1.add(bigPoint2);
                point = data.get(i).pointUser + point;
            }
        }
        tvTotal.setText("￥" + String.valueOf(money));
        tvPoint.setText(String.valueOf(point) + "积分");
    }

    int removePosition = -1;
    SwipeMenuItemClickListener mMenuItemClickListener = (SwipeMenuBridge menuBridge) -> {
        // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
        menuBridge.closeMenu();

        int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
        int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
        int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

        if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {//删除购物车
            LogUtils.d("list第" + adapterPosition + "; 右侧菜单第" + menuPosition);
            cardId = adapter.getData().get(adapterPosition).getCart_id();
            removePosition = adapterPosition;
            mPresenter.removeShopCart();
        } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {


        }
    };

    // 创建菜单：
    SwipeMenuCreator mSwipeMenuCreator = (SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) -> {

        SwipeMenuItem deleteItem = new SwipeMenuItem(ShopCartActivity.this)
                .setBackgroundColor(getResources().getColor(R.color.colorRed))
                .setText("删除") // 文字。
                .setTextColor(getResources().getColor(R.color.white)) // 文字颜色。
                .setTextSize(14) // 文字大小。
                .setWidth(120)
                .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        rightMenu.addMenuItem(deleteItem); // 在Item右侧添加一个菜单。

    };

    @Override
    public String getPage() {
        return mPage + "";
    }

    @Override
    public String getItemIds() {
        return itemIds;
    }

    @Override
    public String getBuyNums() {
        return buyNums;
    }

    @Override
    public String getCartId() {
        return cardId;
    }

    @Override
    protected boolean initToolbar() {
        mTitle.setText("购物车");
        return true;
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPage = 1;
            mPresenter.getShopCart();
            mRefreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPage++;
            mPresenter.getShopCart();
            refreshLayout.finishLoadMore();
        });
    }

    @Override
    protected ShopCartPresenter createPresenter() {
        return new ShopCartPresenter();
    }

}
