package com.clicktocart.app.services;

import com.clicktocart.app.model.Item;
import com.clicktocart.app.model.User;
import com.clicktocart.app.payload.response.ItemBrandDetail;
import com.clicktocart.app.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.HashSet;
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

    //Reduce stock when customer paid
    public void updateStockCustomer(int quantity ,int id){
        itemRepository.updateStockCustomer(quantity,id);
    }

    //increase stock when seller added stock
    public String updateStockSellar(int quantity ,int id){
        itemRepository.updateStockSellar(quantity,id);
        return "Stock updated sucessfully";
    }

    public List<Item> getAllItemsBySellarID(int id){
        return itemRepository.getAllItemsBySellarID(id);
    }

    public List<ItemBrandDetail> getAllItemsBrandsBySellarID(int sellarId){

        List<Item> items = itemRepository.getAllItemsBySellarID(sellarId);
        HashSet<String> setBrands=new HashSet();
        List<ItemBrandDetail> itemBrandDetailList = new ArrayList<>();

        for(Item item: items){
            setBrands.add(item.getBrand());
        }

        for(String itemBrand: setBrands){
                ItemBrandDetail itemBrandDetail = new ItemBrandDetail();
                int qty = itemRepository.getNoOfItembySellerAndBrand(sellarId,itemBrand);
                itemBrandDetail.setBrand(itemBrand);
                itemBrandDetail.setQuantity(qty);
                itemBrandDetailList.add(itemBrandDetail);
        }

        return  itemBrandDetailList;

    }


}
