package com.retailsearch.retailsearch.dto;

import com.google.cloud.retail.v2.PriceInfo;

import java.util.List;

public class ProductDto {
    private String id;
    private String primaryProductId;
    private List<String> categories;
    private String title;
    private PriceInfoDto priceInfo;
    private String availability;
    private String uri;
    private List<ImageDto> images;

    // Constructor
    public ProductDto(String id, String primaryProductId, List<String> categories, String title,
                      PriceInfoDto priceInfo, String availability, String uri, List<ImageDto> images) {
        this.id = id;
        this.primaryProductId = primaryProductId;
        this.categories = categories;
        this.title = title;
        this.priceInfo = priceInfo;
        this.availability = availability;
        this.uri = uri;
        this.images = images;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPrimaryProductId() { return primaryProductId; }
    public void setPrimaryProductId(String primaryProductId) { this.primaryProductId = primaryProductId; }

    public List<String> getCategories() { return categories; }
    public void setCategories(List<String> categories) { this.categories = categories; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public PriceInfoDto getPriceInfo() { return priceInfo; }
    public void setPriceInfo(PriceInfoDto priceInfo) { this.priceInfo = priceInfo; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

    public String getUri() { return uri; }
    public void setUri(String uri) { this.uri = uri; }

    public List<ImageDto> getImages() { return images; }
    public void setImages(List<ImageDto> images) { this.images = images; }
}
