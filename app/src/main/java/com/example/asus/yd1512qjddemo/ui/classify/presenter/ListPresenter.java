package com.example.asus.yd1512qjddemo.ui.classify.presenter;

import com.example.asus.yd1512qjddemo.bean.ProductsBean;
import com.example.asus.yd1512qjddemo.net.ListApi;
import com.example.asus.yd1512qjddemo.ui.base.BasePresenter;
import com.example.asus.yd1512qjddemo.ui.classify.contract.ListContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ListPresenter extends BasePresenter<ListContract.View> implements ListContract.Presenter {
    private ListApi listApi;

    @Inject
    public ListPresenter(ListApi listApi) {
        this.listApi = listApi;
    }

    @Override
    public void getProducts(String pscid) {
        listApi.getProduct(pscid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ProductsBean, List<ProductsBean.DataBean>>() {
                    @Override
                    public List<ProductsBean.DataBean> apply(ProductsBean productsBean) throws Exception {
                        return productsBean.getData();
                    }
                }).subscribe(new Consumer<List<ProductsBean.DataBean>>() {
            @Override
            public void accept(List<ProductsBean.DataBean> dataBeans) throws Exception {
                if (mview != null) {
                    mview.onSuccess(dataBeans);
                }
            }
        });

    }
}
