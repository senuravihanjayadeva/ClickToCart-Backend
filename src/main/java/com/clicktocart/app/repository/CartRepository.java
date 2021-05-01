package com.clicktocart.app.repository;

import com.clicktocart.app.model.CartRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartRecords,Integer> {

    @Query(value = "SELECT * from cart_item c WHERE c.user_id = ?1 AND c.status = '"+"P"+"'",nativeQuery = true)
    public List<CartRecords> getCartDetailsByCustId(int custId);

    @Query(value = "SELECT * from cart_item c WHERE c.user_id = ?1 AND c.item_id = ?2",nativeQuery = true)
    public List<CartRecords> getCartDetailsByCustIdAndId(int custId, int id);

}
