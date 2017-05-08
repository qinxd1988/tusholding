package com.base.qinxd.library.entity;

import com.base.qinxd.library.network.utils.Code;

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

        return Code.OK.equals(code);

    }

    public String getMsg() {

        return msg;

    }

}
