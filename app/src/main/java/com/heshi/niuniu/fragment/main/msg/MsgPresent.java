package com.heshi.niuniu.fragment.main.msg;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.heshi.niuniu.R;
import com.heshi.niuniu.adapter.msg.MessageAdapter;
import com.heshi.niuniu.model.MessageListModel;
import com.heshi.niuniu.present.BasePresenter;
import com.heshi.niuniu.util.recyclerview.BaseMyRecyclerVIewAdapter;
import com.heshi.niuniu.util.recyclerview.DividerItemDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import static com.heshi.niuniu.util.UiUtils.dp2px;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class MsgPresent extends BasePresenter<MsgContract.Model>
        implements MsgContract.Presenter, BaseMyRecyclerVIewAdapter.CardListener {

    private List<MessageListModel> list = new ArrayList<>();
    private View view;
    private MessageAdapter adapter;
    private SwipeMenuBridge menuBridges;


    @Inject
    public MsgPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
    }


    @Override
    public void initAdapter(SwipeMenuRecyclerView recyclerView) {
        adapter = new MessageAdapter(mActivity, list);
        recyclerView.setSwipeMenuCreator(swipeMenuCreator);
        recyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

//        recyclerView.addItemDecoration(new DividerItemDecoration(mActivity,
//                LinearLayout.VERTICAL, dp2px(1, mActivity),
//                mActivity.getResources().getColor(R.color.color_E8E8E9)));

        recyclerView.setAdapter(adapter);

        view = LayoutInflater.from(mActivity).inflate(R.layout.layout_message_head, null);
        initHead(view);
        adapter.setCardListener(this);
    }

    private void initHead(View view) {


    }

    @Override
    public void getMsgList() {
        for (int i = 0; i < 6; i++) {
            MessageListModel model = new MessageListModel();
            list.add(model);
        }
        adapter.notifyDataSetChanged();

        adapter.setHeaderView(view);

    }

    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = dp2px(61, mActivity);

            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
//            int height = dp2px(117, mActivity);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            if (viewType == -2) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mActivity)
                        .setBackground(R.color.color_FC3768)
                        .setText("同意")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。
            }
        }
    };

    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridges = menuBridge;

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                MessageListModel data = adapter.getItem(adapterPosition);


            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
//                Toast.makeText(mActivity, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
//                sendOrder(orderList.get(adapterPosition).getId(), menuPosition);

            }

            menuBridges.closeMenu();
        }
    };


    @Override
    public void onCardListener(View view, int position) {

    }
}
