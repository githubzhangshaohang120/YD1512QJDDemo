package com.example.asus.yd1512qjddemo.ui.classify.presenter;

import com.example.asus.yd1512qjddemo.bean.BaseBean;
import com.example.asus.yd1512qjddemo.net.AddCartApi;
import com.example.asus.yd1512qjddemo.ui.base.BasePresenter;
import com.example.asus.yd1512qjddemo.ui.classify.contract.AddCartContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AddCartPresenter extends BasePresenter<AddCartContract.View> implements AddCartContract.Presenter{

    private AddCartApi addCartApi;
    @Inject
    public AddCartPresenter(AddCartApi addCartApi){
        this.addCartApi=addCartApi;
    }

    @Override
    public void addCart(String uid, String pid, String token) {
        addCartApi.getCatagory(uid, pid, token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mview!=null){
                    mview.onSuccess(s);
                }
            }
        });

    }
}
