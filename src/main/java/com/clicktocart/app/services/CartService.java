package com.clicktocart.app.services;

import com.clicktocart.app.model.CartRecords;
import com.clicktocart.app.model.Item;
import com.clicktocart.app.payload.response.CartResponse;
import com.clicktocart.app.repository.CartRepository;
import com.clicktocart.app.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    public HashMap<String, String> addToCart(CartRecords cart) {

        HashMap<String, String> hm = new HashMap<>();

        double totPrice = (cart.getPrice() * cart.getQuantity());
        cart.setStatus("P");
        cart.setPrice(totPrice);
        CartRecords add = cartRepository.save(cart);

        if(add != null){
           hm.put("message", "Success");
        }
        return hm;
    }

    public List<CartResponse> getAllItemsInCart(int id) {
        List<CartRecords> cart = cartRepository.getCartDetailsByCustId(id);
        List<CartResponse> cartResponse = new ArrayList<CartResponse>();

        for(CartRecords cartRes : cart){
            CartResponse response = new CartResponse();
            response.setQty(cartRes.getQuantity());
            response.setTotalPrice(cartRes.getPrice());
            response.setId(cartRes.getId());
            response.setItemId(cartRes.getItemId());

            Item existingItem = itemRepository.findById(cartRes.getItemId()).orElse(null);
            response.setItemName(existingItem.getName());

            cartResponse.add(response);
        }
        return cartResponse;
    }

    public List<CartRecords> getItemInCartById(int custId, int id) {
        return cartRepository.getCartDetailsByCustIdAndId(custId, id);
    }

    public String deleteItemByID(int id) {
        cartRepository.deleteById(id);
        return "Item deleted";
    }

    public CartRecords updateItemById(CartRecords cart) {
        CartRecords existingItem = cartRepository.findById(cart.getId()).orElse(null);
        existingItem.setQuantity(cart.getQuantity());

        return cartRepository.save(existingItem);
    }

    public List<CartResponse> getAllPayedItemsInCart(int custId) {
        List<CartRecords> cart = cartRepository.getPayedCartDetailsByCustId(custId);
        List<CartResponse> cartResponse = new ArrayList<CartResponse>();

        for(CartRecords cartRes : cart){
            CartResponse response = new CartResponse();
            response.setQty(cartRes.getQuantity());
            response.setTotalPrice(cartRes.getPrice());
            response.setId(cartRes.getId());

            Item existingItem = itemRepository.findById(cartRes.getItemId()).orElse(null);
            response.setItemName(existingItem.getName());

            cartResponse.add(response);
        }
        return cartResponse;
    }
}
