package com.nure.greeneryapp.rest.model;

public class Species {
    String id;
    String name;

    public Species(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
