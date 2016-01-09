package com.peppersalt.peppersalt.api.model;

import com.google.gson.annotations.SerializedName;

public class Announce {
  @SerializedName("URL")
  private String imageUrl;

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
