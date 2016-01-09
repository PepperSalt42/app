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
import com.peppersalt.peppersalt.api.model.Person;
import com.peppersalt.peppersalt.base.PepperSaltFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RankingFragment extends PepperSaltFragment {

  @Bind(R.id.rankingRecyclerView) RecyclerView recyclerView;

  private RankingAdapter adapter;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_ranking, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    adapter = new RankingAdapter();
    adapter.setData(setFakeData());
    setRecyclerView(view.getContext());
  }

  private void setRecyclerView(Context context) {
    GridLayoutManager layoutManager = new GridLayoutManager(context, 1);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);
  }

  @Override
  public void onResume() {
    super.onResume();
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
