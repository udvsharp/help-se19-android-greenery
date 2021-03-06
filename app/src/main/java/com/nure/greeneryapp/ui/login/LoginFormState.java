package com.nure.greeneryapp.ui.login;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
class LoginFormState {
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;
    @Nullable
    private Integer surnameError;
    private boolean isDataValid;

    LoginFormState(
            @Nullable Integer usernameError,
            @Nullable Integer surnameError,
            @Nullable Integer passwordError
    ) {
        this.usernameError = usernameError;
        this.surnameError = surnameError;
        this.passwordError = passwordError;
        this.isDataValid = false;
    }

    LoginFormState(boolean isDataValid) {
        this.usernameError = null;
        this.surnameError = null;
        this.passwordError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    @Nullable
    Integer getSurnameError() {
        return surnameError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}