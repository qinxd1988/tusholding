package com.TusFinancial.Credit.bean;

/**
 * Created by xd on 2017/5/3.
 */

public class BannerBean extends BaseBean {

    public String bannerPic;//   图片地址

    public String bannerTitle;//   图片描述

    public int triggerType;//   触发类型

    public String triggerContent;//   触发内容

    public int channel;//   渠道，0 全部 1 IOS 2 安卓

    public String sort;//   排序id

    public String createDate;//   创建时间

    public String oper;//   操作人

    public int imageLocation;//图片位置，目前以图片描述区分

    public String bannerColour;//   图片底色

}
