package com.foody.api.client.model.entities;

import com.foody.api.client.model.entities.Menu;

import java.util.Date;
import java.util.UUID;

public class Restaurant {
    private String id;
    private String name;
    private String address;
    private String telephone;
    private Boolean open;
    //Google maps Link or coordinates coming soon
    //private Menu menu;


    public Restaurant() {
    }

    /*public Restaurant(String name, String address, String telephone) {
        this.id=UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.open = false;
    }*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void initialize(){
        if(this.id == null){
            setId(UUID.randomUUID().toString());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    /*public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }*/
}
