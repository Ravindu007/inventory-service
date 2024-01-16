package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.ResponseDto;
import com.example.inventoryservice.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/inventory")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ResponseDto responseDto;


    //book catalog-service must return a list of ids of a particular title
//    @GetMapping(value = "/getBookListofTitle/{title}")
//    public ResponseEntity getListOfBooksForTitle(@PathVariable String title){
//        try{
////            //        List<Integer> bookIds = //calling the method of the BookCatalogService
////
////            responseDto.setCode("00");
////            responseDto.setMessage("success");
////            responseDto.setContent(bookIds);
////            return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
//        }catch (Exception ex){
//            responseDto.setCode("05");
//            responseDto.setMessage(ex.getMessage());
//            responseDto.setContent(null);
//            return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
//        }
//    }

}
