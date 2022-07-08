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
    @Nullable
    private Integer roleError;
    @Nullable
    private Integer organizationError;
    private boolean isDataValid;

    LoginFormState(
            @Nullable Integer usernameError,
            @Nullable Integer surnameError,
            @Nullable Integer passwordError,
            @Nullable Integer roleError,
            @Nullable Integer organizationError
    ) {
        this.usernameError = usernameError;
        this.surnameError = surnameError;
        this.passwordError = passwordError;
        this.roleError = roleError;
        this.organizationError = organizationError;
        this.isDataValid = false;
    }

    LoginFormState(boolean isDataValid) {
        this.usernameError = null;
        this.surnameError = null;
        this.passwordError = null;
        this.roleError = null;
        this.organizationError = null;
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

    @Nullable
    public Integer getRoleError() {
        return roleError;
    }

    @Nullable
    public Integer getOrganizationError() {
        return organizationError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}