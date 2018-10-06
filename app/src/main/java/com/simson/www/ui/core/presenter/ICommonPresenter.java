package com.simson.www.ui.core.presenter;

/**
 * 通用业务接口
 * author:
 * date: 2018/3/20
 */

public interface ICommonPresenter {

    void getItemType();

    /**
     * bizId：收藏的业务id
     * method：方法 save或者delete
     * type： 1：日记；2科普；3问答；4商品
     */
    void collect(String bizId, String method, String type);

    /**
     * 关注
     * @param followCustomerId 被关注人的id
     * @param method           方法 save或者delete
     * @param type             1：医院；2医生；3顾客；商品
     */
    void follow(String followCustomerId, String method, String type);


    void praise(String bizId, String method, String type);

}
