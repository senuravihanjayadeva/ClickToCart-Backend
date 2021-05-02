package com.clicktocart.app.repository;

import com.clicktocart.app.model.CartRecords;
import com.clicktocart.app.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {

    @Query("UPDATE Item SET stock = stock - ?1 WHERE id = ?2")
    @Modifying
    void updateStockCustomer(int quantity ,int id);

    @Query("UPDATE Item SET stock = stock + ?1 WHERE id = ?2")
    @Modifying
    void updateStockSellar(int quantity ,int id);

    @Query(value = "SELECT * from items_tbl it WHERE  it.sellarid = ?1 ",nativeQuery = true)
    public List<Item> getAllItemsBySellarID(int sellarid);

    @Query(value = "SELECT SUM(stock) from items_tbl it WHERE it.sellarid = ?1 AND it.brand = ?2",nativeQuery = true)
    public Integer getNoOfItembySellerAndBrand(int sellarid,String brand);

}
