package com.foody.api.client.service.controllers;

import com.foody.api.client.model.entities.Item;
import com.foody.api.client.service.repository.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/items")
public class ItemsController {
    Logger LOGGER = Logger.getLogger(ItemsController.class.getName());

    @Autowired
    ItemService itemService;

    @GetMapping("/getItemDetails")
    public Item getItem(@RequestParam String name ) throws InterruptedException, ExecutionException{
        return itemService.getItemDetails(name);
    }

    @GetMapping("/getItems")
    public List<Item> getItems() throws InterruptedException, ExecutionException{
        LOGGER.log(Level.INFO, String.valueOf(LocalDateTime.now().getHour()));
        return itemService.getItems();
    }

    @GetMapping("/getItemsByRestaurantId")
    public List<Item> getItemsByRestaurantId(@RequestParam String restaurantId) throws InterruptedException, ExecutionException{
        LOGGER.log(Level.INFO, String.valueOf(LocalDateTime.now().getHour()));
        return itemService.getItemsByRestaurantId(restaurantId);
    }

    @PostMapping("/createItem")
    public String createItem(@RequestBody Item item ) throws InterruptedException, ExecutionException {
        return itemService.saveItemDetails(item);
    }

    @PutMapping("/updateItem")
    public String updateItem(@RequestBody Item item  ) throws InterruptedException, ExecutionException {
        return itemService.updateItemDetails(item);
    }

    @DeleteMapping("/deleteItem")
    public String deleteItem(@RequestParam String name){
        return itemService.deleteItem(name);
    }
}
