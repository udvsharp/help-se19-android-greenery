package com.nure.greeneryapp.rest.api;

import com.nure.greeneryapp.rest.model.Device;
import com.nure.greeneryapp.rest.model.DeviceParameters;
import com.nure.greeneryapp.rest.model.DeviceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DevicesService {
    @GET("api/parameters/{id}")
    Call<DeviceParameters> GetDeviceParameters(
            @Path("id") String deviceId
    );

    @GET("api/devices/organization/{id}")
    Call<DeviceResponse> GetOrganizationDevices(
            @Path("id") String orgId
    );
}
