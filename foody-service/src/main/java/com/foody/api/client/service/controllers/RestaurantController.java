package com.foody.api.client.service.controllers;

import com.foody.api.client.model.entities.Restaurant;
import com.foody.api.client.service.repository.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    Logger LOGGER = Logger.getLogger(RestaurantController.class.getName());

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/getRestaurantDetails")
    public Restaurant getRestaurant(@RequestParam String name ) throws InterruptedException, ExecutionException{
        return restaurantService.getRestaurantDetails(name);
    }

    @GetMapping("/getRestaurants")
    public List<Restaurant> getRestaurants() throws InterruptedException, ExecutionException{
        LOGGER.log(Level.INFO, String.valueOf(LocalDateTime.now().getHour()));
        return restaurantService.getRestaurants();
    }

    @PostMapping("/createRestaurant")
    public String createRestaurant(@RequestBody Restaurant restaurant ) throws InterruptedException, ExecutionException {
        return restaurantService.saveRestaurantDetails(restaurant);
    }

    @PutMapping("/updateRestaurant")
    public String updateRestaurant(@RequestBody Restaurant restaurant  ) throws InterruptedException, ExecutionException {
        return restaurantService.updateRestaurantDetails(restaurant);
    }

    @DeleteMapping("/deleteRestaurant")
    public String deleteRestaurant(@RequestParam String name){
        return restaurantService.deleteRestaurant(name);
    }
}
