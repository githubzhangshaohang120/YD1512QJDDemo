package com.example.asus.yd1512qjddemo.ui.classify.contract;

import com.example.asus.yd1512qjddemo.bean.ProductsBean;
import com.example.asus.yd1512qjddemo.ui.base.BaseContract;

import java.util.List;

public interface ListContract {
    interface View extends BaseContract.BaseView {
        void onSuccess(List<ProductsBean.DataBean> list);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getProducts(String pscid);
    }
}
