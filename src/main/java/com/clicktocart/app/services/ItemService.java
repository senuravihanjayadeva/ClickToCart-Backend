package com.clicktocart.app.services;

import com.clicktocart.app.model.Item;
import com.clicktocart.app.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item addItem(Item item){
        return itemRepository.save(item);
    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }
    public Item getItemById(int id){
        return itemRepository.findById(id).orElse(null);
    }

    public String deleteItemByID(int id){
        itemRepository.deleteById(id);
        return "Record deleted";
    }
    public Item updateItemById(Item item){
        Item existingItem = itemRepository.findById(item.getId()).orElse(null);
        existingItem.setName(item.getName());
        existingItem.setCatagory(item.getCatagory());
        existingItem.setPrice(item.getPrice());
        existingItem.setStock(item.getStock());
        return itemRepository.save(existingItem);
    }

}
