package com.simson.www.net.bean;

/**
 * 实体基类
 */

public class BaseBean<T> {
    /**
     * 服务器返回的错误码
     */
    public int result;
    /**
     * 服务器返回的成功或失败的提示
     */
    public String message;
    /**
     * 服务器返回的数据
     */
    public T data;

    public long timestamp;



//    public BaseBean(int result, String message, T data,long timestamp) {
//        this.result = result;
//        this.message = message;
//        this.data = data;
//        this.timestamp = timestamp;
//    }
}
