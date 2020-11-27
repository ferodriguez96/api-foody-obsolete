package com.foody.api.client.service.controllers;

import com.foody.api.client.model.entities.Menu;
import com.foody.api.client.service.repository.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;



//No creo que sea necesaria esta clase o en tal caso debe ser reformulada.
@RestController
@RequestMapping("/menus")
public class MenusController {
    Logger LOGGER = Logger.getLogger(MenusController.class.getName());

    @Autowired
    MenuService menuService;

    @GetMapping("/getMenuDetails")
    public Menu getMenu(@RequestParam String name ) throws InterruptedException, ExecutionException{
        return menuService.getMenuDetails(name);
    }

    @GetMapping("/getMenus")
    public List<Menu> getMenus() throws InterruptedException, ExecutionException{
        LOGGER.log(Level.INFO, String.valueOf(LocalDateTime.now().getHour()));
        return menuService.getMenus();
    }

    @PostMapping("/createMenu")
    public String createMenu(@RequestBody Menu menu ) throws InterruptedException, ExecutionException {
        return menuService.saveMenuDetails(menu);
    }

    @PutMapping("/updateMenu")
    public String updateMenu(@RequestBody Menu menu  ) throws InterruptedException, ExecutionException {
        return menuService.updateMenuDetails(menu);
    }

    @DeleteMapping("/deleteMenu")
    public String deleteMenu(@RequestParam String name){
        return menuService.deleteMenu(name);
    }
}
