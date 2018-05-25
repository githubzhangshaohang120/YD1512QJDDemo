package com.example.asus.yd1512qjddemo.ui.classify.contract;

import com.example.asus.yd1512qjddemo.ui.base.BaseContract;

public interface AddCartContract {
    interface View extends BaseContract.BaseView{
        void onSuccess(String str);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void addCart(String uid,String pid,String token);
    }
}
