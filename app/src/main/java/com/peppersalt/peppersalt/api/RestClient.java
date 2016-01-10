package com.peppersalt.peppersalt.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class RestClient {
  private static final String BASE_URL = "http://ludovicpost.me:3001";
  private RestService mRestService;
  private static RestClient instance;

  private RestClient() {
    Gson gson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .create();
    RestAdapter restAdapter = new RestAdapter
        .Builder()
        .setLogLevel(RestAdapter.LogLevel.FULL)
        .setEndpoint(BASE_URL)
        .setConverter(new GsonConverter(gson))
        .build();

    mRestService = restAdapter.create(RestService.class);
  }

  public static RestClient getInstance() {
    if (instance == null) {
      return (instance = new RestClient());
    }
    return instance;
  }

  public RestService getRestService() {
    return mRestService;
  }
}
