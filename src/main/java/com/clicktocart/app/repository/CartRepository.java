package com.clicktocart.app.repository;

import com.clicktocart.app.model.CartRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface CartRepository extends JpaRepository<CartRecords,Integer> {

    @Query(value = "SELECT * from cart_item c WHERE c.user_id = ?1 AND c.status = '"+"P"+"'",nativeQuery = true)
    public List<CartRecords> getCartDetailsByCustId(int custId);

    @Query(value = "SELECT * from cart_item c WHERE c.user_id = ?1 AND c.item_id = ?2",nativeQuery = true)
    public List<CartRecords> getCartDetailsByCustIdAndId(int custId, int id);

    @Query(value = "SELECT * from cart_item c WHERE c.user_id = ?1 AND c.status = '"+"S"+"'",nativeQuery = true)
    List<CartRecords> getPayedCartDetailsByCustId(int custId);

    @Query("UPDATE CartRecords SET status = '"+ "S" +"' WHERE user_id = ?1")
    @Modifying
    void updateCartPaymentSucess(int custId,Date curentTime);

}
