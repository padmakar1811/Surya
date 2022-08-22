package com.sveltetech.surya.constants;



import static com.sveltetech.surya.app.AppConfig.PAYLOAD_BUNDLE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.sveltetech.surya.R;
import com.sveltetech.surya.app.PreferencesManager;
import com.sveltetech.surya.common.DialogUtil;
import com.sveltetech.surya.common.Utils;
import com.sveltetech.surya.retrofit.ApiServices;
import com.sveltetech.surya.retrofit.MvpView;
import com.sveltetech.surya.retrofit.ServiceGenerator;


import java.util.Random;


public abstract class BaseFragment extends Fragment implements MvpView {
    // Toolbar toolbar;
    protected static final int ASK_SEND_SMS_PERMISSION_REQUEST_CODE = 14;
    private static final String TAG = "BaseFragment";
    private ProgressDialog mProgressDialog;
    protected final Gson gson = new Gson();
    //protected Entity mEntity;
    protected String latitude = "0", longitude = "0", lastActiveTime;
    public Activity context;
    public ApiServices apiServices, createServiceUtilityV2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        apiServices = ServiceGenerator.createService(ApiServices.class);
        createServiceUtilityV2 = ServiceGenerator.createServiceUtilityV2(ApiServices.class);

    }

    public void openSnackBarMessage(View view, String msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        snackbar.setTextColor(getResources().getColor(R.color.white));
        View view1 = snackbar.getView();
        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view1.getLayoutParams();
        params.gravity = Gravity.CENTER;
        view1.setLayoutParams(params);
        snackbar.show();
    }

    public void showAlert(String msg, int color, int icon) {
//        Alerter.create(context)
//                .setText(msg)
//                .setTextAppearance(R.style.alertTextColor)
//                .setBackgroundColorRes(color)
//                .setIcon(icon)
//                .show();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void addLeads(String name, String number, String location, String sessiontime, boolean toadd, int pos) {

    }
    @Override
    public void getCancelReason(String cancelreason) {

    }
    @Override
    public void addLeadsDefault(String name, String number, String location, String sessiontime, int pos) {

    }

    @Override
    public void addleadsvalidation(String name, String number, String location) {

    }
    public String getColoredSpanned(String text, String color) {
        return "<font color=" + color + ">" + text + "</font>";
    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void getCityData(String cityId, String cityName, String stateId, String statename, String ismetrostatus) {

    }

    @Override
    public void onError(int resId) {

    }
    @Override
    public void notificationDeleteAction(String type, String notificationId, int pos) {

    }
    @Override
    public void onError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public void getTripPackage(String trippackage) {

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
    public void sendToDataServer() {

    }
    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {
        Utils.hideSoftKeyboard(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PreferencesManager.initializeInstance(getContext());
        onViewCreatedStuff(view, savedInstanceState);
    }

    public void onViewCreatedStuff(View view, @Nullable Bundle savedInstanceState) {
    }

    ;

    public String generatePin() {
        Random random = new Random();
        @SuppressLint("DefaultLocale") String randomPIN = String.format("%04d", random.nextInt(10000));
        return randomPIN;
    }

    public void showLoading() {
        //hideLoading();
        mProgressDialog = DialogUtil.showLoadingDialog(getActivity(), TAG);
        mProgressDialog.setCancelable(false);
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public void createInfoDialog(Context context, String title,
                                 String msg) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle(title);
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setNegativeButton(
                "OK",
                (dialog, id) -> dialog.cancel());

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void goToActivity(Class<?> classActivity, Bundle bundle) {
        Utils.hideSoftKeyboard(getActivity());
        Intent intent = new Intent(getContext(), classActivity);
        if (bundle != null)
            intent.putExtra(PAYLOAD_BUNDLE, bundle);
        getActivity().startActivity(intent);
    }

    public void goToActivityWithFinish(Class<?> classActivity, Bundle bundle) {
        Intent intent = new Intent(getContext(), classActivity);
        if (bundle != null)
            intent.putExtra(PAYLOAD_BUNDLE, bundle);
        Utils.hideSoftKeyboard(getActivity());
        getActivity().startActivity(intent);
        getActivity().finish();
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
