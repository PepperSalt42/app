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
}
