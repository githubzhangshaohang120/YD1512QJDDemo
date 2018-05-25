package com.example.asus.yd1512qjddemo.ui.base;


import com.example.asus.yd1512qjddemo.ui.login.contract.LoginContract;

//所有Presenter的积累，实现了BaseContract.BasePresenter接口
public class BasePresenter<T extends BaseContract.BaseView>  implements BaseContract.BasePresenter<T> {

    protected T mview;

    //绑定
    @Override
    public void attchView(T view) {
        this.mview=view;
    }
    //解绑
    @Override
    public void detachView() {
        if (mview!=null){
            mview=null;
        }
    }
}
