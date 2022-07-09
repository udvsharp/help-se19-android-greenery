package com.nure.greeneryapp.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeviceResponse {
    @Expose
    @SerializedName("taken")
    List<Device> taken;

    @Expose
    @SerializedName("available")
    List<Device> available;

    public DeviceResponse(List<Device> taken, List<Device> available) {
        this.taken = taken;
        this.available = available;
    }

    public List<Device> getTaken() {
        return taken;
    }

    public List<Device> getAvailable() {
        return available;
    }
}
