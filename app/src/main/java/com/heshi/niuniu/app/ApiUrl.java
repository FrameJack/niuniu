package com.heshi.niuniu.app;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class ApiUrl {

    public static final String Login = "token";

    //验证码获取
    public static final String getCode = "sms";

    //注册
    public static final String Register = "user";

    //获取验证码 --忘记密码
    public static final String getVerCode = "user/forget_password";

    //校验 验证码
    public static final String commitVerCode = "user/forget_password_2";

    //修改密码
    public static final String verPass = "user/forget_password_3";


    //获取im信息
    public static final String getImPass = "redfive/im/user.do";

    //获取基本信息
    public static final String getBaseInfo = "red/user/findfriend.do";

    //获取im信息 user_name
    public static final String getWeiBo = "redfive/blog/count_circle.do";

    //获取im信息 user_name
    public static final String getDynamic = "redfive/face/count_circle.do";

    //获取粉丝 user_id
    public static final String getFan = "red/user/count_friend.do";

    //获取红包  user_id
    public static final String getRedPacket = "red/payred/pay_count.do";

    //获取红包  user_id
    public static final String getImgUrl = "red/use/gethard_pic.do";

    //获取红包  user_id
    public static final String updataImg = "red/use/hard_pic.do";

}
