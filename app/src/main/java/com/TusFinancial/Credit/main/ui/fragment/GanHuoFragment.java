package com.TusFinancial.Credit.main.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.TusFinancial.Credit.R;
import com.base.qinxd.library.ui.fragment.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by xd on 2017/4/22.
 */

public class GanHuoFragment extends BaseFragment {

    public static GanHuoFragment newInstance() {

        Bundle bundle = new Bundle();

        GanHuoFragment fragment = new GanHuoFragment();

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

        View view = inflater.inflate(R.layout.ganhuo_fragment_layout, null);

        ButterKnife.bind(this, view);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
