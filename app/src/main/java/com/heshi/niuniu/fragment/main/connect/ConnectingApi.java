package com.heshi.niuniu.fragment.main.connect;

import com.heshi.niuniu.app.ApiUrl;
import com.heshi.niuniu.model.ContactModel;
import com.heshi.niuniu.model.Response;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public interface ConnectingApi {

    @FormUrlEncoded
    @POST(ApiUrl.getAllFriend)
    Observable<Response<List<ContactModel>>> getAllFriend(@Field("name") String name);

    @GET(ApiUrl.getIconUrl)
    Observable<Response<String>> getImgUrl(@Query("user_id") String user_id);

}
