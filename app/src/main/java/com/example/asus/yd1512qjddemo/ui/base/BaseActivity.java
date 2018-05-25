package com.example.asus.yd1512qjddemo.ui.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.asus.yd1512qjddemo.inter.IBase;
import com.example.asus.yd1512qjddemo.utils.SharedPreferencesUtils;

import javax.inject.Inject;
public abstract class BaseActivity<T extends BaseContract.BasePresenter> extends AppCompatActivity implements IBase,BaseContract.BaseView {

    @Inject
    protected T mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        inject();
        //绑定
        if (mPresenter!=null){
            mPresenter.attchView(this);
        }

    }

    @Override
    public void initView(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        if (mPresenter!=null){
            mPresenter.detachView();
        }
    }

    public void showLoading(){

    }
    public void dismissLoading(){

    }
    protected String getUid() {
        return (String) SharedPreferencesUtils.getParam(this, "uid", "");
    }

    protected String getToken() {
        return (String) SharedPreferencesUtils.getParam(this, "token", "");
    }

    protected void toast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
}
