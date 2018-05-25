package com.example.asus.yd1512qjddemo.ui.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dash.zxinglibrary.activity.CaptureActivity;
import com.dash.zxinglibrary.activity.CodeUtils;
import com.example.asus.yd1512qjddemo.R;
import com.example.asus.yd1512qjddemo.WebViewActivity;
import com.example.asus.yd1512qjddemo.bean.AdBean;
import com.example.asus.yd1512qjddemo.bean.CatagoryBean;
import com.example.asus.yd1512qjddemo.component.DaggerHttpComponent;
import com.example.asus.yd1512qjddemo.inter.OnItemClickListener;
import com.example.asus.yd1512qjddemo.model.HttpModule;
import com.example.asus.yd1512qjddemo.ui.classify.ListDetailsActivity;
import com.example.asus.yd1512qjddemo.ui.base.BaseFragment;
import com.example.asus.yd1512qjddemo.ui.homepage.adapter.RvClassAdapter;
import com.example.asus.yd1512qjddemo.ui.homepage.adapter.RvRecommendAdapter;
import com.example.asus.yd1512qjddemo.ui.homepage.adapter.RvSecKillAdapter;
import com.example.asus.yd1512qjddemo.ui.homepage.contract.HomPageContract;
import com.example.asus.yd1512qjddemo.ui.homepage.presenter.HomePagePresenter;
import com.example.asus.yd1512qjddemo.utils.GlideImageLoader;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


public class HomePageFragment extends BaseFragment<HomePagePresenter> implements HomPageContract.View, View.OnClickListener {


    private View view;
    private ImageView ivZxing;
    private Banner banner;
    private MarqueeView marqueeView;
    private RecyclerView rvClass;
    private RecyclerView rvSecKill;
    private RecyclerView rvRecommend;
    public static final int HOMEPAGE_FRAGMENT = 0;
    private GridLayoutManager gridLayoutManager1;
    private GridLayoutManager gridLayoutManager2;
    private GridLayoutManager gridLayoutManager;

    @Override
    public int getContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);

    }

    @Override
    public void initView(View view) {
        //速度跑马灯
        marqueeView = view.findViewById(R.id.marqueeView);
        List<String> info=new ArrayList<String>();
        info.add("1.大家好，我是张少行。");
        info.add("2.欢迎大家关注我哦！");
        info.add("3.只要你喜欢，我们一起分析");
        info.add("4.懂得感恩才能持久！");
        info.add("5.新的一天，新的心情");
        info.add("6.不一样的样貌，不一样的人生。");
        marqueeView.startWithList(info);


        rvClass = view.findViewById(R.id.rvClass);
        //设置布局管理器
        gridLayoutManager = new GridLayoutManager(getContext(),2, RecyclerView.HORIZONTAL,false);
        rvClass.setLayoutManager(gridLayoutManager);


        rvSecKill = view.findViewById(R.id.rvSecKill);
        //设置布局管理器
        gridLayoutManager1 = new GridLayoutManager(getContext(),1, RecyclerView.HORIZONTAL,false);
        rvSecKill.setLayoutManager(gridLayoutManager1);

        rvRecommend = view.findViewById(R.id.rvRecommend);
        gridLayoutManager2 = new GridLayoutManager(getContext(),2, RecyclerView.VERTICAL,false);
        rvRecommend.setLayoutManager(gridLayoutManager2);


        //扫码
        ivZxing = (ImageView) view.findViewById(R.id.ivZxing);
        //轮播
        banner = view.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        //请求数据
        initData();

        //设置点击事件
        setListener();
    }

    private void setListener() {
        ivZxing.setOnClickListener(this);
    }

    //请求数据
    private void initData() {
        mPresenter.getAd();
        mPresenter.getCatagory();
    }


    @Override
    public void getAdSuccess(final AdBean adBean) {

        final List<AdBean.DataBean> data = adBean.getData();
        List<String> images = new ArrayList<String>();
        for (int i = 0; i < data.size(); i++) {
            images.add(data.get(i).getIcon());
        }

        //设置图片
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        //给banner设置点击事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                String url = data.get(position).getUrl();
                if (!TextUtils.isEmpty(url)) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("detailUrl", url);
                    startActivity(intent);
                }
            }
        });




        RvSecKillAdapter rvSecKillAdapter = new RvSecKillAdapter(getActivity(), adBean.getMiaosha().getList());
        rvSecKill.setAdapter(rvSecKillAdapter);

        rvSecKillAdapter.setOnItemClickListener(new OnItemClickListener() {


            @Override
            public void onItemClick(int position) {
                //跳转显示详情
                //获取
                //            }地址
                String detailUrl = adBean.getMiaosha().getList().get(position).getDetailUrl();
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("detailUrl", detailUrl);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });

        RvRecommendAdapter rvRecommendAdapter = new RvRecommendAdapter(getActivity(), adBean.getTuijian().getList());
        rvRecommend.setAdapter(rvRecommendAdapter);

        rvRecommendAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //跳转到详情页
                Intent intent = new Intent(getActivity(), ListDetailsActivity.class);
                AdBean.TuijianBean.ListBean listBean = adBean.getTuijian().getList().get(position);
                intent.putExtra("flag", HOMEPAGE_FRAGMENT);
                intent.putExtra("bean", listBean);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void getCatagorySuccess(CatagoryBean catagoryBean) {
        final List<CatagoryBean.DataBean> data = catagoryBean.getData();
        RvClassAdapter rvClassAdapter = new RvClassAdapter(getActivity(), data);
        rvClass.setAdapter(rvClassAdapter);
        rvClassAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), data.get(position).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });
    }

    //二维码点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivZxing:
                Intent intent=new Intent(getContext(),CaptureActivity.class);
                startActivityForResult(intent,1);
            break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if ( requestCode == 1 && data != null){
            Bundle bundle=data.getExtras();
            if (bundle.getInt(CodeUtils.RESULT_TYPE)==CodeUtils.RESULT_SUCCESS){
                String string = bundle.getString(CodeUtils.RESULT_STRING);

            }
        }
    }
}
