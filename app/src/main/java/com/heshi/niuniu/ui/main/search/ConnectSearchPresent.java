package com.heshi.niuniu.ui.main.search;

import android.app.Activity;

import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.model.ConnectFriendModel;
import com.heshi.niuniu.model.ContactModel;
import com.heshi.niuniu.present.BasePresenter;
import com.heshi.niuniu.rx.data.RxResultHelper;
import com.heshi.niuniu.rx.data.SchedulersCompat;

import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

/**
 * Created by Administrator on 2018/7/13 0013.
 */

public class ConnectSearchPresent extends BasePresenter<ConnectSearchContract.Model>
        implements ConnectSearchContract.Presenter {

    private final ConnectSearchApi api;

    @Inject
    public ConnectSearchPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(Constants.Im_Url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit1.create(ConnectSearchApi.class);

    }


    @Override
    public void searchFriend(String name) {
        addSubscription(api.searchFriend(name)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber<ConnectFriendModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mModel.onSuccess(null);
                    }

                    @Override
                    public void onNext(ConnectFriendModel contactModels) {
                        mModel.onSuccess(contactModels);

                    }
                });
    }

    @Override
    public void findFriend(String friend_id) {
        addSubscription(api.findFriend(friend_id)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber<List<ContactModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<ContactModel> contactModels) {

                    }
                });
    }
}
