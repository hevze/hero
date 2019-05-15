package com.hero.api;

import java.io.Serializable;

/**
 * 服务端数据返回封装类
 */
public class BaseRespose<T> implements Serializable {
    private int status; //返回状态
    private String msg; //对应信息
    public T data; //数据


    public BaseRespose(T data, int status, String msg) {
        this.data = data;
        this.status = status;
        this.msg = msg;
    }

    /**
     * 默认成功类型
     *
     * @param data
     * @param msg
     */
    public BaseRespose(T data, String msg) {
        this.data = data;
        this.status = 200;
        this.msg = msg;
    }

    /**
     * 失败数据
     *
     * @param status
     * @param msg
     */
    public BaseRespose(int status, String msg) {
        this.status = status;
        this.msg = msg;
        this.data = null;
    }

    @Override
    public String toString() {
        return "BaseRespose{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
