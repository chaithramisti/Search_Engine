package com.retailsearch.retailsearch.controller;

import com.retailsearch.retailsearch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    public List<com.retailsearch.retailsearch.model.Product> search(@RequestParam String query) throws IOException {
        String bucketName = "retailsearch";
        String fileName = "catalog_data.json";

        return productService.searchProducts(bucketName, fileName, query);
    }
}