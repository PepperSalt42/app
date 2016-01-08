package com.peppersalt.peppersalt.api.model;

import com.google.gson.annotations.SerializedName;

public class Message {
  @SerializedName("id")
  private int id;

  @SerializedName("author")
  private Person author;

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
}
