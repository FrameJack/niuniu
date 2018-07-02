package com.heshi.niuniu.ui.password.commit_pass;

import android.app.Activity;

import com.heshi.niuniu.present.BasePresenter;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class CommitPassPresent extends BasePresenter<CommitPassContract.Model>
        implements CommitPassContract.Presenter {

    @Inject
    public CommitPassPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
    }



}
