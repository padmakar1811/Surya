package com.sveltetech.surya.constants;



import static com.sveltetech.surya.app.AppConfig.PAYLOAD_BUNDLE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;
import com.sveltetech.surya.R;
import com.sveltetech.surya.app.PreferencesManager;
import com.sveltetech.surya.common.DialogUtil;
import com.sveltetech.surya.common.LoggerUtil;
import com.sveltetech.surya.common.NetworkConnectionChecker;
import com.sveltetech.surya.common.NetworkUtils;
import com.sveltetech.surya.common.Utils;
import com.sveltetech.surya.retrofit.ApiServices;
import com.sveltetech.surya.retrofit.MvpView;
import com.sveltetech.surya.retrofit.ServiceGenerator;

import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements
        NetworkConnectionChecker.OnConnectivityChangedListener, View.OnClickListener, MvpView {
    private ProgressDialog mProgressDialog;
    private static final String TAG = "BaseActivity";
    protected static final int PHONE_STATE_PERMISSION_REQUEST_CODE = 12;
    public Activity context;
    public ApiServices apiServices, createServiceUtilityV2;
    public PreferencesManager preferencesManager;

    Dialog calldialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        apiServices = ServiceGenerator.createService(ApiServices.class);
        createServiceUtilityV2 = ServiceGenerator.createServiceUtilityV2(ApiServices.class);
        PreferencesManager.initializeInstance(context);
    }


    public void openSnackBarMessage(View view, String msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        snackbar.setTextColor(getResources().getColor(R.color.white));
        snackbar.show();
    }

    public String getColoredSpanned(String text, String color) {
        return "<font color=" + color + ">" + text + "</font>";
    }

    public void languageselection(String langtype) {
        Locale locale = new Locale(langtype);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    public JsonObject encryptBody(JsonObject param) {
        JsonObject body = new JsonObject();
        try {

            LoggerUtil.logItem(body);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }
    public void rateapplication() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
    }


    private void getlogout(Class<?> activity) {

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void setLangRecreate(String langval, boolean bool) {
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale(langval);
        Locale.setDefault(locale);
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        if (bool)
            recreate();
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void showLoading() {
        mProgressDialog = DialogUtil.showLoadingDialog(BaseActivity.this, "Base Activity");
        mProgressDialog.setCancelable(false);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @Override
    public void notificationDeleteAction(String type, String notificationId, int pos) {

    }

    @Override
    public void getCityData(String cityId, String cityName, String stateId, String statename, String ismetrostatus) {

    }

    @Override
    public void getTripPackage(String trippackage) {

    }

    @Override
    public void addLeads(String name, String number, String location, String sessiontime, boolean toadd, int pos) {

    }

    @Override
    public void addLeadsDefault(String name, String number, String location, String sessiontime, int pos) {

    }

    @Override
    public void addleadsvalidation(String name, String number, String location) {

    }

    @Override
    public void addMeetingLocation(String location, boolean toadd, int pos) {

    }

    @Override
    public void addMeetingLocationDefault(String location, int pos) {

    }

    @Override
    public void addMeetingLocationvalidation(String location) {

    }

    @Override
    public void getCancelReason(String cancelreason) {

    }

    @Override
    public void sendToDataServer() {

    }


    @Override
    public void connectivityChanged(boolean availableNow) {

    }

    public void createInfoDialog(Context context, String title,
                                 String msg, boolean isBack) {
        androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder1.setTitle(title);
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setNegativeButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (isBack)
//                            goToActivityWithFinish((Activity) context, Login.class, null);
                            dialog.cancel();
                    }
                });

        androidx.appcompat.app.AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public String generatePin() {
        Random random = new Random();
        @SuppressLint("DefaultLocale") String randomPIN = String.format("%04d", random.nextInt(10000));

        return randomPIN;
    }




    public void hideSoftKeyboard(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        InputMethodManager inputMethodManager = (InputMethodManager) activity
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        View v = activity.getCurrentFocus();
        if (v != null) {
            IBinder binder = activity.getCurrentFocus().getWindowToken();
            if (binder != null) {
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(binder, 0);
                }
            }
        }
    }

    public void goToActivity(Activity activity, Class<?> classActivity, Bundle bundle) {
        Utils.hideSoftKeyboard(activity);
        Intent intent = new Intent(activity, classActivity);
        activity.overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
        if (bundle != null)
            intent.putExtra(PAYLOAD_BUNDLE, bundle);
        activity.startActivity(intent);
    }

    public void goToActivityWithFinish(Activity activity, Class<?> classActivity, Bundle bundle) {
        Intent intent = new Intent(context, classActivity);
//        activity.overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
        if (bundle != null)
            intent.putExtra(PAYLOAD_BUNDLE, bundle);
        Utils.hideSoftKeyboard(activity);
        activity.startActivity(intent);
        activity.finish();
    }

    public void goToActivityWithFinishFlipAnimation(Activity activity, Class<?> classActivity, Bundle bundle) {
        Intent intent = new Intent(context, classActivity);
        activity.overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
        if (bundle != null)
            intent.putExtra(PAYLOAD_BUNDLE, bundle);
        Utils.hideSoftKeyboard(activity);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void finishActivity(Activity activity) {
        Utils.hideSoftKeyboard(activity);
        activity.finish();
    }








    public void shareDataText(String shareBody, String shareSubject) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//        sharingIntent.setPackage("com.facebook.katana");
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(sharingIntent);
    }

    public void SharingToSocialMedia(String application, String shareBody) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shareBody);

        boolean installed = checkAppInstall(application);
        if (installed) {
            intent.setPackage(application);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Install application first", Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkAppInstall(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }

    public void logoutDialog(Activity context, Class<?> activity) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle("Logout");
        builder1.setMessage("Do you really want to logout?");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                (dialog, id) -> {
                    if (NetworkUtils.getConnectivityStatus(context) != 0) {
                       // getLogout(activity);
                    } else
                        showMessage(R.string.alert_internet);
                    dialog.cancel();
                });

        builder1.setNegativeButton("No", (dialog, id) -> dialog.cancel());

        AlertDialog alert11 = builder1.create();
        alert11.setOnShowListener(arg0 -> {
            alert11.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.red));
            alert11.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorAccent));
        });
        alert11.show();
    }

    public void getLogout(Class<?> activity) {

    }

}