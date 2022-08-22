package com.sveltetech.surya.app;

public class AppConfig {
    public static final String PAYLOAD_BUNDLE = "surya";

    public static String treeview_registered = "0";
    public static String treeview_top_loginID = "";

    public static final int FIRSTSTART = 0;
    public static final int NORMALLOGIN = 1;

    public static final int SOCIALLOGIN = 2;


    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    public static final String SHARED_PREF = "os_firebase";

    public static String authToken = "";
}
