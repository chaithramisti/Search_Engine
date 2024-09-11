package com.retailsearch.retailsearch.model;




import java.util.List;

public class Product {
    private String id;
    private String primaryProductId;
    private List<String> categories;
    private String title;
    private PriceInfo priceInfo;
    private String availability;
    private String uri;
    private List<Image> images;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPrimaryProductId() { return primaryProductId; }
    public void setPrimaryProductId(String primaryProductId) { this.primaryProductId = primaryProductId; }

    public List<String> getCategories() { return categories; }
    public void setCategories(List<String> categories) { this.categories = categories; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }


    public PriceInfo getPriceInfo() { return priceInfo; }
    public void setPriceInfo(PriceInfo priceInfo) { this.priceInfo = priceInfo; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

    public String getUri() { return uri; }
    public void setUri(String uri) { this.uri = uri; }

    public List<Image> getImages() { return images; }
    public void setImages(List<Image> images) { this.images = images; }
}