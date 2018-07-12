package com.heshi.niuniu.fragment.main.connect;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import com.heshi.niuniu.present.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class ConnectingPresent extends BasePresenter<ConnectingContract.Model>
        implements ConnectingContract.Presenter{

    @Inject
    public ConnectingPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
    }


    @Override
    public void upCurrListIndex(String key) {

    }

    @Override
    public void initAdapter(RecyclerView recyclerView, int type, List<Integer> newList, int moreFilter) {



    }
}
