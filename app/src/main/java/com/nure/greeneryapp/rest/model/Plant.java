package com.nure.greeneryapp.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Plant {
    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("Species")
    private Species speciesId;

    @Expose
    @SerializedName("Parameter")
    private DeviceParameters targetParams;

    public Plant(String id, Species speciesId, DeviceParameters targetParams) {
        this.id = id;
        this.speciesId = speciesId;
        this.targetParams = targetParams;
    }

    public String getId() {
        return id;
    }

    public Species getSpecies() {
        return speciesId;
    }

    public DeviceParameters getTargetParams() {
        return targetParams;
    }
}
