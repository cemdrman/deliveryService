package com.develivery.cem.deliveryservice.utility;

import com.develivery.cem.deliveryservice.model.Order;
import com.develivery.cem.deliveryservice.model.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by cem on 26.04.2017.
 */
public class JsonParse {

    public ArrayList<Order> jsonParser(String response){
        ArrayList<Order> orders = new ArrayList<>();
        try {
            JSONArray jsonarray = new JSONArray(response);
            for (int i = 0; i < jsonarray.length(); i++) {
                Order order = new Order();
                try {
                    JSONObject rootJsonObject = jsonarray.getJSONObject(i);
                    order.setId(rootJsonObject.getInt("id"));
                    JSONArray productsJson = rootJsonObject.getJSONArray("products");
                    Product[] productList = new Product[productsJson.length()];
                    for (int j = 0; j < productsJson.length(); j++) {
                        JSONArray productsJsonOfJson = productsJson.getJSONArray(j);
                        for (int k = 0; k < productsJsonOfJson.length(); k++) {
                            Product product = new Product();
                            product.setId(productsJsonOfJson.getInt(k));
                            productList[j] = product;
                        }
                    }
                    order.setProducts(productList);
                    System.out.println("product size: " + productList.length);
                    order.setDelivery_price((float) rootJsonObject.getDouble("delivery_price"));
                    order.setContact(rootJsonObject.getString("contact"));
                    order.setStatus(rootJsonObject.getString("status"));
                    order.setStaff_id(rootJsonObject.getInt("staff_id"));
                    order.setDelivery_date( rootJsonObject.getLong("delivery_date"));
                    order.setTaken_of_at( (rootJsonObject.get("taken_off_at") == JSONObject.NULL ? 0 : (Long) rootJsonObject.get("taken_off_at")));
                    order.setDelivered_at((rootJsonObject.get("delivered_at") == JSONObject.NULL ? 0 : (Long) rootJsonObject.get("delivered_at")));
                    order.setApproved_at( rootJsonObject.getLong("approved_at"));
                    order.setCreated_at( rootJsonObject.getLong("created_at"));
                    order.setUpdated_at( rootJsonObject.getLong("updated_at"));
                    order.setProduct_details(rootJsonObject.getJSONArray("product_details"));
                    orders.add(order);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
