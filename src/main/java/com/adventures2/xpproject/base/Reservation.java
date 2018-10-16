package com.adventures2.xpproject.base;

public class Reservation  {
  private int id;
  private String start;
  private String end;
  private int customDiscount;
  private int peopleAmount;
  private int fk_customer_id;
  private int fk_activity_id;
  private int fk_user_id;
  private int fk_employee_id;

  public Reservation() {
  }

  public Reservation(String start, String end, int customDiscount, int peopleAmount, int fk_customer_id, int fk_activity_id, int fk_user_id, int fk_employee_id) {
    this.start = start;
    this.end = end;
    this.customDiscount = customDiscount;
    this.peopleAmount = peopleAmount;
    this.fk_customer_id = fk_customer_id;
    this.fk_activity_id = fk_activity_id;
    this.fk_user_id = fk_user_id;
    this.fk_employee_id = fk_employee_id;
  }

  public Reservation(int id, String start, String end, int customDiscount, int peopleAmount, int fk_customer_id, int fk_activity_id, int fk_user_id, int fk_employee_id) {
    this.id = id;
    this.start = start;
    this.end = end;
    this.customDiscount = customDiscount;
    this.peopleAmount = peopleAmount;
    this.fk_customer_id = fk_customer_id;
    this.fk_activity_id = fk_activity_id;
    this.fk_user_id = fk_user_id;
    this.fk_employee_id = fk_employee_id;
  }

  @Override
  public String toString() {
    return "Reservation{" +
            "id=" + id +
            ", start='" + start + '\'' +
            ", end='" + end + '\'' +
            ", customDiscount=" + customDiscount +
            ", peopleAmount=" + peopleAmount +
            ", fk_customer_id=" + fk_customer_id +
            ", fk_activity_id=" + fk_activity_id +
            ", fk_user_id=" + fk_user_id +
			", fk_employee_id=" + fk_employee_id +
            '}';
  }

  public int getId() {
    return id;
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public String getEnd() {
    return end;
  }

  public void setEnd(String end) {
    this.end = end;
  }

  public int getCustomDiscount() {
    return customDiscount;
  }

  public void setCustomDiscount(int customDiscount) {
    this.customDiscount = customDiscount;
  }

  public int getPeopleAmount() {
    return peopleAmount;
  }

  public void setPeopleAmount(int peopleAmount) {
    this.peopleAmount = peopleAmount;
  }

  public int getFk_customer_id() {
    return fk_customer_id;
  }

  public void setFk_customer_id(int fk_customer_id) {
    this.fk_customer_id = fk_customer_id;
  }

  public int getFk_activity_id() {
    return fk_activity_id;
  }

  public void setFk_activity_id(int fk_activity_id) {
    this.fk_activity_id = fk_activity_id;
  }

  public int getFk_user_id() {
    return fk_user_id;
  }

  public void setFk_user_id(int fk_user_id) {
    this.fk_user_id = fk_user_id;
  }

	public int getFk_employee_id() {
		return fk_employee_id;
	}

	public void setFk_employee_id(int fk_employee_id) {
		this.fk_employee_id = fk_employee_id;
	}
}

