package com.peppersalt.peppersalt.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question {
  @SerializedName("description")
  private String description;

  @SerializedName("answers")
  private List<String> answers;
}
