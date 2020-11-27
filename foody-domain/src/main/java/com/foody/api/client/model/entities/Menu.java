package com.foody.api.client.model.entities;

import java.util.List;
import java.util.UUID;


//No creo que sea necesaria esta clase o en tal caso debe ser reformulada.
public class Menu {
    private String id;
    private String restaurantId;
    private List<Item> items;

    public Menu() {
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
