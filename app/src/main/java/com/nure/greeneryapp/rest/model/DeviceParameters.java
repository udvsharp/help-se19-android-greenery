package com.nure.greeneryapp.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceParameters {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("co2_level")
    private Integer co2Level;

    @Expose
    @SerializedName("ground_humidity")
    private Integer groundHumidity;

    @Expose
    @SerializedName("air_humidity")
    private Integer airHumidity;

    @Expose
    @SerializedName("air_temperature")
    private Integer airTemperature;

    @Expose
    @SerializedName("light_level")
    private Integer lightLevel;

    public DeviceParameters(String id, Integer co2Level, Integer groundHumidity, Integer airHumidity, Integer airTemperature, Integer lightLevel) {
        this.id = id;
        this.co2Level = co2Level;
        this.groundHumidity = groundHumidity;
        this.airHumidity = airHumidity;
        this.airTemperature = airTemperature;
        this.lightLevel = lightLevel;
    }

    public String getId() {
        return id;
    }

    public Integer getCo2Level() {
        return co2Level;
    }

    public Integer getGroundHumidity() {
        return groundHumidity;
    }

    public Integer getAirHumidity() {
        return airHumidity;
    }

    public Integer getAirTemperature() {
        return airTemperature;
    }

    public Integer getLightLevel() {
        return lightLevel;
    }
}
