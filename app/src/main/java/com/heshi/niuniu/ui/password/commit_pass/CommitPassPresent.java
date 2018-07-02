package com.heshi.niuniu.ui.password.commit_pass;

import android.app.Activity;

import com.heshi.niuniu.present.BasePresenter;
import com.heshi.niuniu.rx.data.RxResultHelper;
import com.heshi.niuniu.rx.data.SchedulersCompat;
import com.heshi.niuniu.ui.login.LoginActivity;
import com.heshi.niuniu.util.HttpDialog;
import com.heshi.niuniu.util.ToashUtils;
import com.heshi.niuniu.util.UIHelper;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Subscriber;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class CommitPassPresent extends BasePresenter<CommitPassContract.Model>
        implements CommitPassContract.Presenter {

    private  CommitPassApi api;
    private  HttpDialog dialog;

    @Inject
    public CommitPassPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
        api = retrofit.create(CommitPassApi.class);
        dialog = new HttpDialog(mActivity);

    }


    @Override
    public void verPass(String verifyCode, String newPwd) {
        addSubscription(api.verPass(verifyCode, newPwd)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        ToashUtils.show(mActivity,"修改成功！");
                        UIHelper.startActivity(mActivity, LoginActivity.class);
                    }
                });

    }
}
