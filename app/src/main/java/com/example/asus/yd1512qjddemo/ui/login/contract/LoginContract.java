package com.example.asus.yd1512qjddemo.ui.login.contract;


import com.example.asus.yd1512qjddemo.bean.UserBean;
import com.example.asus.yd1512qjddemo.ui.base.BaseContract;

public interface LoginContract {
    interface View  extends BaseContract.BaseView{
        //成功
        void loginSuccess(UserBean userBean);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        //登录
        void login(String mobile,String password);
    }
}
