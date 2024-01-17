package com.example.inventoryservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("BOOK-CATALOG-SERVICE")
public interface BookCatalogInterface {

    @GetMapping(value = "api/v1/bookCatalog/getBookListofTitle/{title}")
    public ResponseEntity<List<Integer>> getaBookListbyTitle(@PathVariable String title);
}
