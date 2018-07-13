package com.heshi.niuniu.ui.main.friend_info;

import android.app.Activity;
import android.util.Log;

import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.fragment.main.mine.MineApi;
import com.heshi.niuniu.model.BaseInfoModel;
import com.heshi.niuniu.present.BasePresenter;
import com.heshi.niuniu.rx.data.RxResultHelper;
import com.heshi.niuniu.rx.data.SchedulersCompat;
import com.heshi.niuniu.util.HttpDialog;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

/**
 * Created by Administrator on 2018/7/13 0013.
 */

public class FriendInfoPresent extends BasePresenter<FriendInfoContract.Model>
        implements FriendInfoContract.Presenter {

    private final FriendInfoApi api;
    private final HttpDialog dialog;

    @Inject
    public FriendInfoPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(Constants.Im_Url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit1.create(FriendInfoApi.class);

        dialog = new HttpDialog(mActivity);

    }


    @Override
    public void getBaseInfo(String name) {
        addSubscription(api.getBaseInfo(name)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber<BaseInfoModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseInfoModel baseInfoModel) {
                        Log.e("slfs", baseInfoModel + "");
                        mModel.setBaseInfo(baseInfoModel);

                    }
                });

    }

    @Override
    public void addFriendAction(String name, String id) {
        dialog.show();

        addSubscription(api.addFriend(name, id)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        dialog.dismiss();

                        if (o != null) {

                        }

                    }
                });

    }

}
