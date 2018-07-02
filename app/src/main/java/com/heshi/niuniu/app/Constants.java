package com.heshi.niuniu.app;

import android.text.TextUtils;

import com.heshi.niuniu.model.LoginModel;
import com.heshi.niuniu.util.SharedHelper;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class Constants {

    public static final String Base_Url = "http://api.zhimaim.com/api/";//正式

    //network time
    public static final int HTTP_CONNECT_TIMEOUT = 16000;

    public static String UUID = "";

    public static String access_token="";

    public static final String TOKEN_KEY="token";


    public static void saveInfo(LoginModel loginModel){
        SharedHelper.put(Constants.TOKEN_KEY,loginModel.getAccess_token());

    }

    public static void  readInfo(){
        access_token=SharedHelper.getString(TOKEN_KEY,"");

    }

}
