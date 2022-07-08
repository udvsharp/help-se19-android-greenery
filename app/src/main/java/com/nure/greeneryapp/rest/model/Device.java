package com.nure.greeneryapp.rest.model;

public class Device {
    String id;
    String orgId;
    String paramsId;
    String plantId;
    boolean isWorking;

    public Device(String id, String orgId, String paramsId, String plantId, boolean isWorking) {
        this.id = id;
        this.orgId = orgId;
        this.paramsId = paramsId;
        this.plantId = plantId;
        this.isWorking = isWorking;
    }

    public String getId() {
        return id;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getParamsId() {
        return paramsId;
    }

    public String getPlantId() {
        return plantId;
    }

    public boolean isWorking() {
        return isWorking;
    }
}
