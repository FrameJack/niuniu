package com.heshi.niuniu.di.module;

import android.text.TextUtils;
import android.util.Log;


import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.MyApplication;
import com.heshi.niuniu.cache.CacheLoader;
import com.heshi.niuniu.di.ContextLife;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 初始化网络请求
 */

@Module
public class AppModule {

    private MyApplication app;
    Request         request;
    Request.Builder requestBuilder;

    public AppModule(MyApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    @ContextLife("Application")
    public MyApplication provideApp() {
        return app;
    }

    @Provides
    @Singleton
    CacheLoader provideCacheLoader() {
        return CacheLoader.getInstance(app.getApplicationContext());
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        //数据拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("message", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                request = chain.request()
                        .newBuilder()
                        .addHeader("appId", "ANDROID-1.0.0")
                        /**
                         * 若存在用户cookie则带入请求头
                         */
//                        .addHeader("cookie", TextUtils.isEmpty(Constants.USER_COOKIE) ? "" : Constants.USER_COOKIE)
                        //  .addHeader("token",MyApplication.appToken)
                        .build();

                Response response = chain.proceed(request);

//                if (response.request().url().toString().indexOf("login") != -1) {
//                    loginSaveCookie(response);
//                }
                return response;
            }
        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(Constants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(Constants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(interceptor)
                .build();


        return okHttpClient;
    }

    /**
     * 处理login的cookie
     */
    private void loginSaveCookie(Response originalResponse) throws IOException {
        Headers      httpHead   = originalResponse.headers();
        List<String> cookieHead = originalResponse.headers("Set-Cookie");
        if (cookieHead != null) {
            for (String cache : cookieHead) {
                if (!TextUtils.isEmpty(cache)) {
                    String[] arrays = cache.split(";");
                    if (arrays != null && arrays.length > 0) {
                        /**
                         * 将cookie放到用户信息中
                         */
                        String cookie = arrays[0];
                        String url    = originalResponse.request().url().toString();
                        if (!TextUtils.isEmpty(url) && url.indexOf("login") != -1) {
                            Log.e("cookie", cookie);
//                            Constants.saveUserCookie(cookie);
                        }
                    }
                }
            }
        }
        //        originalResponse.close();//这个没有用的就是关闭请求而已
    }

    @Provides
    @Singleton
    Retrofit provideHotApi(OkHttpClient okHttpClient) {
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(Constants.Base_Url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit1;
    }


}
