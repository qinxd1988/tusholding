package com.TusFinancial.Credit.main.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.TusFinancial.Credit.R;
import com.TusFinancial.Credit.loginRegister.ui.activity.LoginActivity;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
