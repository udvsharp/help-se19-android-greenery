package com.nure.greeneryapp.rest.api;

import com.nure.greeneryapp.rest.model.DeviceParameters;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ParametersService {
    @GET("/api/parameters/{id}")
    Call<DeviceParameters> GetParameters(
            @Path("id") String id
    );

    @PUT("/api/parameters/{id}")
    Call<DeviceParameters> UpdateParameters(
        @Path("id") String id,
        @Body DeviceParameters params
    );
}
