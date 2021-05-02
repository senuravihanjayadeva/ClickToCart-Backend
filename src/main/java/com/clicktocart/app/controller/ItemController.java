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
//    @PreAuthorize("hasRole('MODERATOR')")
    public String deleteItemById(@PathVariable int id){
        return itemService.deleteItemByID(id);
    }

    @PutMapping
//    @PreAuthorize("hasRole('MODERATOR')")
    public Item updateItem(@RequestBody Item item){
        return itemService.updateItemById(item);
    }

    @PutMapping("customer/{qty}/{id}")
    public void updateStockCustomer(@PathVariable int qty,@PathVariable int id){
        itemService.updateStockCustomer(qty,id);
    }

    @PutMapping("sellar/{qty}/{name}/{sellarName}")
    public String updateStockSellar(@PathVariable int qty,@PathVariable String name,@PathVariable String sellarName){
        return itemService.updateStockSellar(qty,name,sellarName);
    }
}
