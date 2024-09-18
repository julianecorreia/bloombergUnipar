package br.unipar.frameworksweb.bloombergunipar;

import java.text.DecimalFormat;

public class Stock {

    private String name;
    private double price;

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return Math.round(price * 10000.0) / 10000.0;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
