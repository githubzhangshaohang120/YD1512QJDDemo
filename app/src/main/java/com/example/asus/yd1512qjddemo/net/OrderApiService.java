package com.example.asus.yd1512qjddemo.net;

import com.example.asus.yd1512qjddemo.bean.BaseBean;
import com.example.asus.yd1512qjddemo.bean.OrdersBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OrderApiService {
    @FormUrlEncoded
    @POST("product/getOrders")
    Observable<OrdersBean> getOrders(@Field("Uid") String uid,
                                     @Field("Page") String page,
                                     @Field("Token") String token);

    @FormUrlEncoded
    @POST("product/updateOrder")
    Observable<BaseBean> updateOrder(@Field("uid") String uid,
                                     @Field("status") String status,
                                     @Field("orderId") String orderId,
                                     @Field("token") String token);

}
