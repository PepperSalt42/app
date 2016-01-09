package com.peppersalt.peppersalt.messages;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
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
import com.peppersalt.peppersalt.api.model.Message;
import com.peppersalt.peppersalt.api.model.Person;
import com.peppersalt.peppersalt.base.PepperSaltFragment;
import com.peppersalt.peppersalt.messages.adapter.MessagesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MessagesFragment extends PepperSaltFragment {

  @Bind(R.id.messagesRecyclerView) RecyclerView messagesRecyclerView;
  @Bind(R.id.content_view) LinearLayout contentView;
  @Bind(R.id.progress_bar) ProgressBar progressBar;
  @Bind(R.id.empty_view) TextView emptyView;
  @Bind(R.id.error_view) TextView errorView;

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
    loadData();
  }

  private void loadData() {
    final RestService service = RestClient.getInstance().getRestService();

    service.getMessages(new Callback<List<Message>>() {
      @Override
      public void success(List<Message> messages, Response response) {
        final List<Person> people = new ArrayList<>();

        if (messages.size() == 0) {
          setEmptyView();
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
              }

              @Override
              public void failure(RetrofitError error) {
                setErrorView();
              }
            });
          }
        }
        setAuthorToMessage(messages, people);
        List<Object> data = new ArrayList<>();
        data.addAll(messages);
        adapter.setData(data);
        showContent();
      }

      @Override
      public void failure(RetrofitError error) {
        adapter.setData(new ArrayList<Object>());
        setErrorView();
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

  private void showContent() {
    contentView.setVisibility(View.VISIBLE);
    progressBar.setVisibility(View.GONE);
    errorView.setVisibility(View.GONE);
    emptyView.setVisibility(View.GONE);
  }

  private void setEmptyView() {
    contentView.setVisibility(View.GONE);
    progressBar.setVisibility(View.GONE);
    errorView.setVisibility(View.GONE);
    emptyView.setVisibility(View.VISIBLE);
  }

  private void setErrorView() {
    contentView.setVisibility(View.GONE);
    progressBar.setVisibility(View.GONE);
    emptyView.setVisibility(View.GONE);
    errorView.setVisibility(View.VISIBLE);
  }

  private void addFakeData() {
    Person author = new Person();
    Message message = new Message();

    author.setFirstName("Lucien");
    author.setLastName("Bramart");
    author.setUsername("bramar_l");
    author.setImageUrl("https://avatars.slack-edge.com/2016-01-06/17850350192_00b38f75688300858435_192.jpg");
    author.setPoints(10);
    message.setAuthor(author);
    message.setTime("2016-15-08 18:54:42");
    message.setMessage("Bonjour, ceci est un test de la team PepperSalt Android. Ceci est un message allongé afin de prendre plus d'espace");

    for (int i = 0; i < 7; ++i) {
      data.add(message);
    }
    adapter.setData(data);
  }
}
