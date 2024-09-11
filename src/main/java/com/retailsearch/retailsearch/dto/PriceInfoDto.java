package com.retailsearch.retailsearch.dto;

public class PriceInfoDto {
    private String currencyCode;
    private double price;

    // Constructor
    public PriceInfoDto(String currencyCode, double price) {
        this.currencyCode = currencyCode;
        this.price = price;
    }

    // Getters and setters
    public String getCurrencyCode() { return currencyCode; }
    public void setCurrencyCode(String currencyCode) { this.currencyCode = currencyCode; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
