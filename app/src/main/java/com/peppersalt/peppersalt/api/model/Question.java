package com.peppersalt.peppersalt.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question {
  @SerializedName("description")
  private String description;

  @SerializedName("answers")
  private List<String> answers;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> getAnswers() {
    return answers;
  }

  public void setAnswers(List<String> answers) {
    this.answers = answers;
  }
}
