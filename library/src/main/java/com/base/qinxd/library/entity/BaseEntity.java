package com.base.qinxd.library.entity;

import java.io.Serializable;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/3/13 14:39
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：
 */
public class BaseEntity implements Serializable {

    // 是否成功
    private boolean isSuccess;
    // 数据
    public String info;
    // 错误消息
    public String errormsg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

}
