package com.nure.greeneryapp.rest.api;

import com.nure.greeneryapp.rest.model.LoggedInUser;
import com.nure.greeneryapp.rest.model.LoginInfo;
import com.nure.greeneryapp.rest.model.RegisterInfo;
import com.nure.greeneryapp.rest.model.UserProfile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthService {
    @POST("api/auth/register")
    Call<LoggedInUser> Register(@Body RegisterInfo registerInfo);

    @POST("api/auth/login")
    Call<LoggedInUser> Login(@Body LoginInfo info);
}
