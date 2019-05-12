package com.example.evaluation_cave_a_vin;

public class Bottle {
    private String id;
    private String appellation;
    private String exploitation;
    private String type;
    private String millesime;
    private String stock_in;
    private String stock_out;
    private String date;
    private String price;

    public Bottle(String id, String appellation, String exploitation, String type, String millesime, String stock_in, String stock_out, String date, String price) {
        this.id = id;
        this.appellation = appellation;
        this.exploitation = exploitation;
        this.type = type;
        this.millesime = millesime;
        this.date = date;
        this.stock_in = stock_in;
        this.stock_out = stock_out;
        this.price = price;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public String getExploitation() {
        return exploitation;
    }

    public void setExploitation(String exploitation) {
        this.exploitation = exploitation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMillesime() {
        return millesime;
    }

    public void setMillesime(String millesime) {
        this.millesime = millesime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStock_in() {
        return stock_in;
    }

    public void setStock_in(String stock_in) {
        this.stock_in = stock_in;
    }

    public String getStock_out() {
        return stock_out;
    }

    public void setStock_out(String stock_out) {
        this.stock_out = stock_out;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
