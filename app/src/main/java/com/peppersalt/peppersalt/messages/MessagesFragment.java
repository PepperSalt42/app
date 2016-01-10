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
import com.peppersalt.peppersalt.api.RestClient;
import com.peppersalt.peppersalt.api.RestService;
import com.peppersalt.peppersalt.api.model.Message;
import com.peppersalt.peppersalt.api.model.Person;
import com.peppersalt.peppersalt.base.PepperSaltLceFragment;
import com.peppersalt.peppersalt.messages.adapter.MessagesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MessagesFragment extends PepperSaltLceFragment {

  @Bind(R.id.messagesRecyclerView) RecyclerView messagesRecyclerView;

  private static int REFRESH_TIME = 5000;

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
  public void onViewCreated(final View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    data = new ArrayList<>();
    adapter = new MessagesAdapter(view, data);
    messagesRecyclerView.setAdapter(adapter);
    messagesRecyclerView.setLayoutManager(new LinearLayoutManager(context));
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        loadData();
        view.postDelayed(this, REFRESH_TIME);
      }
    };
    view.post(runnable);
  }

  private void loadData() {
    final RestService service = RestClient.getInstance().getRestService();
    int count = 8;

    service.getMessages(new Callback<List<Message>>() {
      @Override
      public void success(final List<Message> messages, Response response) {
        final List<Person> people = new ArrayList<>();

        if (messages.size() == 0) {
          showEmpty();
          return ;
        }
        for (final Message message : messages) {
          int messageAuthorId = message.getAuthorId();
          boolean isAlreadyGetted = false;

          for (Person person : people) {
            if (person.getId() == messageAuthorId) {
              isAlreadyGetted = true;
              break ;
            }
          }
          if (!isAlreadyGetted) {
            service.getUser(message.getAuthorId(), new Callback<Person>() {
              @Override
              public void success(Person person, Response response) {
                people.add(person);
                setAuthorToMessage(messages, people);
                List<Object> data = new ArrayList<>();
                data.addAll(messages);
                adapter.setData(data);
                showContent();
              }

              @Override
              public void failure(RetrofitError error) {
                showError();
              }
            });
          }
        }
      }

      @Override
      public void failure(RetrofitError error) {
        adapter.setData(new ArrayList<Object>());
        showError();
      }
    });
  }

  private void setAuthorToMessage(List<Message> messages, List<Person> people) {
    for (Message message : messages) {
      for (Person person : people) {
        if (message.getAuthorId() == person.getId()) {
          message.setAuthor(person);
          break ;
        }
      }
    }
  }
}
