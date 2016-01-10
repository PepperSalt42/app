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
    final int count = 7;

    service.getMessages(count, new Callback<List<Message>>() {
      @Override
      public void success(final List<Message> messages, Response response) {
        final List<Integer> authorsIds;
        final List<Person> authors = new ArrayList<>();

        if (messages == null || messages.size() == 0) {
          showEmpty();
          return ;
        }
        authorsIds = getAuthors(messages);
        for (Integer id : authorsIds) {
          downloadUser(id, service, authors, authorsIds, messages);
        }
      }

      @Override
      public void failure(RetrofitError error) {
        adapter.setData(new ArrayList<>());
        showError();
      }
    });
  }

  private void downloadUser(int id, RestService service, final List<Person> authors,
                            final List<Integer> authorsIds, final List<Message> messages) {
    service.getUser(id, new Callback<Person>() {
      @Override
      public void success(Person person, Response response) {
        boolean areAllAuthorsDownloaded = true;

        authors.add(person);
        for (Integer authorId : authorsIds) {
          boolean isAuthorDownloaded = false;

          for (Person author : authors) {
            if (authorId.equals(author.getId())) {
              isAuthorDownloaded = true;
              break ;
            }
          }
          if (!isAuthorDownloaded) {
            areAllAuthorsDownloaded = false;
            break ;
          }
        }

        if (areAllAuthorsDownloaded) {
          setAuthorsToMessages(messages, authors);
          setData(messages);
        }
      }

      @Override
      public void failure(RetrofitError error) {
        showError();
      }
    });
  }

  private void setData(List<Message> messages) {
    List<Object> data = new ArrayList<>();
    data.addAll(messages);
      adapter.setData(data);
      showContent();
  }

  private List<Integer> getAuthors(List<Message> messages) {
    List<Integer> authorsIds = new ArrayList<>();

    for (Message message : messages) {
      if (!authorsIds.contains(message.getAuthorId())) {
        authorsIds.add(message.getAuthorId());
      }
    }
    return authorsIds;
  }

  private void setAuthorsToMessages(List<Message> messages, List<Person> people) {
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
