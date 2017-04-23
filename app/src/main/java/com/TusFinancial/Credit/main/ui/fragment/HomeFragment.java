package com.TusFinancial.Credit.main.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.TusFinancial.Credit.JinDiaoApplication;
import com.TusFinancial.Credit.R;
import com.base.qinxd.library.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xd on 2017/4/22.
 * 首页fragment
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;

    @BindView(R.id.title_top_search_layout)
    RelativeLayout titleTopSearchLayout;

    @BindView(R.id.home_search_layout)
    RelativeLayout searchLayout;

    private int showTopSearchLayoutHeight;

    public static HomeFragment newInstance() {

        Bundle bundle = new Bundle();

        HomeFragment fragment = new HomeFragment();

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

        showTopSearchLayoutHeight = Math.round(JinDiaoApplication.DENSITY * 58);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment_layout, null);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset) >= showTopSearchLayoutHeight) {

                    titleTopSearchLayout.setVisibility(View.VISIBLE);

                } else {

                    titleTopSearchLayout.setVisibility(View.GONE);

                }

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

}
