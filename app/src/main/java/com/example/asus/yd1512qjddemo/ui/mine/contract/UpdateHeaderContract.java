package com.example.asus.yd1512qjddemo.ui.mine.contract;


import com.example.asus.yd1512qjddemo.ui.base.BaseContract;

public interface UpdateHeaderContract {

    interface View extends BaseContract.BaseView{
        void updateSuccess(String code);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void updateHeader(String uid, String filePath);
    }
}
