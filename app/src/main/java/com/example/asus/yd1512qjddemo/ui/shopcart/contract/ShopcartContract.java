package com.example.asus.yd1512qjddemo.ui.shopcart.contract;

import com.example.asus.yd1512qjddemo.bean.GetCartsBean;
import com.example.asus.yd1512qjddemo.bean.SellerBean;
import com.example.asus.yd1512qjddemo.ui.base.BaseContract;
import com.example.asus.yd1512qjddemo.ui.base.BaseContract.BaseView;

import java.util.List;

public interface ShopcartContract {
    interface View extends BaseView{
        void showCartList(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList);

        void updateCartsSuccess(String msg);

        void deleteCartSuccess(String msg);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getCarts(String uid, String token);

        void updateCarts(String uid, String sellerid, String pid, String num, String selected, String token);

        void deleteCart(String uid, String pid, String token);
    }
}
