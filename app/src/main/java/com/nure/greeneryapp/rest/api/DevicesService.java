package com.nure.greeneryapp.rest.api;

import com.nure.greeneryapp.rest.model.Device;
import com.nure.greeneryapp.rest.model.DeviceParameters;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DevicesService {
    @GET("api/parameters/{id}")
    Call<DeviceParameters> GetDeviceParameters(
            @Path("id") Integer deviceId
    );

    @GET("api/devices/organization/{id}")
    Call<List<Device>> GetOrganizationDevices(
            @Path("id") Integer orgId
    );
}
