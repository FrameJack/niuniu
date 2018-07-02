package com.heshi.niuniu.adapter.msg;

import android.content.Context;

import com.heshi.niuniu.R;
import com.heshi.niuniu.model.MessageListModel;
import com.heshi.niuniu.util.recyclerview.BaseMyRecyclerVIewAdapter;
import com.heshi.niuniu.util.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/7/2 0002.
 */

public class MessageAdapter extends BaseMyRecyclerVIewAdapter<MessageListModel> {

    public MessageAdapter(Context context, List<MessageListModel> list) {
        super(context, list, R.layout.item_message);
    }

    @Override
    protected void covert(MessageListModel mode, BaseViewHolder holder, int position) {
        super.covert(mode, holder, position);


    }
}
