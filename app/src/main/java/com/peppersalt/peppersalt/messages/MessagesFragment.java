package com.peppersalt.peppersalt.messages;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peppersalt.peppersalt.R;
import com.peppersalt.peppersalt.api.model.Message;
import com.peppersalt.peppersalt.api.model.Person;
import com.peppersalt.peppersalt.base.PepperSaltFragment;
import com.peppersalt.peppersalt.messages.adapter.MessagesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessagesFragment extends PepperSaltFragment {

  @Bind(R.id.messagesRecyclerView) RecyclerView messagesRecyclerView;

  private Context context;
  private MessagesAdapter adapter;
  private List<Object> data;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
  }

  @Override
  public void onDetach() {
    this.context = null;
    super.onDetach();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_messages, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    data = new ArrayList<>();
    adapter = new MessagesAdapter(view, data);
    messagesRecyclerView.setAdapter(adapter);
    messagesRecyclerView.setLayoutManager(new LinearLayoutManager(context));
    addFakeData();
  }

  private void addFakeData() {
    Person author = new Person();
    Message message = new Message();

    author.setFirstName("Lucien");
    author.setLastName("Bramart");
    author.setUsername("bramar_l");
    author.setImageUrl("https://avatars.slack-edge.com/2016-01-06/17850350192_00b38f75688300858435_48.jpg");
    author.setPoints(10);
    message.setAuthor(author);
    message.setTime("2016-15-08 18:54:42");
    message.setMessage("Bonjour, ceci est un test de la team PepperSalt Android. Ceci est un message allongé afin de prendre plus d'espace");

    data.add("Today's messages");
    data.add(message);
    data.add(message);
    data.add(message);
    adapter.setData(data);
  }
}
