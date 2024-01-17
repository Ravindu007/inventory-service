package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.BookDto;
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



    //update the inventory upon adding a new book
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
              System.out.println("stock is already available");
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


    //register all the new books then it will automatically update the stock as well
    @PostMapping(value="/createStockOfParticularTitle/{bookCount}")
    public ResponseEntity createBookStockForGivenTitleAndCount(@RequestBody BookDto bookDto, @PathVariable Integer bookCount){
        //check if there is a book store already available for given title
        if(stockService.checkStockAvailability(bookDto.getTitle()).equals("record_found")){
           //if a stock is available update the existing count with the count pass to here
           stockService.updateExistingBookStock(bookDto,bookCount);
        }
//        else{
//          //if it is not available create a stock for this title with the count
//
//        }
        responseDto.setCode("00");
        responseDto.setMessage("success");
        responseDto.setContent(null);
        return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
    }



}
