package com.nure.greeneryapp.rest.model;

public class UserProfile {
    private String id;
    private String name;
    private String surname;
    private String email;
    private Integer role;
    private String organizationId;

    public UserProfile(String id, String name, String surname, String email, Integer role, String organizationId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.organizationId = organizationId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Integer getRole() {
        return role;
    }

    public String getOrganizationId() {
        return organizationId;
    }
}
