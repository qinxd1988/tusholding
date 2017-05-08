package com.TusFinancial.Credit.entity;

import android.text.TextUtils;

import com.TusFinancial.Credit.bean.RecommendBean;
import com.base.qinxd.library.entity.BaseEntity;

import java.util.ArrayList;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/5/8 10:38
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：首页推荐实体
 */
public class RecommendEntity extends BaseEntity {

    /**
     * 0，表示成功；1，表示失败
     */
    public int status = -1;

    /**
     * 请求耗时，单位毫秒
     */
    public long cost;

    /**
     * 请求唯一标识
     */
    public String req_id;

    /**
     * 响应数目
     */
    public int count;

    /**
     * 错误编码
     */
    public String error_code;

    /**
     * 错误信息
     */
    public String error_msg;

    public String columnid;

    public String appid;

    public ArrayList<RecommendBean> data;

    @Override
    public boolean isSuccess() {

        return 0 == status;

    }

    @Override
    public String getMsg() {

        if (!TextUtils.isEmpty(error_msg)) {

            return error_msg;

        }

        return null;

    }

}
