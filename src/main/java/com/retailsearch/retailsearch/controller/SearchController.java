/*package com.retailsearch.retailsearch.controller;

import com.google.cloud.retail.v2.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @GetMapping("/simple")
    public List<Product> simpleSearch(@RequestParam String query) throws Exception {
        String projectId = "sinuous-anvil-432407-m4";
        String searchQuery = query;

        SearchServiceClient searchServiceClient = SearchServiceClient.create();
        BranchName branchName = BranchName.of(projectId, "default_catalog", "default_branch");

        SearchRequest searchRequest = SearchRequest.newBuilder()
                .setPlacement(PlacementName.of(projectId, "default_catalog", "default_search").toString())
                .setQuery(searchQuery)
                .setBranch(branchName.toString())
                .build();

        SearchResponse searchResponse = searchServiceClient.search(searchRequest).iterateAll();

        List<Product> products = new ArrayList<>();
        for (SearchResponse.SearchResult searchResult : searchResponse.getResultsList()) {
            products.add(searchResult.getProduct());
        }

        return products;
    }
    @GetMapping("/boosted")
    public List<Product> searchWithBoost(@RequestParam String query, @RequestParam String boostAttribute) throws Exception {
        String projectId = "YOUR_PROJECT_ID";

        SearchServiceClient searchServiceClient = SearchServiceClient.create();
        BranchName branchName = BranchName.of(projectId, "default_catalog", "default_branch");

        SearchRequest.BoostSpec boostSpec = SearchRequest.BoostSpec.newBuilder()
                .addConditionBoostSpecs(SearchRequest.BoostSpec.ConditionBoostSpec.newBuilder()
                        .setCondition(String.format("attributes.%s", boostAttribute))
                        .setBoost(2.0f)
                        .build())
                .build();

        SearchRequest searchRequest = SearchRequest.newBuilder()
                .setPlacement(PlacementName.of(projectId, "default_catalog", "default_search").toString())
                .setQuery(query)
                .setBranch(branchName.toString())
                .setBoostSpec(boostSpec)
                .build();

        Iterable<SearchResponse.SearchResult> searchResponse = searchServiceClient.search(searchRequest).iterateAll();

        List<Product> products = new ArrayList<>();
        for (SearchResponse.SearchResult searchResult : searchResponse.getClass()) {
            products.add(searchResult.getProduct());
        }

        return products;
    }

}*/
