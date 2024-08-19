package com.retailsearch.retailsearch.service;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.retailsearch.retailsearch.model.Product;
import com.retailsearch.retailsearch.model.ProductCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final Storage storage;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductService(Storage storage, ObjectMapper objectMapper) {
        this.storage = storage;
        this.objectMapper = objectMapper;
    }

    public List<Product> searchProducts(String bucketName, String fileName, String query) throws IOException {
        Blob blob = storage.get(bucketName, fileName);
        String jsonContent = new String(blob.getContent(), StandardCharsets.UTF_8);
        ProductCatalog productCatalog = objectMapper.readValue(jsonContent, ProductCatalog.class);

        return productCatalog.getProducts().stream()
                .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}