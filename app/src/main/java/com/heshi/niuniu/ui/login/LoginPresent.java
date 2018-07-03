package com.heshi.niuniu.ui.login;

import android.app.Activity;
import android.util.Log;

import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.model.LoginModel;
import com.heshi.niuniu.present.BasePresenter;
import com.heshi.niuniu.rx.data.RxResultHelper;
import com.heshi.niuniu.rx.data.SchedulersCompat;
import com.heshi.niuniu.ui.main.MainActivity;
import com.heshi.niuniu.util.HttpDialog;
import com.heshi.niuniu.util.SharedHelper;
import com.heshi.niuniu.util.UIHelper;

import org.eclipse.jdt.internal.compiler.batch.Main;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Subscriber;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class LoginPresent extends BasePresenter<LoginContract.Model> implements LoginContract.Presenter {

    private final LoginApi api;
    private final HttpDialog dialog;

    @Inject
    public LoginPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
        api = retrofit.create(LoginApi.class);
        dialog = new HttpDialog(mActivity);

    }

    @Override
    public void loginAction(final String name, String password) {
        dialog.setMessage("登录中");
        dialog.show();

        addSubscription(api.login(name, password)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber<LoginModel>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();

                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();

                    }

                    @Override
                    public void onNext(LoginModel loginModel) {
                        if (loginModel != null) {
                            Constants.saveInfo(loginModel,name);
                            UIHelper.startActivity(mActivity, MainActivity.class);
                            mActivity.finish();
                        }
                        dialog.dismiss();

                    }
                });

    }
}
