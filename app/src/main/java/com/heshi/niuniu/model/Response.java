package com.heshi.niuniu.model;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by min on 2017/3/1.
 */
public class Response<T> implements Serializable {
    public static final int SUCCESS_CODE = -1;

    public String result;
    public String message;
    public T data;
    private int code = -1;
    private boolean success;
    private boolean isSuccess;

    public boolean code() {
        if (!TextUtils.isEmpty(message) && success == false) {
            try {
                success = isSuccess;
            } catch (Exception e) {
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}


