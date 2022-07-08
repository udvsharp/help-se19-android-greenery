package com.nure.greeneryapp.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoggedInUser {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("surname")
    @Expose
    private String surname;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("role_id")
    @Expose
    private Integer roleId;

    @SerializedName("organization_id")
    @Expose
    private String organizationId;

    @SerializedName("access_token")
    @Expose
    private String token;

    public LoggedInUser(String id, String name, String surname, String email, Integer roleId, String organizationId, String token) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roleId = roleId;
        this.organizationId = organizationId;
        this.token = token;
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

    public Integer getRoleId() {
        return roleId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public String getToken() {
        return token;
    }
}