package com.heshi.niuniu.fragment.main.msg;

import android.app.Activity;

import com.heshi.niuniu.present.BasePresenter;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class MsgPresent extends BasePresenter<MsgContract.Model>
        implements MsgContract.Presenter{

    @Inject
    public MsgPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
    }


}
