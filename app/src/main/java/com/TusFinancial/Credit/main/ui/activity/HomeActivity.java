package com.TusFinancial.Credit.main.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.TusFinancial.Credit.R;
import com.base.qinxd.library.ui.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.jindiaodashi_home_layout);

        ButterKnife.bind(this);

        btnGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                String message = "";

                switch (checkedId) {

                    case R.id.tab_home_btn:

                        message = "点击了首页";

                        break;

                    case R.id.tab_information_btn:

                        message = "点击了咨询";

                        break;

                    case R.id.tab_ganhuo_btn:

                        message = "点击了干货";

                        break;

                    case R.id.tab_my_btn:

                        message = "点击了我的";

                        break;

                }

                Toast.makeText(HomeActivity.this,message,Toast.LENGTH_SHORT).show();

            }

        });

        homeBtn.setChecked(true);

    }

}
