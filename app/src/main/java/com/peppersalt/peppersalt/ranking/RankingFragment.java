package com.peppersalt.peppersalt.ranking;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.peppersalt.peppersalt.R;
import com.peppersalt.peppersalt.api.RestClient;
import com.peppersalt.peppersalt.api.RestService;
import com.peppersalt.peppersalt.api.model.Person;
import com.peppersalt.peppersalt.base.PepperSaltFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RankingFragment extends PepperSaltFragment {

  @Bind(R.id.rankingRecyclerView) RecyclerView recyclerView;

  @Bind(R.id.content_view) LinearLayout contentView;
  @Bind(R.id.progress_bar) ProgressBar progressBar;
  @Bind(R.id.empty_view) TextView emptyView;
  @Bind(R.id.error_view) TextView errorView;

  private static int REFRESH_DELAY = 60000;

  private RankingAdapter adapter;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_ranking, container, false);
  }

  @Override
  public void onViewCreated(final View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    adapter = new RankingAdapter();
    setRecyclerView(view.getContext());
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        loadData();
        view.postDelayed(this, REFRESH_DELAY);
      }
    };
    view.post(runnable);
  }

  private void loadData() {
    RestService service = RestClient.getInstance().getRestService();

    service.getUsersTop(new Callback<List<Person>>() {
      @Override
      public void success(List<Person> persons, Response response) {
        if (persons == null || persons.size() == 0) {
          showEmpty();
          return ;
        }
        adapter.setData(persons);
        showContent();
      }

      @Override
      public void failure(RetrofitError error) {
        showError();
      }
    });
  }

  private void setRecyclerView(Context context) {
    GridLayoutManager layoutManager = new GridLayoutManager(context, 1);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);
  }

  private void showContent() {
    contentView.setVisibility(View.VISIBLE);
    progressBar.setVisibility(View.GONE);
    errorView.setVisibility(View.GONE);
    emptyView.setVisibility(View.GONE);
  }

  private void showEmpty() {
    contentView.setVisibility(View.GONE);
    progressBar.setVisibility(View.GONE);
    errorView.setVisibility(View.GONE);
    emptyView.setVisibility(View.VISIBLE);
  }

  private void showError() {
    contentView.setVisibility(View.GONE);
    progressBar.setVisibility(View.GONE);
    emptyView.setVisibility(View.GONE);
    errorView.setVisibility(View.VISIBLE);
  }

  private List<Person> setFakeData() {
    List<Person> people = new ArrayList<>();
    Person person = new Person();

    person.setFirstName("Noel");
    person.setLastName("Flantier");
    person.setPoints(10);
    person.setUsername("flanti_n");
    person.setImageUrl("https://avatars.slack-edge.com/2016-01-06/17850350192_00b38f75688300858435_192.jpg");
    for (int i = 0; i < 6; ++i) {
      people.add(person);
    }
    return people;
  }
}
