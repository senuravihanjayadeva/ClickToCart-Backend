package com.clicktocart.app.controller;

import com.clicktocart.app.model.CartRecords;
import com.clicktocart.app.payload.response.CartResponse;
import com.clicktocart.app.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public HashMap<String, String> addToCart(@RequestBody CartRecords cart) throws Exception {
        HashMap<String, String> response = cartService.addToCart(cart);
        return response;
    }

    @GetMapping("/{custId}")
    @PreAuthorize("hasRole('USER')")
    public List<CartResponse> getAllItemsInCart(@PathVariable int custId) {
        return cartService.getAllItemsInCart(custId);
    }

    @GetMapping("/{custId}/{id}")
    @PreAuthorize("hasRole('USER')")
    public List<CartRecords> getItemInCartById(@PathVariable int custId, @PathVariable int itemId) {
        return cartService.getItemInCartById(custId, itemId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public String deleteItemInCartById(@PathVariable int id){
        return cartService.deleteItemByID(id);
    }

    @PutMapping
    @PreAuthorize("hasRole('USER')")
    public CartRecords updateItemInCart(@RequestBody CartRecords cart){
        return cartService.updateItemById(cart);
    }

    @GetMapping("/{custId}/payedCarts")
    @PreAuthorize("hasRole('USER')")
    public List<CartResponse> getAllPayedItemsInCart(@PathVariable int custId) {
        return cartService.getAllPayedItemsInCart(custId);
    }
}
