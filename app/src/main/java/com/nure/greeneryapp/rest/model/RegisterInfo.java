package com.nure.greeneryapp.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterInfo {
    @SerializedName("name")
    @Expose
    private final String name;

    @SerializedName("surname")
    @Expose
    private final String surname;

    @SerializedName("password")
    @Expose
    private final String password;

    @SerializedName("email")
    @Expose
    private final String email;

    @SerializedName("role")
    @Expose
    private final String roleName;

    @SerializedName("organization")
    @Expose
    private final String organizationName;

    public RegisterInfo(String name, String surname, String password, String email, String roleName, String organizationName) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.roleName = roleName;
        this.organizationName = organizationName;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getOrganizationName() {
        return organizationName;
    }
}
