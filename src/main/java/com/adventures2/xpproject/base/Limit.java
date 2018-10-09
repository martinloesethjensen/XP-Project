package com.adventures2.xpproject.base;

public class Limit {
  private int id;
  private String content;
  private int fk_activity_id;

  public Limit() {
  }

  public Limit(String content, int fk_activity_id) {
    this.content = content;
    this.fk_activity_id = fk_activity_id;
  }

  public Limit(int id, String content, int fk_activity_id) {
    this.id = id;
    this.content = content;
    this.fk_activity_id = fk_activity_id;
  }

  public int getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getFk_activity_id() {
    return fk_activity_id;
  }

  public void setFk_activity_id(int fk_activity_id) {
    this.fk_activity_id = fk_activity_id;
  }
}
