package com.heshi.niuniu.ui.main.search;

import com.heshi.niuniu.app.ApiUrl;
import com.heshi.niuniu.model.ConnectFriendModel;
import com.heshi.niuniu.model.ContactModel;
import com.heshi.niuniu.model.Response;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/7/13 0013.
 */

public interface ConnectSearchApi {

    @FormUrlEncoded
    @POST(ApiUrl.searchFriend)
    Observable<Response<ConnectFriendModel>> searchFriend(@Field("name") String name);

    @FormUrlEncoded
    @POST(ApiUrl.findFriend)
    Observable<Response<List<ContactModel>>> findFriend(@Field("friend_id") String friend_id);

}
