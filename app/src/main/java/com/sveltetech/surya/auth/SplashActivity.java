package com.sveltetech.surya.auth;

import android.os.Bundle;
import android.os.Handler;

import com.sveltetech.surya.R;
import com.sveltetech.surya.app.PreferencesManager;
import com.sveltetech.surya.common.Utils;
import com.sveltetech.surya.constants.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!PreferencesManager.getInstance(context).getMPin().equals(""))
                   goToActivityWithFinish(context,EnterPinActivity.class,null);
                else
                    goToActivityWithFinish(context,AuthActivity.class,null);
            }
        }, Utils.SPLASHTIMEOUT);
    }
}