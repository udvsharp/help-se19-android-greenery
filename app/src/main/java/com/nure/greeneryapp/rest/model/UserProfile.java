package com.nure.greeneryapp.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfile {
    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("surname")
    private String surname;

    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("role")
    private String role;

    @Expose
    @SerializedName("role_id")
    private String roleId;

    @Expose
    @SerializedName("organization_id")
    private String organizationId;

    @Expose
    @SerializedName("organization")
    private String organization;

    public UserProfile(String id, String name, String surname, String email, String role, String roleId, String organizationId, String organization) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.roleId = roleId;
        this.organizationId = organizationId;
        this.organization = organization;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public String getOrganization() {
        return organization;
    }
}
