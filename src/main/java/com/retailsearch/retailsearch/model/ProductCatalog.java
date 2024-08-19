package com.retailsearch.retailsearch.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductCatalog {
    private List<Product> products;
}