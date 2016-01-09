package com.peppersalt.peppersalt.api.model;

import com.google.gson.annotations.SerializedName;

public class Person {
  @SerializedName("id")
  private String id;

  @SerializedName("first_name")
  private String firstName;

  @SerializedName("last_name")
  private String lastName;

  @SerializedName("username")
  private String username;

  @SerializedName("points")
  private int points;

  @SerializedName("image")
  private String imageUrl;


  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getUsername() {
    return username;
  }

  public int getPoints() {
    return points;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
