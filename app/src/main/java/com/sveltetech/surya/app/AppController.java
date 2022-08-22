package com.sveltetech.surya.app;

import android.app.Application;


import com.sveltetech.surya.R;
import com.sveltetech.surya.common.ConnectivityReceiver;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class AppController extends Application {
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Mulish-SemiBold.ttf")
                .setFontAttrId(uk.co.chrisjenx.calligraphy.R.attr.fontPath)
                .disableCustomViewInflation()
                .build());
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
