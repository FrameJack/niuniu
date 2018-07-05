package com.heshi.niuniu.fragment.main.msg;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMCore;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.conversation.IYWConversationListener;
import com.alibaba.mobileim.conversation.IYWConversationService;
import com.alibaba.mobileim.conversation.IYWConversationUnreadChangeListener;
import com.alibaba.mobileim.conversation.YWConversation;
import com.heshi.niuniu.R;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.BaseFragment;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerFragmentComponent;
import com.heshi.niuniu.di.module.FragmentModule;
import com.heshi.niuniu.im.core.InitSample;
import com.heshi.niuniu.im.sample.LoginSampleHelper;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class MsgFragment extends BaseFragment<MsgPresent>
        implements MsgContract.Model {


    @BindView(R.id.rec_msg)
    SwipeMenuRecyclerView recMsg;
    private IYWConversationService mConversationService;
    private Handler mUIHandler = new Handler(Looper.getMainLooper());
    private YWIMKit mIMKit;


    @Override
    protected void setupActivityComponent(AppComponent appComponent, FragmentModule fragmentModule) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .fragmentModule(fragmentModule)
                .build()
                .inject(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_message;
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.initAdapter(recMsg);

//        YWIMCore mIMCore = InitSample.getInstance().getIMCore();

        YWIMCore mIMCore = YWAPI.createIMCore(Constants.im_usrName, Constants.appkey);

        if (mIMCore != null) {
            mConversationService = mIMCore.getConversationService();
            //初始化最近联系人列表
            List<YWConversation> mConversationList = mConversationService.getConversationList();

//            Log.e("sda", mConversationList.get(0).getConversationId() + "");
            if (mConversationList != null) {
                mPresenter.getMsgList(mConversationList);
            }
            //添加会话列表变更监听，收到该监听回调时更新adapter就可以刷新页面了
            mConversationService.addConversationListener(mConversationListener);
        }

        mIMKit = LoginSampleHelper.getInstance().getIMKit();
        if (mIMKit == null) {
            return;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        syncRecentConversations();
    }

    IYWConversationListener mConversationListener = new IYWConversationListener() {
        @Override
        public void onItemUpdated() {
            mUIHandler.post(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
    };

    private void syncRecentConversations() {
        mConversationService.syncRecentConversations(new IWxCallback() {
            @Override
            public void onSuccess(Object... result) {
                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.setNotify();

                    }
                });
            }

            @Override
            public void onError(int code, String info) {
                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.setNotify();

                    }
                });
            }

            @Override
            public void onProgress(int progress) {

            }
        });
    }

//    private void initConversationServiceAndListener() {
//        mConversationUnreadChangeListener = new IYWConversationUnreadChangeListener() {
//
//            //当未读数发生变化时会回调该方法，开发者可以在该方法中更新未读数
//            @Override
//            public void onUnreadChange() {
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        LoginSampleHelper loginHelper = LoginSampleHelper.getInstance();
//                        final YWIMKit imKit = loginHelper.getIMKit();
//                        mConversationService = imKit.getConversationService();
//                        //获取当前登录用户的所有未读数
//                        int unReadCount = mConversationService.getAllUnreadCount();
//                        //设置桌面角标的未读数
//                        mIMKit.setShortcutBadger(unReadCount);
//                        if (unReadCount > 0) {
//                            mUnread.setVisibility(View.VISIBLE);
//                            if (unReadCount < 100) {
//                                mUnread.setText(unReadCount + "");
//                            } else {
//                                mUnread.setText("99+");
//                            }
//                        } else {
//                            mUnread.setVisibility(View.INVISIBLE);
//                        }
//                    }
//                });
//            }
//        };
//        mConversationService.addTotalUnreadChangeListener(mConversationUnreadChangeListener);
//    }

}
