package com.peppersalt.peppersalt;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peppersalt.peppersalt.announce.AnnounceFragment;
import com.peppersalt.peppersalt.messages.MessagesFragment;
import com.peppersalt.peppersalt.question.QuestionFragment;
import com.peppersalt.peppersalt.ranking.RankingFragment;

public class MainFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    FragmentManager fragmentManager = getFragmentManager();
    Fragment messagesFragment = new MessagesFragment();
    Fragment announceFragment = new AnnounceFragment();
    Fragment questionFragment = new QuestionFragment();
    Fragment rankingFragment = new RankingFragment();

    fragmentManager
        .beginTransaction()
        .replace(R.id.messages_fragment, messagesFragment)
        .commit();
    fragmentManager
        .beginTransaction()
        .replace(R.id.announce_fragment, announceFragment)
        .commit();
    fragmentManager
        .beginTransaction()
        .replace(R.id.question_fragment, questionFragment)
        .commit();
    fragmentManager
        .beginTransaction()
        .replace(R.id.ranking_fragment, rankingFragment)
        .commit();
  }
}
