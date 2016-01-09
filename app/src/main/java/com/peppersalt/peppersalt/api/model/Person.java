package com.peppersalt.peppersalt.api.model;

import com.google.gson.annotations.SerializedName;

public class Person {
  @SerializedName("ID")
  private int id;

  @SerializedName("FirstName")
  private String firstName;

  @SerializedName("LastName")
  private String lastName;

  @SerializedName("SlackID")
  private String username;

  @SerializedName("Points")
  private int points;

  @SerializedName("ImageURL")
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
