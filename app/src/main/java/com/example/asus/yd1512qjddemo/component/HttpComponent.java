package com.example.asus.yd1512qjddemo.component;


import android.app.ListActivity;

import com.example.asus.yd1512qjddemo.model.HttpModule;
import com.example.asus.yd1512qjddemo.ui.classify.ClassifyFragment;
import com.example.asus.yd1512qjddemo.ui.classify.ListDetailsActivity;
import com.example.asus.yd1512qjddemo.ui.homepage.HomePageFragment;
import com.example.asus.yd1512qjddemo.ui.login.LoginActivity;
import com.example.asus.yd1512qjddemo.ui.mine.MakeSureOrderActivity;
import com.example.asus.yd1512qjddemo.ui.mine.OrderListActivity;
import com.example.asus.yd1512qjddemo.ui.mine.UserInfoActivity;
import com.example.asus.yd1512qjddemo.ui.mine.fragment.FragmentAllOrder;
import com.example.asus.yd1512qjddemo.ui.mine.fragment.FragmentWaitOrder;
import com.example.asus.yd1512qjddemo.ui.shopcart.ShopCartActivity;
import com.example.asus.yd1512qjddemo.ui.shopcart.ShopCartActivity2;

import dagger.Component;

@Component(modules = HttpModule.class)
public interface HttpComponent {
    void inject(LoginActivity loginActivity);
    void inject(HomePageFragment homePageFragment);
    void inject(ClassifyFragment classifyFragment);
    void inject(com.example.asus.yd1512qjddemo.ui.classify.ListActivity listActivity);
    void inject(ListDetailsActivity listDetailsActivity);
    void inject(ShopCartActivity shopCartActivity);
    void inject(ShopCartActivity2 shopCartActivity2);
    void inject(MakeSureOrderActivity makeSureOrderActivity);

    void inject(UserInfoActivity userInfoActivity);

    void inject(FragmentAllOrder fragmentAllOrder);

    void inject(FragmentWaitOrder fragmentWaitOrder);

}
