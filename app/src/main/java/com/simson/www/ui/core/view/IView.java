package com.simson.www.ui.core.view;

import com.simson.www.net.bean.main.ItemTypeBean;

import java.util.List;

/**
 * view基类
 * Created by  on 2018/2/1.
 */

public interface IView{

    /**
     * 显示进度条
     *
     */
    void showLoading(String msg);

    /**
     * 隐藏进度条
     */
    void hideLoading();

    /**
     * 失败
     *
     * @param msg
     */
    void showFail(String msg);

    /**
     * 错误
     */
    void showError();

    /**
     * 没有数据
     */
    void showEmpty();//没有数据

    void setItemType(List<ItemTypeBean> bean);

}
