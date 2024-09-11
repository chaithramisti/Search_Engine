package com.retailsearch.retailsearch.dto;

public class ImageDto {
    private String uri;


    // Constructor
    public ImageDto(String uri) {
        this.uri = uri;
    }

    // Getters and setters
    public String getUri() { return uri; }
    public void setUri(String uri) { this.uri = uri; }
}