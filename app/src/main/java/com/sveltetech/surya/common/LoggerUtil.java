package com.sveltetech.surya.common;

import android.util.Log;

import com.google.gson.Gson;
import com.sveltetech.surya.BuildConfig;


public class LoggerUtil {
    private static final String TAG = "OUTPUT";

    public static void logItem(Object src) {
        Gson gson = new Gson();
        if (BuildConfig.DEBUG)
            Log.e(TAG, "====:> " + gson.toJson(src));
    }
}
