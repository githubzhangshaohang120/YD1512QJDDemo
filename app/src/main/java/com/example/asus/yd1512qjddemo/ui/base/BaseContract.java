package com.example.asus.yd1512qjddemo.ui.base;

public interface BaseContract {

    //抽取所有Presenter的共性，比如绑定和解绑
    interface BasePresenter<T extends BaseView>{
        //绑定
        void attchView(T view);
        //解绑
        void detachView();
    }

    //抽取所有view的共性，比如显示进度条和关闭进度条
    interface BaseView {
        //显示进度条
        void showLoading();
        //关闭进度条
        void dismissLoading();
    }
}
