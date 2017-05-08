package com.TusFinancial.Credit.main.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.TusFinancial.Credit.JinDiaoApplication;
import com.TusFinancial.Credit.R;
import com.TusFinancial.Credit.api.ApiUser;
import com.TusFinancial.Credit.event.LoginFinishEvent;
import com.TusFinancial.Credit.event.LoginOutEvent;
import com.TusFinancial.Credit.event.UserEntity;
import com.TusFinancial.Credit.helper.TransferHelper;
import com.TusFinancial.Credit.loginRegister.ui.activity.LoginActivity;
import com.TusFinancial.Credit.loginRegister.ui.activity.RegisterActivity;
import com.TusFinancial.Credit.utils.Constants;
import com.base.qinxd.library.image.DisplayOption;
import com.base.qinxd.library.image.ImageLoaderFactory;
import com.base.qinxd.library.network.ApiCallBack;
import com.base.qinxd.library.network.utils.Code;
import com.base.qinxd.library.network.utils.Const;
import com.base.qinxd.library.ui.fragment.BaseFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xd on 2017/4/22.
 * 我的 fragment
 */

public class MyFragment extends BaseFragment {

    @BindView(R.id.my_login_text)
    AppCompatTextView loginText;

    @BindView(R.id.my_head_img)
    ImageView mMyHeadImg;

    @BindView(R.id.my_register_text)
    AppCompatTextView mMyRegisterText;

    @BindView(R.id.my_login_register_layout)
    LinearLayout mMyLoginRegisterLayout;

    @BindView(R.id.my_name_text)
    AppCompatTextView mMyNameText;

    @BindView(R.id.my_project_layout)
    RelativeLayout mMyProjectLayout;

    @BindView(R.id.my_report_layout)
    RelativeLayout mMyReportLayout;

    @BindView(R.id.my_focus_company_layout)
    RelativeLayout mMyFocusCompanyLayout;

    @BindView(R.id.my_collection_layout)
    RelativeLayout mMyCollectionLayout;

    @BindView(R.id.my_browsing_history_layout)
    RelativeLayout mMyBrowsingHistoryLayout;

    @BindView(R.id.my_setting_layout)
    RelativeLayout mMySettingLayout;

    @BindView(R.id.my_kefu_layout)
    RelativeLayout mMyKefuLayout;

    @BindView(R.id.my_fankui_layout)
    RelativeLayout mMyFankuiLayout;

    @BindView(R.id.my_about_layout)
    RelativeLayout mMyAboutLayout;

    private SharedPreferences mPreferences;

    public static MyFragment newInstance() {

        Bundle bundle = new Bundle();

        MyFragment fragment = new MyFragment();

        fragment.setArguments(bundle);

        return fragment;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected boolean isSupportEventBus() {

        return true;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.my_fragment_layout, null);

        ButterKnife.bind(this, view);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!TextUtils.isEmpty(JinDiaoApplication.TOKEN)) {

            loadUserData();

        } else {

            mMyLoginRegisterLayout.setVisibility(View.VISIBLE);

            mMyNameText.setVisibility(View.GONE);

            mMyNameText.setText(JinDiaoApplication.MOBILE);

        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginFinishEvent event) {

        loadUserData();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginOutEvent event) {

        mMyLoginRegisterLayout.setVisibility(View.VISIBLE);

        mMyNameText.setVisibility(View.GONE);

        mMyNameText.setText(JinDiaoApplication.MOBILE);

    }

    @OnClick(R.id.my_login_text)
    void onClick() {

        Intent intent = new Intent(getContext(), LoginActivity.class);

        startActivity(intent);

    }

    @OnClick(R.id.my_register_text)
    void onRegisterClick() {

        Intent intent = new Intent(getContext(), RegisterActivity.class);

        startActivity(intent);

    }

    @OnClick({R.id.my_project_layout, R.id.my_report_layout,
            R.id.my_focus_company_layout, R.id.my_collection_layout,
            R.id.my_browsing_history_layout, R.id.my_fankui_layout})
    void onNeedLoginClick(View view) {

        String url = "default";

        switch (view.getId()) {

            case R.id.my_project_layout://我的项目
                url = Const.BASE_URL + "/web/users/myproject.html";
                break;

            case R.id.my_report_layout://我的报告
                url = Const.BASE_URL + "/web/users/myreport.html";
                break;

            case R.id.my_focus_company_layout://我的关注
                url = Const.BASE_URL + "/web/users/subscription.html";
                break;

            case R.id.my_collection_layout:

                break;

            case R.id.my_browsing_history_layout://浏览记录
                url = Const.BASE_URL + "/web/users/history.html";
                break;

            case R.id.my_fankui_layout:

                break;

        }

        url = operateUrl(url);

        TransferHelper.onTransfer(getContext(), url, true);

    }

    private String operateUrl(String url) {

        if (url.contains("&token=")) {

            Uri uri = Uri.parse(url);

            String data = uri.getQueryParameter(Constants.TOKEN);

            //使用本地token值替换
            if (TextUtils.isEmpty(data)) {

                url = url.replace("&token=", "&token=" + JinDiaoApplication.TOKEN);

            } else {

                url = url.replace(data, JinDiaoApplication.TOKEN);

            }

        } else {

            if (url.indexOf("token") == -1) {

                if (url.contains("?")) {

                    url += "&token=" + JinDiaoApplication.TOKEN;

                } else {

                    url += "?token=" + JinDiaoApplication.TOKEN;

                }

            }

        }

        return url;

    }

    private void loadUserData() {

        ApiUser api = new ApiUser(getContext());

        api.setToken(JinDiaoApplication.TOKEN)
                .setApiCallBack(new ApiCallBack<UserEntity>() {
                    @Override
                    public void onSuccess(UserEntity response) {

                        if (response != null && response.data != null) {

                            mMyLoginRegisterLayout.setVisibility(View.GONE);

                            mMyNameText.setVisibility(View.VISIBLE);

                            mMyNameText.setText(response.data.mobile);

                            if (!TextUtils.isEmpty(response.data.headImgUrl)) {

                                ImageLoaderFactory.getImageLoader()
                                        .with(getContext())
                                        .displayImage(mMyHeadImg, response.data.headImgUrl,
                                                DisplayOption.createTransparentOption().setCenterCrop(true));

                            }

                        }

                    }

                    @Override
                    public void onError(UserEntity response, String err_msg) {

                        if (response != null) {

                            if (Code.TOKEN_DISABLE_CODE.equals(response.code)) {

                                JinDiaoApplication.TOKEN = "";

                                JinDiaoApplication.MOBILE = "";

                                if (mPreferences == null) {

                                    mPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

                                }

                                mPreferences.edit().putString(Constants.TOKEN, "").commit();

                                mPreferences.edit().putString(Constants.MOBILE, "").commit();

                            }

                        }

                    }

                    @Override
                    public void onFailure() {

                    }
                });

        api.enqueue();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

}
