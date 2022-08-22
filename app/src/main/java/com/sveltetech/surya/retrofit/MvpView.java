package com.sveltetech.surya.retrofit;


import androidx.annotation.StringRes;


public interface MvpView {
    void showLoading();

    void hideLoading();

    void openActivityOnTokenExpire();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();

    void notificationDeleteAction(String type,String notificationId,int pos);

    void getCityData(String cityId,String cityName,String stateId,String statename,String ismetrostatus);

    void getTripPackage(String trippackage);

    void addLeads(String name, String number,String location,String sessiontime, boolean toadd, int pos);

    void addLeadsDefault(String name, String number,String location,String sessiontime,int pos);

    void addleadsvalidation(String name, String number,String location);

    void addMeetingLocation(String location, boolean toadd, int pos);

    void addMeetingLocationDefault(String location,int pos);

    void addMeetingLocationvalidation(String location);

    void getCancelReason(String cancelreason);

    void sendToDataServer();

}
