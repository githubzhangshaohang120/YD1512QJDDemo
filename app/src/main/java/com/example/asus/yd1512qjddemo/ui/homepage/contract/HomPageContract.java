package com.example.asus.yd1512qjddemo.ui.homepage.contract;

import com.example.asus.yd1512qjddemo.bean.AdBean;
import com.example.asus.yd1512qjddemo.bean.CatagoryBean;
import com.example.asus.yd1512qjddemo.ui.base.BaseContract;

public interface HomPageContract {
    interface View extends BaseContract.BaseView{
        void  getAdSuccess(AdBean adBean);
        void  getCatagorySuccess(CatagoryBean catagoryBean);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getAd();
        void getCatagory();
    }
}
