package com.sveltetech.surya.app;

import android.content.Context;
import android.content.SharedPreferences;


public class PreferencesManager {

    //app login variables
    private static final String PREF_NAME = "com.sveltetech.surya.PREF";
    private static final String Full_Name = "Full_Name";
    private static final String UniqueId = "UniqueId";
    private static final String UserType = "UserType";
    private static final String ProfilePic = "ProfilePic";
    private static final String Contact = "Contact";
    private static final String AltContact = "AltContact";
    private static final String Token = "Token";
    private static final String LoginId = "LoginId";
    private static final String Gender = "Gender";
    private static final String Email = "Email";
    private static final String MPin = "MPin";

    public static PreferencesManager sInstance;
    private final SharedPreferences mPref;

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_FIRST_INTRO = "IS_FIRST_INTRO";

    private PreferencesManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    //for fragment
    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
    }

    //for getting instance
    public static synchronized PreferencesManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
        return sInstance;
    }

    public boolean clear() {
        return mPref.edit().clear().commit();
    }




    //Email
    public void setEmail(String value) {
        mPref.edit().putString(Email, value).apply();
    }
    public String getEmail() {
        return mPref.getString(Email, "");
    }

    //MPin
    public void setMPin(String value) {
        mPref.edit().putString(MPin, value).apply();
    }
    public String getMPin() {
        return mPref.getString(MPin, "");
    }



    //Full_Name
    public void setFull_Name(String value) {
        mPref.edit().putString(Full_Name, value).apply();
    }

    public String getFull_Name() {
        return mPref.getString(Full_Name, "");
    }

    //UniqueId
    public void setUniqueId(String value) {
        mPref.edit().putString(UniqueId, value).apply();
    }

    public String getUniqueId() {
        return mPref.getString(UniqueId, "");
    }

    //UserType
    public void setUserType(String value) {
        mPref.edit().putString(UserType, value).apply();
    }

    public String getUserType() {
        return mPref.getString(UserType, "");
    }

    //ProfilePic
    public void setProfilePic(String value) {
        mPref.edit().putString(ProfilePic, value).apply();
    }

    public String getProfilePic() {
        return mPref.getString(ProfilePic, "");
    }

    //Contact
    public void setContact(String value) {
        mPref.edit().putString(Contact, value).apply();
    }

    public String getContact() {
        return mPref.getString(Contact, "");
    }

    //Token
    public void setToken(String value) {
        mPref.edit().putString(Token, value).apply();
    }

    public String getToken() {
        return mPref.getString(Token, "");
    }

    //LoginId
    public void setLoginId(String value) {
        mPref.edit().putString(LoginId, value).apply();
    }

    public String getLoginId() {
        return mPref.getString(LoginId, "");
    }


    //IS_FIRST_TIME_LAUNCH
    public void setIsFirstTimeLaunch(boolean value) {
        mPref.edit().putBoolean(IS_FIRST_TIME_LAUNCH, value).apply();
    }

    public boolean getIsFirstTimeLaunch() {
        return mPref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    //setIsFirstIntro
    public void setIsFirstIntro(boolean value) {
        mPref.edit().putBoolean(IS_FIRST_INTRO, value).apply();
    }

    public boolean getIsFirstIntro() {
        return mPref.getBoolean(IS_FIRST_INTRO, true);
    }




    //AltContact
    public void setAltContact(String value) {
        mPref.edit().putString(AltContact, value).apply();
    }

    public String getAltContact() {
        return mPref.getString(AltContact, "");
    }

    //Gender
    public void setGender(String value) {
        mPref.edit().putString(Gender, value).apply();
    }

    public String getGender() {
        return mPref.getString(Gender, "");
    }


}
