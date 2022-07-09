package com.nure.greeneryapp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsUtils {
    public static final String AUTH_DATA_PREFS = "AuthData";
    private static final String PREFS_KEY_TOKEN = "token";
    private static PrefsUtils sInstance;
    private Context context;
    private SharedPreferences authPrefs;

    private PrefsUtils(Context context) {
        this.context = context.getApplicationContext();
    }

    public static void init(Context context) {
        sInstance = new PrefsUtils(context);
        sInstance.authPrefs = context.getSharedPreferences(AUTH_DATA_PREFS, Context.MODE_PRIVATE);
    }

    public static PrefsUtils getInstance() {
        return sInstance;
    }

    public void saveToken(String token) {
        SharedPreferences.Editor editor = authPrefs.edit();

        editor.putString(PREFS_KEY_TOKEN, token);

        editor.apply();
    }

    public String getToken() {
        return authPrefs.getString(PREFS_KEY_TOKEN, "NULL");
    }

    public boolean isTokenSet() {
        return authPrefs.contains(PREFS_KEY_TOKEN);
    }
}
