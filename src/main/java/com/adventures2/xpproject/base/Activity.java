package com.adventures2.xpproject.base;

public class Activity {
  private int id;
  private String name;
  private double price;
  private int discount;

  public Activity() {}

  public Activity(int id, String name, double price, int discount) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.discount = discount;
  }

  public Activity(String name, double price, int discount) {
    this.name = name;
    this.price = price;
    this.discount = discount;
  }

  @Override
  public String toString() {
    return "Activity{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", discount=" + discount +
            '}';
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getDiscount() {
    return discount;
  }

  public void setDiscount(int discount) {
    this.discount = discount;
  }
}
