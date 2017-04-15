package com.develivery.cem.deliveryservice.model;

import java.security.Timestamp;

/**
 * Created by cem on 15.04.2017.
 */
public class Staff {

    private int id;
    private String email;
    private String password;
    private String name;
    private String mobile;
    private int total_deliveries;
    private Timestamp updated_at;
    private Timestamp created_at;
    private Timestamp deleted_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getTotal_deliveries() {
        return total_deliveries;
    }

    public void setTotal_deliveries(int total_deliveries) {
        this.total_deliveries = total_deliveries;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }
}
