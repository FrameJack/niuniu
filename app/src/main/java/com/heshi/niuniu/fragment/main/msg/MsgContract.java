package com.heshi.niuniu.fragment.main.msg;

import android.support.v7.widget.RecyclerView;

import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class MsgContract {

    interface Model extends MModel {

    }

    interface Presenter extends IPresenter<Model> {
        void initAdapter(SwipeMenuRecyclerView recyclerView);
        void getMsgList();

    }

}
