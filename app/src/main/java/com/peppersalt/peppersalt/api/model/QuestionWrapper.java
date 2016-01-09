package com.peppersalt.peppersalt.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionWrapper {
  @SerializedName("Question")
  private Question question;

  @SerializedName("Answers")
  private List<String> answers;

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public List<String> getAnswers() {
    return answers;
  }

  public void setAnswers(List<String> answers) {
    this.answers = answers;
  }
}
