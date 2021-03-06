package com.simson.www.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.simson.www.R;
import com.simson.www.net.bean.mine.OrderBean;

import java.util.List;

public class OrderAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {

    public OrderAdapter(@Nullable List<OrderBean> data) {
        super(R.layout.item_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {

        helper.setText(R.id.tv_date, item.getCreate_time());
        //helper.setText(R.id.tv_trade_state, item.getTrans_type());//TODO 字段待确认
        helper.setText(R.id.tv_buy_num, "共" + item.getItems().size() + "件商品");
        helper.setText(R.id.tv_total, "合计:￥" + item.getTransaction_money());
        helper.setText(R.id.tv_integral, "积分兑换:" + item.getTransaction_point());
        helper.addOnClickListener(R.id.tv_remind_shipments);
        helper.addOnClickListener(R.id.tv_logistics);
        helper.addOnClickListener(R.id.tv_evaluate);
        helper.addOnClickListener(R.id.tv_payment);
        /**
         *  To_Be_Paid = 1;//待支付
         *  Already_Paid = 2;//已支付
         *  To_Be_Evaluated = 4;//待评价
         */
        if (item.getTransaction_status() == 1) {//显示付款
            helper.getView(R.id.tv_remind_shipments).setVisibility(View.GONE);
            helper.getView(R.id.tv_logistics).setVisibility(View.GONE);
            helper.getView(R.id.tv_evaluate).setVisibility(View.GONE);
            helper.getView(R.id.tv_payment).setVisibility(View.VISIBLE);
        } else if (item.getTransaction_status() == 2) {//显示使用
            helper.getView(R.id.tv_remind_shipments).setVisibility(View.GONE);
            helper.getView(R.id.tv_logistics).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_evaluate).setVisibility(View.GONE);
            helper.getView(R.id.tv_payment).setVisibility(View.GONE);
        } else if (item.getTransaction_status() == 4) {//显示评价
            helper.getView(R.id.tv_remind_shipments).setVisibility(View.GONE);
            helper.getView(R.id.tv_logistics).setVisibility(View.GONE);
            helper.getView(R.id.tv_evaluate).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_payment).setVisibility(View.GONE);
        } else {//
            helper.getView(R.id.tv_remind_shipments).setVisibility(View.GONE);
            helper.getView(R.id.tv_logistics).setVisibility(View.GONE);
            helper.getView(R.id.tv_evaluate).setVisibility(View.GONE);
            helper.getView(R.id.tv_payment).setVisibility(View.GONE);
        }

        OrderItemAdapter adapter = new OrderItemAdapter(item.getItems());
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }
}
