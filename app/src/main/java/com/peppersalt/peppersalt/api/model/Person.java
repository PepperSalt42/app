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
  private String points;

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

  public String getPoints() {
    return points;
  }

  public String getImageUrl() {
    return imageUrl;
  }
}
