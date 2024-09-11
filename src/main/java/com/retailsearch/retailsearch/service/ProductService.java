package com.retailsearch.retailsearch.service;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.cloud.retail.v2.Product;
import com.google.cloud.retail.v2.PriceInfo;
import com.google.cloud.retail.v2.SearchRequest;
import com.google.cloud.retail.v2.SearchResponse;
import com.google.cloud.retail.v2.SearchServiceClient;
import com.google.cloud.retail.v2.SearchServiceClient.SearchPagedResponse;
import com.retailsearch.retailsearch.dto.ImageDto;
import com.retailsearch.retailsearch.dto.PriceInfoDto;
import com.retailsearch.retailsearch.dto.SearchResponseDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.protobuf.util.JsonFormat;
import com.retailsearch.retailsearch.dto.ProductDto;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductService {

    private final Storage storage;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductService(Storage storage, ObjectMapper objectMapper) {
        this.storage = storage;
        this.objectMapper = objectMapper;
    }

    //normal search
 /*   public List<Product> searchProducts(String bucketName, String fileName, String query) throws IOException {
        Blob blob = storage.get(bucketName, fileName);
        String jsonContent = new String(blob.getContent(), StandardCharsets.UTF_8);
        System.out.println(blob.getBucket() + " *******Chaithra ,  this is name of the bucker*****");
        ProductCatalog productCatalog = objectMapper.readValue(jsonContent, ProductCatalog.class);

        return productCatalog.getProducts().stream()
                .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }*/

    //ordered search
    public SearchResponseDto searchOrderedProducts(String query, int pageSize, String orderBy) throws IOException {
        SearchRequest searchRequest = SearchRequest.newBuilder()
                .setPlacement("projects/sinuous-anvil-432407-m4/locations/global/catalogs/default_catalog/placements/default_search")
                .setBranch("projects/hopeful-sound-1010/locations/global/catalogs/default_catalog/branches/default_branch")
                .setVisitorId(UUID.randomUUID().toString())
                .setQuery(query)
                .setPageSize(pageSize)
                .setOrderBy(orderBy)
                .build();
System.out.println(searchRequest+"searchRequest");
        try (SearchServiceClient searchClient = SearchServiceClient.create()) {
            SearchPagedResponse pagedResponse = searchClient.search(searchRequest);
            System.out.println(pagedResponse+"pagedResponses");
            return convertToDto(pagedResponse.getPage().getResponse());


        }
    }

    private SearchResponseDto convertToDto(SearchResponse searchResponse) {
        List<ProductDto> productDtos = new ArrayList<>();
        for (SearchResponse.SearchResult searchResult : searchResponse.getResultsList()) {
            Product product = searchResult.getProduct();
            System.out.println(" searchResult" + searchResult + " searchResult");
            // Convert PriceInfo
            PriceInfo priceInfo = product.hasPriceInfo() ? product.getPriceInfo() : null;
            PriceInfoDto priceInfoDto = null;
            if (priceInfo != null) {
                priceInfoDto = new PriceInfoDto(
                        priceInfo.getCurrencyCode(),
                        priceInfo.getPrice()
                );

            }

            // Convert Images
            List<ImageDto> imageDtos = new ArrayList<>();
            if (!product.getImagesList().isEmpty()) {
                for (com.google.cloud.retail.v2.Image image : product.getImagesList()) {
                    imageDtos.add(new ImageDto(image.getUri()));
                }
            }
    System.out.println(" getId " + product.getId() + " get id");
            System.out.println(" product " + product + " product");
            // Convert Product to ProductDto
            ProductDto productDto = new ProductDto(
                    product.getId(),
                    product.getPrimaryProductId(),
                    product.getCategoriesList(),
                    product.getTitle(),
                    priceInfoDto,
                    product.getAvailability().name(),
                    product.getUri(),
                    imageDtos
            );
            System.out.println(product.getId()+" productDto");
            productDtos.add(productDto);
        }

        // Return the SearchResponseDto with a list of ProductDto and total size
        return new SearchResponseDto(productDtos, searchResponse.getTotalSize());
    }

public SearchResponse FilteredProducts(String query,int pageSiz,String filter)throws IOException,InterruptedException{
    String BRANCH_NAME = "projects/sinuous-anvil-432407-m4/locations/global/catalogs/default_catalog/branches/branch0";
    // String PLACEMENT_NAME = "projects/sinuous-anvil-432407-m4/locations/global/catalogs/default_catalog/placements/default_search"; // Update if you have a specific placement for branch0
    String VISITOR_ID = "your-visitor-id";  // Replace with actual visitor ID if available
SearchRequest searchRequest=SearchRequest.newBuilder().setBranch(BRANCH_NAME).setVisitorId(VISITOR_ID)
        .setQuery(query)
        .setPageSize(pageSiz)
        .setFilter(filter).build();
try(SearchServiceClient searchServiceClient=SearchServiceClient.create()){
    SearchPagedResponse pagedResponse=searchServiceClient.search(searchRequest);
    return pagedResponse.getPage().getResponse();
}
}



    public SearchResponse OrderedProducts(String query, int pageSize, String orderBy) throws IOException {
        String BRANCH_NAME = "projects/sinuous-anvil-432407-m4/locations/global/catalogs/default_catalog/branches/branch0";
        String bucketName = "retailsearch";  // Replace with your bucket name
        String objectName = "catalog_data.json";  // Replace with your file's path

        // Initialize the Storage client
        Storage storage = StorageOptions.getDefaultInstance().getService();

        // Read the JSON file from GCS
        Blob blob = storage.get(BlobId.of(bucketName, objectName));
        String jsonContent = new String(blob.getContent());

        // Parse the JSON content to extract visitor_id, placement, etc.
        // Assuming your JSON structure is like:
        // {
        //   "visitor_id": "your-visitor-id",
        //   "placement": "projects/sinuous-anvil-432407-m4/locations/global/catalogs/default_catalog/placements/default_search"
        // }
        SearchRequest.Builder requestBuilder = SearchRequest.newBuilder();
        JsonFormat.parser().merge(jsonContent, requestBuilder);
        requestBuilder.setBranch(BRANCH_NAME)
                .setQuery(query)
                .setPageSize(pageSize)
                .setOrderBy(orderBy);

        SearchRequest searchRequest = requestBuilder.build();

        // Perform the search using SearchServiceClient
        try (SearchServiceClient searchClient = SearchServiceClient.create()) {
            SearchPagedResponse pagedResponse = searchClient.search(searchRequest);
            return pagedResponse.getPage().getResponse();
        }
    }

}