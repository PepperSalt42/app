package com.peppersalt.peppersalt.api;

import com.peppersalt.peppersalt.api.model.Announce;
import com.peppersalt.peppersalt.api.model.Message;
import com.peppersalt.peppersalt.api.model.Person;
import com.peppersalt.peppersalt.api.model.QuestionWrapper;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface RestService {
  @GET("/users/top")
  public void getUsersTop(Callback<List<Person>> callback);

  @GET("/users/{user_id}")
  public void getUser(@Path("user_id") int userId, Callback<Person> callback);

  @GET("/messages")
  public void getMessages(@Query("count") int count, Callback<List<Message>> callback);

  @GET("/questions/current")
  public void getQuestion(Callback<QuestionWrapper> callback);

  @GET("/images/latest")
  public void getLastImage(Callback<Announce> callback);
}
