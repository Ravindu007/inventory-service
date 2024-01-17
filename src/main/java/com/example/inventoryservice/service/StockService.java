package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.ResponseDto;
import com.example.inventoryservice.dto.StockDto;
import com.example.inventoryservice.entity.Stock;
import com.example.inventoryservice.feign.BookCatalogInterface;
import com.example.inventoryservice.repo.StockRepo;
import com.example.inventoryservice.util.VarList;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private ResponseDto responseDto;

    @Autowired
    private StockRepo stockRepo;

    @Autowired
    BookCatalogInterface bookCatalogInterface;



    //check whether we have a stock with that title
    public String checkStockAvailability(String title){
        Integer stock_id = stockRepo.findByTitle(title);
        if(stock_id == null){
            System.out.println("Stock is not found");
            return "no_record";
        }else{
            System.out.println("stock is already there");
            return "record_found";
        }
    }


    //create a stock if a stock is not available with the given name
    public String createStockWithData(String title){


        Stock stock = new Stock();
        stock.setStockTitle(title);
        stockRepo.save(stock);

        List<Integer> bookIds =  bookCatalogInterface.getaBookListbyTitle(title).getBody();
        System.out.println(bookIds);

        stock.setBooks(bookIds);
        stockRepo.save(stock);
        return VarList.RSP_SUCCESS;

    }


    //otherwise update the book-stock
    public String updateBookStock(String title){
        Integer stock_id = stockRepo.findByTitle(title);
        if(stock_id == null){
            return VarList.RSP_NO_DATA_FOUND;
        }else{
            Stock stock = stockRepo.findById(stock_id).get();

            List<Integer> bookIds =  bookCatalogInterface.getaBookListbyTitle(title).getBody();
            System.out.println(bookIds);
            stock.setBooks(bookIds);

            stock.setBooks(bookIds);
            stockRepo.save(stock);
            return VarList.RSP_SUCCESS;

        }
    }

}
