package com.clicktocart.app.controller;

import com.clicktocart.app.model.Item;
import com.clicktocart.app.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping(value = "/api/item")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    @PreAuthorize("hasRole('MODERATOR')")
    public Item addItem (@RequestBody Item item){
        return itemService.addItem(item);
    }

    @GetMapping
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItemBuId(@PathVariable int id){
        return itemService.getItemById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteItemById(@PathVariable int id){
        return itemService.deleteItemByID(id);
    }

    @PutMapping
    public Item updateItem(@RequestBody Item item){
        return itemService.updateItemById(item);
    }
}
