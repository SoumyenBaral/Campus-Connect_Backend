package com.campus.connect.Dto;

public class UserUpdateRequest {
    private String name;
    private String contact;
    private String organisationName;
    private String status;

    public UserUpdateRequest() {
    }

    public UserUpdateRequest(String name, String contact, String organisationName, String status) {
        this.name = name;
        this.contact = contact;
        this.organisationName = organisationName;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
