package com.peppersalt.peppersalt.api.model;

import com.google.gson.annotations.SerializedName;

public class Message {
  @SerializedName("id")
  private int id;

  @SerializedName("message")
  private String message;
}
