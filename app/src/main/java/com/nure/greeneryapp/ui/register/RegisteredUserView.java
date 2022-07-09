package com.nure.greeneryapp.ui.register;

/**
 * Class exposing authenticated user details to the UI.
 */
class RegisteredUserView {
    private final String displayName;
    private final String token;

    RegisteredUserView(String displayName, String token) {
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