package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.ResponseDto;
import com.example.inventoryservice.feign.BookCatalogInterface;
import com.example.inventoryservice.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/inventory")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ResponseDto responseDto;



    @PutMapping(value = "/updateTheStockOfParticularTitle/{title}")
    public ResponseEntity updateListOfBooksForTitle(@PathVariable String title){
        try{
            if(stockService.checkStockAvailability(title).equals("no_record")){
              //create a stock if a stock is not available for that title

                    System.out.println("stock is not available so I am creating one");
              stockService.createStockWithData(title);

              responseDto.setCode("00");
              responseDto.setMessage("success");
              responseDto.setContent(null);
              return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }else{
              //update an existing stock with the title
                    System.out.println("stock is avaialble");
              stockService.updateBookStock(title);


              responseDto.setCode("00");
              responseDto.setMessage("success");
              responseDto.setContent(null);
              return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }
        }catch (Exception ex){
            responseDto.setCode("05");
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
        }
    }



}
