package com.TusFinancial.Credit.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.TusFinancial.Credit.JinDiaoApplication;
import com.TusFinancial.Credit.utils.ThirdConfig;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        regToWX();

        try {
            api.handleIntent(getIntent(), this);
        } catch (Exception e) {
            e.printStackTrace();

            finish();

        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);

        api.handleIntent(intent, this);

    }

    /**
     * 将appid注册到微信
     */
    private void regToWX() {

        api = WXAPIFactory.createWXAPI(this, ThirdConfig.WENWAN_WEIXIN_APPID, true);

        api.registerApp(ThirdConfig.WENWAN_WEIXIN_APPID);

    }

    @Override
    public void onReq(BaseReq arg0) {

    }

    @Override
    public void onResp(BaseResp resp) {

        int result = 0;

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK://授权成功

                if (SendAuth.Resp.class.isInstance(resp)) {

                    SendAuth.Resp auth = (SendAuth.Resp) resp;

                    JinDiaoApplication.WECHAT_CODE = auth.code;

                    finish();

                } else {


                }

                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消授权
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                finish();
                break;
            default:
                break;
        }


    }

    @Override
    protected void onStop() {

        super.onStop();

    }

}
