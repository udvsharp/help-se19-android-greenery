package com.nure.greeneryapp.rest.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Device {
    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("is_working")
    private boolean working;

    @Expose
    @Nullable
    @SerializedName("Plant")
    private Plant plant;

    @Expose
    @SerializedName("Parameter")
    private DeviceParameters parameter;

    public boolean isAvailable() {
        return plant == null;
    }

    public Device(String id, boolean working, Plant plant, DeviceParameters parameter) {
        this.id = id;
        this.working = working;
        this.plant = plant;
        this.parameter = parameter;
    }

    public String getId() {
        return id;
    }

    public boolean isWorking() {
        return working;
    }

    public Plant getPlant() {
        return plant;
    }

    public DeviceParameters getParameter() {
        return parameter;
    }
}
