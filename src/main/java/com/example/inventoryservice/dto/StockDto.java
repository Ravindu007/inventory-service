package com.example.inventoryservice.dto;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockDto {
    private Integer stockId;

    private String bookTitle;

    private Integer bookCount;

    @ElementCollection
    private List<Integer> books;
}
