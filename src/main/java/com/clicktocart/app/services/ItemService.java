package com.clicktocart.app.services;

import com.clicktocart.app.model.Item;
import com.clicktocart.app.model.User;
import com.clicktocart.app.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
        existingItem.setBrand(item.getBrand());
        existingItem.setRam(item.getRam());
        existingItem.setStorage(item.getStorage());
        existingItem.setPrice(item.getPrice());
        existingItem.setStock(item.getStock());
        existingItem.setImgLink(item.getImgLink());
        existingItem.setDescription(item.getDescription());

        return itemRepository.save(existingItem);
    }

    public void updateStockCustomer(int quantity ,int id){
        itemRepository.updateStockCustomer(quantity,id);
    }

    public String updateStockSellar(int quantity ,int id){
        itemRepository.updateStockSellar(quantity,id);
        return "Stock updated sucessfully";
    }

    public List<Item> getAllItemsBySellarID(int id){
        return itemRepository.getAllItemsBySellarID(id);
    }


}
