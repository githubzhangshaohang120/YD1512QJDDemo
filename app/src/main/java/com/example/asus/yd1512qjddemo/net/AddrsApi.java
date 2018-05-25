package com.example.asus.yd1512qjddemo.net;

import com.example.asus.yd1512qjddemo.bean.AddrsBean;

import io.reactivex.Observable;

public class AddrsApi {
    private static AddrsApi addrsApi;
    private AddrsApiService addrsApiService;

    private AddrsApi(AddrsApiService addrsApiService) {
        this.addrsApiService = addrsApiService;
    }

    public static AddrsApi getAddrsApi(AddrsApiService addrsApiService) {
        if (addrsApi == null) {
            addrsApi = new AddrsApi(addrsApiService);
        }
        return addrsApi;
    }

    public Observable<AddrsBean> getAddrs(String uid, String token) {
        return addrsApiService.getAddrs(uid, token);
    }

}
