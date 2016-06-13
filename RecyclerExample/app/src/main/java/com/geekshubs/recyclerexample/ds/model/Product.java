package com.geekshubs.recyclerexample.ds.model;

/**
 * Created by geekshubs on 17/04/16.
 */
public class Product {

    private int id;
    private String name;
    private int quantity;

    public Product(String name, int quantity) {
        this.quantity = quantity;
        this.name = name;
    }

    public Product(int id, String name, int quantity) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getId() {
        return id;
    }
}
