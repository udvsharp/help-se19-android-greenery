package com.nure.greeneryapp.rest.model;

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
    @SerializedName("Plant")
    private Plant plant;

    @Expose
    @SerializedName("Parameter")
    private DeviceParameters parameter;

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
