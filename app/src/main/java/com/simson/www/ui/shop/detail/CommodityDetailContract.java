package com.simson.www.ui.shop.detail;

import com.simson.www.net.bean.BaseBean;
import com.simson.www.net.bean.home.LatelyHospitalBean;
import com.simson.www.net.bean.mine.SubmitOrderBean;
import com.simson.www.net.bean.shop.CommodityDetailBean;
import com.simson.www.net.bean.shop.CommodityDetailPraiseBean;
import com.simson.www.ui.shop.detail.technology.TechnologyContract;


public interface CommodityDetailContract {
    interface Presenter {
        void getCommodityDetail();

        void getCommodityDetailPicture();

        void getCommodityDetailPraise();

        void getLatelyHospital();

        void saveShopCart();

        void submitOrder();

        void collect();
    }

    interface View extends TechnologyContract.View {

        String getItemId();

        String getMethod();

        String getType();

        String getBuyNum();

        String hospitalId();

        String technologyId();

        String subscribeDate();

        String longitude();

        String latitude();

        void showCommodityDetail(CommodityDetailBean bean);

        void getLatelyHospital(LatelyHospitalBean bean);

        void showCommodityDetailPicture(CommodityDetailBean bean);

        void showCommodityDetailPraise(CommodityDetailPraiseBean bean);

        void showSaveShopCart(BaseBean bean);

        void showSubmitOrder(SubmitOrderBean bean);

    }
}
