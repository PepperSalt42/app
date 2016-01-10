package com.peppersalt.peppersalt.ranking;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peppersalt.peppersalt.R;
import com.peppersalt.peppersalt.api.RestClient;
import com.peppersalt.peppersalt.api.RestService;
import com.peppersalt.peppersalt.api.model.Person;
import com.peppersalt.peppersalt.base.PepperSaltLceFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RankingFragment extends PepperSaltLceFragment {

  @Bind(R.id.rankingRecyclerView) RecyclerView recyclerView;

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
}
