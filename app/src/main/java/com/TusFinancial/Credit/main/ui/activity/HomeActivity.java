package com.TusFinancial.Credit.main.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.TusFinancial.Credit.JinDiaoApplication;
import com.TusFinancial.Credit.R;
import com.TusFinancial.Credit.browse.ui.fragment.BrowseFragment;
import com.TusFinancial.Credit.main.ui.fragment.GanHuoFragment;
import com.TusFinancial.Credit.main.ui.fragment.HomeFragment;
import com.TusFinancial.Credit.main.ui.fragment.InformationFragment;
import com.TusFinancial.Credit.main.ui.fragment.MyFragment;
import com.base.qinxd.library.network.utils.Const;
import com.base.qinxd.library.ui.activity.BaseActivity;
import com.base.qinxd.library.ui.fragment.BaseFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/3/14 10:43
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：首页
 */
public class HomeActivity extends BaseActivity {

    @BindView(R.id.tab_btn_group)
    RadioGroup btnGroup;

    @BindView(R.id.tab_home_btn)
    RadioButton homeBtn;

    @BindView(R.id.tab_information_btn)
    RadioButton informationBtn;

    @BindView(R.id.tab_ganhuo_btn)
    RadioButton ganhuoBtn;

    @BindView(R.id.tab_my_btn)
    RadioButton myBtn;

    private Map<Integer, BaseFragment> fragmentMap = new HashMap<>();

    FragmentManager fragmentManager;

    private Fragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = getSupportFragmentManager();

        setContentView(R.layout.jindiao_home_layout);

        ButterKnife.bind(this);

        homeBtn.setChecked(true);

        fragmentOperate(R.id.tab_home_btn);

    }

    void fragmentOperate(int resId) {

        BaseFragment fragment = fragmentMap.get(resId);

        if (fragment != null && mFragment == fragment) {

            fragment.autoRefresh();

        }

        if (fragment == null) {

            switch (resId) {

                case R.id.tab_home_btn:

                    fragment = HomeFragment.newInstance();

                    break;

                case R.id.tab_information_btn:

                    fragment = InformationFragment.newInstance();

                    break;

                case R.id.tab_ganhuo_btn:

                    fragment = BrowseFragment.newInstance(Const.GANHUO_URL);

                    break;

                case R.id.tab_my_btn:

                    String url = Const.BASE_URL + "/web/users/index.html?token=" + JinDiaoApplication.TOKEN;

                    fragment = BrowseFragment.newInstance(url);

                    break;

            }

        }

        List<Fragment> fragmentList = fragmentManager.getFragments();

        boolean isAdded = false;

        if (fragmentList != null) {

            isAdded = fragmentList.contains(fragment);

            if (isAdded) {//已添加 显示

                for (Fragment frag : fragmentList) {

                    if (frag != fragment) {

                        fragmentManager.beginTransaction().hide(frag).commitAllowingStateLoss();

                    }

                }

                mFragment = fragment;

                fragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss();

                return;

            }

        }

        if (!isAdded) {//未添加，将fragment 添加

            fragmentMap.put(resId, fragment);

            mFragment = fragment;

            fragmentManager
                    .beginTransaction()
                    .add(R.id.replace_layout, fragment)
                    .disallowAddToBackStack()
                    .commitAllowingStateLoss();

        }

    }

    @OnClick({R.id.tab_home_btn, R.id.tab_information_btn, R.id.tab_ganhuo_btn, R.id.tab_my_btn})
    void onClick(View view) {

        int resId = view.getId();

        fragmentOperate(resId);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (mFragment instanceof BrowseFragment) {

                if (((BrowseFragment) mFragment).onKeyDown(keyCode, event)) {

                    return true;

                }

            }

        }

        return super.onKeyDown(keyCode, event);

    }

}
