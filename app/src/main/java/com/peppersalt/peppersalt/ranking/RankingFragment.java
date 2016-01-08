package com.peppersalt.peppersalt.ranking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peppersalt.peppersalt.R;
import com.peppersalt.peppersalt.api.model.Person;
import com.peppersalt.peppersalt.base.PepperSaltFragment;
import com.peppersalt.peppersalt.customviews.podiumView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RankingFragment extends PepperSaltFragment {

  @Bind(R.id.podiumView) podiumView podiumV;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_ranking, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    setFakeData();
  }

  private void setFakeData() {
    List<Person> people = new ArrayList<>();
    Person person = new Person();

    person.setFirstName("Noel");
    person.setLastName("Flantier");
    person.setPoints(10);
    person.setUsername("flanti_n");
    people.add(person);
    people.add(person);
    people.add(person);
    podiumV.setData(people);
  }
}
