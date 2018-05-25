package com.example.asus.yd1512qjddemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.asus.yd1512qjddemo.R;
import com.example.asus.yd1512qjddemo.ui.base.BaseActivity;
import com.example.asus.yd1512qjddemo.ui.classify.ClassifyFragment;
import com.example.asus.yd1512qjddemo.ui.homepage.HomePageFragment;
import com.example.asus.yd1512qjddemo.ui.mine.MyFragment;

public class MainActivity extends BaseActivity {

    private HomePageFragment homePageFragment;
    private ClassifyFragment classifyFragment;
    private FragmentManager fragmentManager;
    private RadioButton rbHomepage;
    private RadioButton rbClass;
    private RadioButton rbFind;
    private RadioButton rbShopCar;
    private RadioButton rbMine;
    private RadioGroup rg;
    private FrameLayout flContent;
    private int currentIndex=1;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initView 必须在 rbHomepage.setChecked(true);之前 否则出错钓不到应用
        initView();
        fragmentManager = getSupportFragmentManager();
        homePageFragment = new HomePageFragment();
        classifyFragment = new ClassifyFragment();
        myFragment = new MyFragment();
        fragmentManager.beginTransaction().replace(R.id.flContent, homePageFragment).commit();

        rbHomepage.setChecked(true);
        //设置点击事件
        setLisenter();

    }

    private void setLisenter() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    //首页
                    case R.id.rbHomepage:
                        if (currentIndex==1){
                            return;
                        }
                        currentIndex=1;
                        fragmentManager.beginTransaction().replace(R.id.flContent,homePageFragment).commit();
                        break;
                    //分类
                     case R.id.rbClass:
                        if (currentIndex==2){
                            return;
                        }
                        currentIndex=2;
                        fragmentManager.beginTransaction().replace(R.id.flContent,classifyFragment).commit();
                    break;
                    //发现
                    case R.id.rbFind:

                    break;
                    //购物车
                    case R.id.rbShopCar:

                    break;
                    //我的
                    case R.id.rbMine:
                        fragmentManager.beginTransaction().replace(R.id.flContent,myFragment).commit();
                    break;
                }
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void inject() {

    }

    private void initView() {
        rbHomepage = (RadioButton) findViewById(R.id.rbHomepage);
        rbClass = (RadioButton) findViewById(R.id.rbClass);
        rbFind = (RadioButton) findViewById(R.id.rbFind);
        rbShopCar = (RadioButton) findViewById(R.id.rbShopCar);
        rbMine = (RadioButton) findViewById(R.id.rbMine);
        rg = (RadioGroup) findViewById(R.id.rg);
        flContent = (FrameLayout) findViewById(R.id.flContent);
    }
}
