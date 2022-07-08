package com.nure.greeneryapp.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private final String displayName;
    private final String token;

    LoggedInUserView(String displayName, String token) {
        this.displayName = displayName;
        this.token = token;
    }

    String getDisplayName() {
        return displayName;
    }

    public String getToken() {
        return token;
    }
}