package com.TusFinancial.Credit.bean;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/4/29 14:14
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：
 */
public class LoginBean extends BaseBean{

    /**
     * 登录成功令牌
     */
    public String token;

    /**
     * 是否需要极验 验证码  0不需要  1需要
     */
    public String isVerfyCode;

    public String mobile;

    /**
     * 微信登录使用 当token为空或者null时，需要用户绑定手机号
     */
    public String openId;

}
