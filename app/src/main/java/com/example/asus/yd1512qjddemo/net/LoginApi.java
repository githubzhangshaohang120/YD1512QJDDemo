package com.example.asus.yd1512qjddemo.net;

import com.example.asus.yd1512qjddemo.bean.UserBean;

import io.reactivex.Observable;

public class LoginApi {
    private static LoginApi loginApi;
    private LoginApiService loginApiService;

    public LoginApi(LoginApiService loginApiService){
        this.loginApiService=loginApiService;
    }
    public static LoginApi getLoginApi(LoginApiService loginApiService) {
        if (loginApi==null){
            loginApi=new LoginApi(loginApiService);
        }
        return loginApi;
    }
    public Observable<UserBean> login(String mobile,String password){
        return loginApiService.login(mobile, password);
    }
}
