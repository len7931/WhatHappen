package com.example.wxl.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.wxl.myapplication.R;
import com.example.wxl.myapplication.module.SinaSDKInfo;
import com.example.wxl.myapplication.network.SinaRequestHelper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SinaLoginActivity extends AppCompatActivity {
    AuthInfo mAuthInfo;
    SsoHandler mSsoHandler;
    Oauth2AccessToken mAccessToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sina_login);
        ButterKnife.bind(this);
        mAuthInfo = new AuthInfo(this, SinaSDKInfo.APP_KEY, SinaSDKInfo.REDIRECT_URL
                ,SinaSDKInfo.SCOPE);
        mSsoHandler = new SsoHandler(SinaLoginActivity.this, mAuthInfo);
        new Thread(){
            @Override
            public void run() {
                super.run();
                SinaRequestHelper.getData();
            }
        }.start();


    }
    @OnClick(R.id.login)
    public void Login() {
        Log.d("wxl", "login");
        mSsoHandler.authorizeWeb(new AuthListener());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("wxl", "come back");
        // SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }

    }

    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            if (mAccessToken.isSessionValid()) {
                Log.d("wxl", mAccessToken.getToken());
                Log.d("wxl", "OK");
            } else {
                Log.d("wxl", "failed");
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            e.printStackTrace();
            Log.d("wxl", "on failed");

        }

        @Override
        public void onCancel() {
            Log.d("wxl", "onCancle");


        }
    }

}
