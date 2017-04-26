package com.TusFinancial.Credit.main.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.TusFinancial.Credit.R;
import com.TusFinancial.Credit.helper.TransferHelper;
import com.TusFinancial.Credit.loginRegister.ui.activity.LoginActivity;
import com.TusFinancial.Credit.loginRegister.ui.activity.RegisterActivity;
import com.base.qinxd.library.ui.fragment.BaseFragment;

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

            case R.id.my_project_layout:

                break;

            case R.id.my_report_layout:

                break;

            case R.id.my_focus_company_layout:

                break;

            case R.id.my_collection_layout:

                break;

            case R.id.my_browsing_history_layout:

                break;

            case R.id.my_fankui_layout:

                break;

        }

        TransferHelper.onTransfer(getContext(), url, true);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
