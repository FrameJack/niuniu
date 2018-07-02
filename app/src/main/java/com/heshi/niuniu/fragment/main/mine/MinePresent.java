package com.heshi.niuniu.fragment.main.mine;

import android.app.Activity;

import com.heshi.niuniu.present.BasePresenter;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class MinePresent extends BasePresenter<MineContract.Model>
        implements MineContract.Presenter{

    @Inject
    public MinePresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
    }


}
