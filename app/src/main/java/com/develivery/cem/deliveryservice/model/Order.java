package com.develivery.cem.deliveryservice.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.Timestamp;

/**
 * Created by cem on 15.04.2017.
 */
public class Order {

    private int id;
    private Product[] products;
    private float delivery_price;
    private String contact;
    private String status;
    private int staff_id;
    private Timestamp delivery_date;
    private Timestamp approved_at;
    private Timestamp taken_of_at;
    private Timestamp delivered_at;
    private Timestamp created_at;
    private Timestamp updated_at;
    private JSONObject[] product_details;

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

    public Timestamp getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Timestamp delivery_date) {
        this.delivery_date = delivery_date;
    }

    public Timestamp getApproved_at() {
        return approved_at;
    }

    public void setApproved_at(Timestamp approved_at) {
        this.approved_at = approved_at;
    }

    public Timestamp getTaken_of_at() {
        return taken_of_at;
    }

    public void setTaken_of_at(Timestamp taken_of_at) {
        this.taken_of_at = taken_of_at;
    }

    public Timestamp getDelivered_at() {
        return delivered_at;
    }

    public void setDelivered_at(Timestamp delivered_at) {
        this.delivered_at = delivered_at;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public JSONObject[] getProduct_details() {
        return product_details;
    }

    public void setProduct_details(JSONObject[] product_details) {
        this.product_details = product_details;
    }
}
