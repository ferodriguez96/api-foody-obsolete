package com.foody.api.client.model.entities;

import java.math.BigDecimal;
import java.util.UUID;

public class Item {
    private String id;
    private String restaurantId;
    private String name;
    private String description;
    private double price;
    private String imageURL;
    private boolean active; //Status

    public Item() {
    }

    public void initialize(){
        if(this.id == null){
            setId(UUID.randomUUID().toString());
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
