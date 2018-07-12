package com.heshi.niuniu.fragment.main.connect;

import com.heshi.niuniu.app.ApiUrl;
import com.heshi.niuniu.model.Response;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public interface ConnectingApi {

    @FormUrlEncoded
    @POST(ApiUrl.getAllFriend)
    Observable<Response<Object>> getAllFriend(@Field("name") String name);


}
