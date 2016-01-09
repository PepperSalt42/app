package com.peppersalt.peppersalt.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ranking {

  @SerializedName("date")
  private String date;

  @SerializedName("people")
  private List<Person> people;
}
