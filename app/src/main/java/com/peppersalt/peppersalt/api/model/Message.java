package com.peppersalt.peppersalt.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Message {
  @SerializedName("ID")
  private int id;

  private Person author;

  @SerializedName("UserID")
  private int authorId;

  @SerializedName("Message")
  private String message;

  @SerializedName("SentAt")
  private Date time;

  public Person getAuthor() {
    return author;
  }

  public String getMessage() {
    return message;
  }

  public Date getTime() {
    return time;
  }

  public void setAuthor(Person author) {
    this.author = author;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public int getAuthorId() {
    return authorId;
  }

  public void setAuthorId(int authorId) {
    this.authorId = authorId;
  }
}
