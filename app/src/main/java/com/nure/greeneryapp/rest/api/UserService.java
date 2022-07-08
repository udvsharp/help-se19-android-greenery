package com.nure.greeneryapp.rest.api;

import com.nure.greeneryapp.rest.model.UserProfile;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("api/users/me") // TODO: add auth
    Call<UserProfile> GetUserProfile();
}
