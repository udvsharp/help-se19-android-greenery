package com.nure.greeneryapp.rest.model;

public class DeviceParameters {
    private String id;
    Integer co2Level;
    Integer groundHumidity;
    Integer airHumidity;
    Integer airTemperature;
    Integer lightLevel;

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
