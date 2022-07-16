package com.nure.greeneryapp.ui.login;

class LoggedInUserView {
    private final String displayName;
    private final String token;
    private final String organizationId;

    LoggedInUserView(String displayName, String token, String orgId) {
        this.displayName = displayName;
        this.token = token;
        this.organizationId = orgId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getToken() {
        return token;
    }
}