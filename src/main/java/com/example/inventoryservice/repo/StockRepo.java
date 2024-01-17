package com.example.inventoryservice.repo;

import com.example.inventoryservice.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepo extends JpaRepository<Stock, Integer> {
    //find the stock by title; => return stock id
    @Query(value = "SELECT s.stock_id FROM Stock s WHERE s.stock_title = :title LIMIT 1", nativeQuery = true)
    Integer findByTitle(String title);
}
