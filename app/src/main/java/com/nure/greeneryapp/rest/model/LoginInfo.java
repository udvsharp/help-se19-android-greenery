package com.nure.greeneryapp.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginInfo {
    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("surname")
    @Expose
    String surname;

    @SerializedName("password")
    @Expose
    String password;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public LoginInfo(String name, String surname, String password) {
        this.name = name;
        this.surname = surname;
        this.password = password;
    }
}
