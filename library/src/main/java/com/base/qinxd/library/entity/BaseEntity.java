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

    public String code;

    public String msg;

    public boolean successful;

    public String doing;

    public boolean isSuccess() {

        return "C00000".equals(code);

    }

    public String getMsg() {

        return msg;

    }

}
