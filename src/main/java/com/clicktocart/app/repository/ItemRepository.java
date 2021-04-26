package com.clicktocart.app.repository;

import com.clicktocart.app.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {

    @Query("UPDATE Item SET stock = stock - ?1 WHERE id = ?2")
    @Modifying
    void updateStockCustomer(int quantity ,int id);

    @Query("UPDATE Item SET stock = stock + ?1 WHERE name = ?2 and sellarName= ?3")
    @Modifying
    void updateStockSellar(int quantity ,String name,String sellarName);
}
