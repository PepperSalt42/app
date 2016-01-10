package com.peppersalt.peppersalt.question;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peppersalt.peppersalt.R;
import com.peppersalt.peppersalt.api.RestClient;
import com.peppersalt.peppersalt.api.RestService;
import com.peppersalt.peppersalt.api.model.Question;
import com.peppersalt.peppersalt.api.model.QuestionWrapper;
import com.peppersalt.peppersalt.base.PepperSaltLceFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.grantland.widget.AutofitTextView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class QuestionFragment extends PepperSaltLceFragment {

  @Bind(R.id.question) AutofitTextView questionView;
  @Bind(R.id.answer1) TextView answer1View;
  @Bind(R.id.answer2) TextView answer2View;
  @Bind(R.id.answer3) TextView answer3View;
  @Bind(R.id.answer4) TextView answer4View;

  private static int REFRESH_DELAY = 60000;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_question, container, false);
  }

  @Override
  public void onViewCreated(final View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        loadQuestion();
        view.postDelayed(this, REFRESH_DELAY);
      }
    };
    view.post(runnable);
  }

  private void loadQuestion() {
    RestService service = RestClient.getInstance().getRestService();

    service.getQuestion(new Callback<QuestionWrapper>() {
      @Override
      public void success(QuestionWrapper wrapper, Response response) {
        Question question = wrapper.getQuestion();
        List<String> answers = wrapper.getAnswers();

        if (question == null || question.getDescription() == null ||
            answers == null || answers.size() < 2) {
          showEmpty();
          return;
        }
        setView(question, answers);
        showContent();
      }

      @Override
      public void failure(RetrofitError error) {
        if (error.getResponse() != null && error.getResponse().getStatus() == 404) {
          showEmpty();
        }
        else {
          showError();
        }
      }
    });
  }

  private void setView(Question question, List<String> answers) {
    questionView.setText(question.getDescription());
    answer1View.setText(String.format("1. %s", answers.get(0)));
    answer2View.setText(String.format("2. %s", answers.get(1)));
    switch (answers.size()) {
      default:
        answer3View.setText("");
        answer4View.setText("");
      case 3:
        answer3View.setText(String.format("3. %s", answers.get(2)));
        answer4View.setText("");
        break;
      case 4:
        answer3View.setText(String.format("3. %s", answers.get(2)));
        answer4View.setText(String.format("4. %s", answers.get(3)));
        break;
    }
  }
}
