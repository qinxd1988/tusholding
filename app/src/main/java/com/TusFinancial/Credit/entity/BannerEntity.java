package com.TusFinancial.Credit.entity;

import com.TusFinancial.Credit.bean.BannerBean;
import com.base.qinxd.library.entity.BaseEntity;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by xd on 2017/5/3.
 */

public class BannerEntity extends BaseEntity {

    public Data data;

    public static class Data{

        @SerializedName("bannerList")
        public ArrayList<BannerBean> list;

    }

}
