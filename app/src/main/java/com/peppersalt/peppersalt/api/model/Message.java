package com.peppersalt.peppersalt.api.model;

import com.google.gson.annotations.SerializedName;

public class Message {
  @SerializedName("id")
  private int id;

  @SerializedName("author")
  private Person author;

  @SerializedName("author_id")
  private int authorId;

  @SerializedName("message")
  private String message;

  @SerializedName("time")
  private String time;

  public Person getAuthor() {
    return author;
  }

  public String getMessage() {
    return message;
  }

  public String getTime() {
    return time;
  }

  public void setAuthor(Person author) {
    this.author = author;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public int getAuthorId() {
    return authorId;
  }

  public void setAuthorId(int authorId) {
    this.authorId = authorId;
  }
}
