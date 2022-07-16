package com.nure.greeneryapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PrefsUtils {
    public static final String AUTH_DATA_PREFS = "AuthData";

    private static final String PREFS_KEY_TOKEN = "token";
    private static final String PREFS_KEY_ORGANIZATION_ID = "orgId";

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
    public String getOrganizationId() {
        return authPrefs.getString(PREFS_KEY_ORGANIZATION_ID, "NULL");
    }

    public void deleteToken() {
        SharedPreferences.Editor editor = authPrefs.edit();
        editor.remove(PREFS_KEY_TOKEN);
        editor.apply();
    }

    public boolean isTokenSet() {
        return authPrefs.contains(PREFS_KEY_TOKEN);
    }

    public void saveOrganization(String organizationId) {
        SharedPreferences.Editor editor = authPrefs.edit();

        editor.putString(PREFS_KEY_ORGANIZATION_ID, organizationId);

        editor.apply();
    }

}
