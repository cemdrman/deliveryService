package com.develivery.cem.deliveryservice.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.security.Timestamp;

/**
 * Created by cem on 15.04.2017.
 */

public class Order implements Serializable {

    private int id;
    private Product[] products;
    private float delivery_price;
    private String contact;
    private String status;
    private int staff_id;
    private long delivery_date;
    private long approved_at;
    private long taken_of_at;
    private long delivered_at;
    private long created_at;
    private long updated_at;
    private JSONArray product_details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public float getDelivery_price() {
        return delivery_price;
    }

    public void setDelivery_price(float delivery_price) {
        this.delivery_price = delivery_price;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public long getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(long delivery_date) {
        this.delivery_date = delivery_date;
    }

    public long getApproved_at() {
        return approved_at;
    }

    public void setApproved_at(long approved_at) {
        this.approved_at = approved_at;
    }

    public long getTaken_of_at() {
        return taken_of_at;
    }

    public void setTaken_of_at(long taken_of_at) {
        this.taken_of_at = taken_of_at;
    }

    public long getDelivered_at() {
        return delivered_at;
    }

    public void setDelivered_at(long delivered_at) {
        this.delivered_at = delivered_at;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public JSONArray getProduct_details() {
        return product_details;
    }

    public void setProduct_details(JSONArray product_details) {
        this.product_details = product_details;
    }

}
