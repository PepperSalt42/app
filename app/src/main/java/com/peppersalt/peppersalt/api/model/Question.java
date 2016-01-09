package com.peppersalt.peppersalt.api.model;

import com.google.gson.annotations.SerializedName;

public class Question {
  @SerializedName("Sentence")
  private String description;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
