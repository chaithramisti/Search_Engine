package com.retailsearch.retailsearch.controller;

import com.retailsearch.retailsearch.dto.SearchResponseDto;
import com.retailsearch.retailsearch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.cloud.retail.v2.SearchResponse;

import java.io.IOException;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

  /*  @GetMapping("/search")
    public List<com.retailsearch.retailsearch.model.Product> search(@RequestParam String query) throws IOException {
        String bucketName = "retailsearch";
        String fileName = "catalog.json";

        return productService.searchProducts(bucketName, fileName, query);
    }*/
  @GetMapping("/searchOrdered")
  public ResponseEntity<SearchResponseDto> searchProducts(@RequestParam String query,
                                                          @RequestParam int pageSize,
                                                          @RequestParam String orderBy) {
      try {
          // Call the service layer to get the SearchResponseDto
          SearchResponseDto SearchResponseDto = productService.searchOrderedProducts(query, pageSize, orderBy);

          // Return a successful response with the SearchResponseDto
          return ResponseEntity.ok(SearchResponseDto);

      } catch (IOException e) {
          e.printStackTrace();

          // Return an error response if there is an exception
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body(null); // You can customize the error response message here if needed
      }
  }





        @GetMapping("/Ordered")
        public SearchResponse OrderedProducts (@RequestParam String query,
        @RequestParam(defaultValue = "10") int pageSize,
        @RequestParam(defaultValue = "desc") String orderBy) throws IOException {
            return productService.OrderedProducts(query, pageSize, orderBy);
        }
        @GetMapping("/filtered")
        public SearchResponse filteredProduct (@RequestParam String query,
        @RequestParam(defaultValue = "10") int pageSize,
        @RequestParam String filter )throws IOException, InterruptedException {
            return productService.FilteredProducts(query, pageSize, filter);
        }
    }