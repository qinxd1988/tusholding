package com.TusFinancial.Credit.main.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.TusFinancial.Credit.JinDiaoApplication;
import com.TusFinancial.Credit.R;
import com.TusFinancial.Credit.bean.ModuleBean;
import com.TusFinancial.Credit.bean.TemplateBean;
import com.TusFinancial.Credit.main.adapter.HomeAdapter;
import com.base.qinxd.library.image.ImageLoaderWrapper;
import com.base.qinxd.library.ui.fragment.BaseFragment;

import java.util.ArrayList;

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

    @BindView(R.id.home_recycler_view)
    RecyclerView mRecyclerView;

    HomeAdapter mAdapter;

    LinearLayoutManager manager;

    private int showTopSearchLayoutHeight;

    private ArrayList<Object> mList;

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

        showTopSearchLayoutHeight = Math.round(JinDiaoApplication.DENSITY * 60);

        mAdapter = new HomeAdapter(getContext());

        manager = new LinearLayoutManager(getContext());

        initData();

    }

    private void initData() {

        if (mList == null) {

            mList = new ArrayList<>();

        }

        mList.clear();

        TemplateBean notice = new TemplateBean();

        notice.type = 1;

        notice.list = new ArrayList<>();

        ModuleBean notice1 = new ModuleBean();

        notice1.title = "北京****科技公司融资1000万";

        notice.list.add(notice1);

        ModuleBean notice2 = new ModuleBean();

        notice2.title = "恭喜北京****成功上市！";

        notice.list.add(notice2);

        mList.add(notice);

        TemplateBean bean1 = new TemplateBean();

        bean1.type = 2;

        bean1.list = new ArrayList<>();

        ModuleBean module1 = new ModuleBean();

        module1.title = "一键尽调";

        module1.imgUrl = ImageLoaderWrapper.DRAWABLE + R.drawable.ic_yijianjindiao;

        module1.linkUrl = "";

        bean1.list.add(module1);

        ModuleBean module2 = new ModuleBean();

        module2.title = "找资产";

        module2.imgUrl = ImageLoaderWrapper.DRAWABLE + R.drawable.ic_zhaozichan;

        module2.linkUrl = "";

        bean1.list.add(module2);

        ModuleBean module3 = new ModuleBean();

        module3.title = "推项目";

        module3.imgUrl = ImageLoaderWrapper.DRAWABLE + R.drawable.ic_tuixiangmu;

        module3.linkUrl = "";

        bean1.list.add(module3);

        mList.add(bean1);

        TemplateBean bean2 = new TemplateBean();

        bean2.type = 3;

        bean2.list = new ArrayList<>();

        for (int i = 0; i < 8; i++) {

            ModuleBean module = new ModuleBean();

            switch (i) {

                case 0:

                    module.title = "查评级";

                    module.imgUrl = ImageLoaderWrapper.DRAWABLE + R.drawable.ic_pingji;

                    break;

                case 1:

                    module.title = "查舆情";

                    module.imgUrl = ImageLoaderWrapper.DRAWABLE + R.drawable.ic_yuqing;

                    break;

                case 2:

                    module.title = "查受惩";

                    module.imgUrl = ImageLoaderWrapper.DRAWABLE + R.drawable.ic_shoucheng;

                    break;

                case 3:

                    module.title = "查纳税";

                    module.imgUrl = ImageLoaderWrapper.DRAWABLE + R.drawable.ic_nashui;

                    break;

                case 4:

                    module.title = "查司法";

                    module.imgUrl = ImageLoaderWrapper.DRAWABLE + R.drawable.ic_sifa;

                    break;

                case 5:

                    module.title = "贴现利率";

                    module.imgUrl = ImageLoaderWrapper.DRAWABLE + R.drawable.ic_tiexianlilv;

                    break;

                case 6:

                    module.title = "企业排名";

                    module.imgUrl = ImageLoaderWrapper.DRAWABLE + R.drawable.ic_qiyepaiming;

                    break;

                case 7:

                    module.title = "更多";

                    module.imgUrl = ImageLoaderWrapper.DRAWABLE + R.drawable.ic_home_more;

                    break;

            }

            bean2.list.add(module);

        }

        mList.add(bean2);

        mAdapter.refreshContentList(mList);


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

        ViewGroup.LayoutParams params = appBarLayout.getLayoutParams();

        params.height = Math.round(JinDiaoApplication.WIDTH * 1f / 75 * 33);

        appBarLayout.setLayoutParams(params);

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

        mRecyclerView.setLayoutManager(manager);

        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

}
