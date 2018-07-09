package com.heshi.niuniu.ui.main;

import android.Manifest;
import android.animation.Animator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qrcode.Constant;
import com.example.qrcode.ScannerActivity;
import com.heshi.niuniu.R;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.custom.tab.CustomTabView;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.fragment.main.connect.ConnectingFragment;
import com.heshi.niuniu.fragment.main.dynamic.DynamicFragment;
import com.heshi.niuniu.fragment.main.mine.MineFragment;
import com.heshi.niuniu.fragment.main.msg.MsgFragment;
import com.heshi.niuniu.fragment.main.weibo.WeiBoFragment;
import com.heshi.niuniu.util.AnimUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.heshi.niuniu.util.UiUtils.dp2px;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class MainActivity extends BaseActivity<MainPresent> implements CustomTabView.OnTabCheckListener, ViewPager.OnPageChangeListener, View.OnClickListener {


    @BindView(R.id.viewpager_main)
    ViewPager viewpagerMain;
    @BindView(R.id.mCustomTabView)
    CustomTabView mCustomTabView;
    @BindView(R.id.img_add_right)
    ImageView imgAddRight;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.t_title)
    Toolbar tTitle;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> strList = new ArrayList<>();
    private AnimUtil animUtil;
    private PopupWindow mPopupWindow;
    private static final long DURATION = 100;
    private static final float START_ALPHA = 0.7f;
    private static final float END_ALPHA = 1f;
    private float bgAlpha = 1f;
    private boolean bright = false;
    private TextView tv_1, tv_2, tv_3;
    private final int REQUEST_PERMISION_CODE_CAMARE = 0;
    private final int RESULT_REQUEST_CODE = 1;


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
        initPop();
        if (TextUtils.isEmpty(Constants.im_usrName)) {
            mPresenter.getImPass(Constants.userName);

        } else {
            if (!Constants.isLoginIm) {
                mPresenter.loginIm(Constants.im_usrName, Constants.im_pass, Constants.appkey);
            }
        }

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
        imgAddRight.setVisibility(View.GONE);

        switch (position) {
            case 0:
                imgAddRight.setVisibility(View.VISIBLE);

                textTitle.setText("消息");

                break;
            case 1:
                textTitle.setText("人脉");

                break;
            case 2:
                textTitle.setText("微博");

                break;
            case 3:
                textTitle.setText("动态");

                break;
            case 4:
                textTitle.setText("我的");

                break;
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
        viewpagerMain.setOffscreenPageLimit(5);
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

    @OnClick(R.id.img_add_right)
    public void onViewClicked() {
        showPop();
        toggleBright();
    }

    private void initPop() {
        mPopupWindow = new PopupWindow(this);
        animUtil = new AnimUtil();
    }

    private void showPop() {
        // 设置布局文件
        mPopupWindow.setContentView(LayoutInflater.from(this).inflate(R.layout.pop_add, null));
        // 为了避免部分机型不显示，我们需要重新设置一下宽高
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置pop透明效果
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x0000));
        // 设置pop出入动画
        mPopupWindow.setAnimationStyle(R.style.pop_add);
        // 设置pop获取焦点，如果为false点击返回按钮会退出当前Activity，如果pop中有Editor的话，focusable必须要为true
        mPopupWindow.setFocusable(true);
        // 设置pop可点击，为false点击事件无效，默认为true
        mPopupWindow.setTouchable(true);
        // 设置点击pop外侧消失，默认为false；在focusable为true时点击外侧始终消失
        mPopupWindow.setOutsideTouchable(true);
        // 相对于 + 号正下面，同时可以设置偏移量
        mPopupWindow.showAsDropDown(imgAddRight, -100, 0);

//        mPopupWindow.showAtLocation(tTitle, 0&Gravity.RIGHT, 0, 0);
        // 设置pop关闭监听，用于改变背景透明度
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                toggleBright();
            }
        });

        tv_1 = (TextView) mPopupWindow.getContentView().findViewById(R.id.tv_1);
        tv_2 = (TextView) mPopupWindow.getContentView().findViewById(R.id.tv_2);
        tv_3 = (TextView) mPopupWindow.getContentView().findViewById(R.id.tv_3);


        tv_1.setOnClickListener(this);
        tv_2.setOnClickListener(this);
        tv_3.setOnClickListener(this);

    }

    private void toggleBright() {
        // 三个参数分别为：起始值 结束值 时长，那么整个动画回调过来的值就是从0.5f--1f的
        animUtil.setValueAnimator(START_ALPHA, END_ALPHA, DURATION);
        animUtil.addUpdateListener(new AnimUtil.UpdateListener() {
            @Override
            public void progress(float progress) {
                // 此处系统会根据上述三个值，计算每次回调的值是多少，我们根据这个值来改变透明度
                bgAlpha = bright ? progress : (START_ALPHA + END_ALPHA - progress);
                backgroundAlpha(bgAlpha);
            }
        });
        animUtil.addEndListner(new AnimUtil.EndListener() {
            @Override
            public void endUpdate(Animator animator) {
                // 在一次动画结束的时候，翻转状态
                bright = !bright;
            }
        });
        animUtil.startAnimator();
    }

    /**
     * 此方法用于改变背景的透明度，从而达到“变暗”的效果
     */
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        // 0.0-1.0
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
        // everything behind this window will be dimmed.
        // 此方法用来设置浮动层，防止部分手机变暗无效
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_1:
                mPopupWindow.dismiss();

                break;
            case R.id.tv_2:
                mPopupWindow.dismiss();

                break;
            case R.id.tv_3:
                mPopupWindow.dismiss();
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    goScanner();
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISION_CODE_CAMARE);
                }

                break;
        }
    }

    private void goScanner() {
        Intent intent = new Intent(this, ScannerActivity.class);
        //这里可以用intent传递一些参数，比如扫码聚焦框尺寸大小，支持的扫码类型。
        //设置扫码框的宽
        intent.putExtra(Constant.EXTRA_SCANNER_FRAME_WIDTH, dp2px(260, mContext));
        //设置扫码框的高
        intent.putExtra(Constant.EXTRA_SCANNER_FRAME_HEIGHT, dp2px(260, mContext));
//        //设置扫码框距顶部的位置
        intent.putExtra(Constant.EXTRA_SCANNER_FRAME_TOP_PADDING, dp2px(150, mContext));
//        //设置是否启用从相册获取二维码。
//        intent.putExtra(Constant.EXTRA_IS_ENABLE_SCAN_FROM_PIC,true);
//        Bundle bundle = new Bundle();
//        //设置支持的扫码类型
//        bundle.putSerializable(Constant.EXTRA_SCAN_CODE_TYPE, mHashMap);
//        intent.putExtras(bundle);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISION_CODE_CAMARE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goScanner();
                }
                return;
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_REQUEST_CODE:
                    if (data == null) return;
                    String type = data.getStringExtra(Constants.EXTRA_RESULT_CODE_TYPE);
                    String content = data.getStringExtra(Constants.EXTRA_RESULT_CONTENT);
                    Toast.makeText(this, "codeType:" + type
                            + "-----content:" + content, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void setSelectTab(int index) {
        viewpagerMain.setCurrentItem(index);
    }

}
