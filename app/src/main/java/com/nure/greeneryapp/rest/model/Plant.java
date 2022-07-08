package com.nure.greeneryapp.rest.model;

public class Plant {
    String id;
    String speciesId;
    String targetParamsId;

    public Plant(String id, String speciesId, String targetParamsId) {
        this.id = id;
        this.speciesId = speciesId;
        this.targetParamsId = targetParamsId;
    }

    public String getId() {
        return id;
    }

    public String getSpeciesId() {
        return speciesId;
    }

    public String getTargetParamsId() {
        return targetParamsId;
    }
}
