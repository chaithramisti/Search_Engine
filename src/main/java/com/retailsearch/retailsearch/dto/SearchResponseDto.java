package com.retailsearch.retailsearch.dto;

import java.util.List;

public class SearchResponseDto {
    private List<ProductDto> products;
    private int totalSize;

    // Constructor
    public SearchResponseDto(List<ProductDto> products, int totalSize) {
        this.products = products;
        this.totalSize = totalSize;
    }


    // Getters and setters
    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}
