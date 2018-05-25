package com.example.asus.yd1512qjddemo.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.yd1512qjddemo.R;
import com.example.asus.yd1512qjddemo.bean.UserBean;
import com.example.asus.yd1512qjddemo.component.DaggerHttpComponent;
import com.example.asus.yd1512qjddemo.model.HttpModule;
import com.example.asus.yd1512qjddemo.MainActivity;
import com.example.asus.yd1512qjddemo.ui.base.BaseActivity;
import com.example.asus.yd1512qjddemo.ui.login.contract.LoginContract;
import com.example.asus.yd1512qjddemo.ui.login.presenter.LoginPresenter;
import com.example.asus.yd1512qjddemo.utils.SharedPreferencesUtils;


public class LoginActivity extends BaseActivity<LoginPresenter>  implements LoginContract.View, View.OnClickListener {

    private EditText mEdMobile;
    private EditText mEdPassword;
    /**
     * 登录
     */
    private Button mBtButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }


    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }


    @Override
    public void loginSuccess(UserBean userBean) {
        //登录成功
        Toast.makeText(LoginActivity.this,userBean.getMsg(),Toast.LENGTH_LONG).show();
        SharedPreferencesUtils.setParam(LoginActivity.this,"uid",userBean.getData().getUid() + "");
        SharedPreferencesUtils.setParam(LoginActivity.this,"name",userBean.getData().getUsername() + "");
        SharedPreferencesUtils.setParam(LoginActivity.this,"iconUrl",userBean.getData().getIcon() + "");
        SharedPreferencesUtils.setParam(LoginActivity.this,"token",userBean.getData().getToken() + "");
        //跳转
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        LoginActivity.this.finish();
    }

    private void initView() {
        mEdMobile = (EditText) findViewById(R.id.ed_mobile);
        mEdPassword = (EditText) findViewById(R.id.ed_password);
        mBtButton = (Button) findViewById(R.id.bt_button);
        mBtButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_button:
                String mobile = mEdMobile.getText().toString();
                String password = mEdPassword.getText().toString();
                mPresenter.login(mobile,password);
                break;
        }
    }
}
