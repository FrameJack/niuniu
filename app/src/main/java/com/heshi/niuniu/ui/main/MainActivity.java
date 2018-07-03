package com.heshi.niuniu.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.heshi.niuniu.R;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.custom.NoScrollViewPager;
import com.heshi.niuniu.custom.tab.CustomTabView;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.fragment.main.connect.ConnectingFragment;
import com.heshi.niuniu.fragment.main.dynamic.DynamicFragment;
import com.heshi.niuniu.fragment.main.mine.MineFragment;
import com.heshi.niuniu.fragment.main.msg.MsgFragment;
import com.heshi.niuniu.fragment.main.weibo.WeiBoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class MainActivity extends BaseActivity<MainPresent> implements CustomTabView.OnTabCheckListener, ViewPager.OnPageChangeListener {


    @BindView(R.id.viewpager_main)
    ViewPager viewpagerMain;
    @BindView(R.id.mCustomTabView)
    CustomTabView mCustomTabView;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> strList = new ArrayList<>();

    @Override
    protected void setupActivityComponent(AppComponent appComponent, ActivityModule activityModule) {

        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(activityModule)
                .build()
                .inject(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initFragmentData();
        setTabData();
        mPresenter.getImPass(Constants.userName);
    }

    /**
     * tab数据初始化
     */
    private void setTabData() {
        CustomTabView.Tab tabMsg = new CustomTabView.Tab().setText("消息")
                .setColor(getResources().getColor(R.color.color_8989a6))
                .setCheckedColor(getResources().getColor(R.color.color_f93967))
                .setNormalIcon(R.mipmap.icon_msg_select_un)
                .setPressedIcon(R.mipmap.icon_msg_select);

        mCustomTabView.addTab(tabMsg);

        CustomTabView.Tab tabConnect = new CustomTabView.Tab().setText("人脉")
                .setColor(getResources().getColor(R.color.color_8989a6))
                .setCheckedColor(getResources().getColor(R.color.color_f93967))
                .setNormalIcon(R.mipmap.icon_connection_select_un)
                .setPressedIcon(R.mipmap.icon_connection_select);

        mCustomTabView.addTab(tabConnect);

        CustomTabView.Tab tabWeiBo = new CustomTabView.Tab().setText("微博")
                .setColor(getResources().getColor(R.color.color_8989a6))
                .setCheckedColor(getResources().getColor(R.color.color_f93967))
                .setNormalIcon(R.mipmap.icon_vitas_select_un)
                .setPressedIcon(R.mipmap.icon_vitas_select);

        mCustomTabView.addTab(tabWeiBo);

        CustomTabView.Tab tabDynamic = new CustomTabView.Tab().setText("动态")
                .setColor(getResources().getColor(R.color.color_8989a6))
                .setCheckedColor(getResources().getColor(R.color.color_f93967))
                .setNormalIcon(R.mipmap.icon_dynamic_select_un)
                .setPressedIcon(R.mipmap.icon_dynamic_select);

        mCustomTabView.addTab(tabDynamic);

        CustomTabView.Tab tabMine = new CustomTabView.Tab().setText("我的")
                .setColor(getResources().getColor(R.color.color_8989a6))
                .setCheckedColor(getResources().getColor(R.color.color_f93967))
                .setNormalIcon(R.mipmap.icon_mine_select_un)
                .setPressedIcon(R.mipmap.icon_mine_select);

        mCustomTabView.addTab(tabMine);
        mCustomTabView.setOnTabCheckListener(this);
        mCustomTabView.setCurrentItem(0);
    }

    @Override
    public void onTabSelected(View v, int position) {
        viewpagerMain.setCurrentItem(position);
        switch (position){


        }
    }

    /**
     * fragment数据初始化
     */
    private void initFragmentData() {
        fragmentList.add(new MsgFragment());
        fragmentList.add(new ConnectingFragment());
        fragmentList.add(new WeiBoFragment());
        fragmentList.add(new DynamicFragment());
        fragmentList.add(new MineFragment());

        strList.add("消息");
        strList.add("人脉");
        strList.add("微博");
        strList.add("动态");
        strList.add("我的");
        mPresenter.initAdapter(getSupportFragmentManager(), fragmentList, strList, viewpagerMain);
        viewpagerMain.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCustomTabView.setCurrentItem(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
