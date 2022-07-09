package com.nure.greeneryapp.ui.register;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
class RegisterFormState {
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
    @Nullable
    private Integer emailError;
    private boolean isDataValid;

    RegisterFormState(
            @Nullable Integer usernameError,
            @Nullable Integer surnameError,
            @Nullable Integer passwordError,
            @Nullable Integer emailError,
            @Nullable Integer roleError,
            @Nullable Integer organizationError
    ) {
        this.usernameError = usernameError;
        this.surnameError = surnameError;
        this.passwordError = passwordError;
        this.emailError = emailError;
        this.roleError = roleError;
        this.organizationError = organizationError;
        this.isDataValid = false;
    }

    RegisterFormState(boolean isDataValid) {
        this.usernameError = null;
        this.surnameError = null;
        this.passwordError = null;
        this.emailError = null;
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

    @Nullable
    public Integer getEmailError() {
        return emailError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}