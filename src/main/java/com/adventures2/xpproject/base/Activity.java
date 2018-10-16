package com.adventures2.xpproject.base;

public class Activity {
  private int id;
  private String name;
  private double price;
  private String time;
  private int discount;
  private String image;
  private int amountOfEquipment;


  public Activity() {
  }

  public Activity(int id, String name, double price, String time, int discount, String image, int amountOfEquipment) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.time = time;
    this.discount = discount;
    this.image = image;
    this.amountOfEquipment = amountOfEquipment;
  }

  public Activity(String name, double price, String time, int discount, String image, int amountOfEquipment) {
    this.name = name;
    this.price = price;
    this.time = time;
    this.discount = discount;
    this.image = image;
    this.amountOfEquipment = amountOfEquipment;

  }

  @Override
  public String toString() {
    return "Activity{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", price=" + price +
      ", time=" + time +
      ", discount=" + discount +
      ", image=" + image +
      '}';
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public int getDiscount() {
    return discount;
  }

  public void setDiscount(int discount) {
    this.discount = discount;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public int getAmountOfEquipment() {
    return amountOfEquipment;
  }

  public void setAmountOfEquipment(int amountOfEquipment) {
    this.amountOfEquipment = amountOfEquipment;
  }
}
