package com.stackroute.showservice.domain;

public class Category {
    private String type;
    private int price;

    public Category() {
    }

    public Category(String type, int price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Category{" +
                "type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
