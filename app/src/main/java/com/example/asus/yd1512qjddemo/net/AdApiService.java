package com.example.asus.yd1512qjddemo.net;

import com.example.asus.yd1512qjddemo.bean.AdBean;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface AdApiService {
    @GET("ad/getAd")
    Observable<AdBean> AdApi();
}
