package com.example.inventoryservice.feign;

import com.example.inventoryservice.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("BOOK-CATALOG-SERVICE")
public interface BookCatalogInterface {

    @GetMapping(value = "api/v1/bookCatalog/getBookListofTitle/{title}")
    public ResponseEntity<List<Integer>> getaBookListbyTitle(@PathVariable String title);


    @PostMapping(value = "api/v1/bookCatalog/create")
    public ResponseEntity createSingleBook(@RequestBody BookDto bookDto);
}

