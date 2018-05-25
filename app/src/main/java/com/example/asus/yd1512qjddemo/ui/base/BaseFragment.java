package com.example.asus.yd1512qjddemo.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.yd1512qjddemo.R;
import com.example.asus.yd1512qjddemo.inter.IBase;
import com.example.asus.yd1512qjddemo.utils.SharedPreferencesUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<T extends BaseContract.BasePresenter> extends Fragment implements IBase,BaseContract.BaseView{

    @Inject
    protected T mPresenter;
    private Unbinder bind;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注入
        inject();
        //绑定
        if (mPresenter!=null){
            mPresenter.attchView(this);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentLayout(), null);
        bind = ButterKnife.bind(getActivity(), view);
        initView(view);
        return view;

    }

    //解绑
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachView();
        }
        if (bind!=null){
            bind.unbind();
        }

    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    protected String getUid() {
        return (String) SharedPreferencesUtils.getParam(getContext(), "uid", "");
    }

    protected String getToken() {
        return (String) SharedPreferencesUtils.getParam(getContext(), "token", "");
    }
}
