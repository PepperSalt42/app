package com.peppersalt.peppersalt.messages.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates.AbsAdapterDelegate;
import com.peppersalt.peppersalt.R;
import com.peppersalt.peppersalt.api.model.Message;
import com.peppersalt.peppersalt.api.model.Person;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessageAdapterDelegate extends AbsAdapterDelegate<List<Object>> {

  private Context context;
  private LayoutInflater inflater;

  public MessageAdapterDelegate(Activity activity, int viewType) {
    super(viewType);
    this.context = activity;
    this.inflater = activity.getLayoutInflater();
  }

  @Override
  public boolean isForViewType(@NonNull List<Object> items, int position) {
    return items.get(position) instanceof Message;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
    return new MessageViewHolder(inflater.inflate(R.layout.fragment_messages_row, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull List<Object> items, int position,
                               @NonNull RecyclerView.ViewHolder holder) {
    MessageViewHolder vh = (MessageViewHolder) holder;
    Message message = (Message) items.get(position);
    Person author = message.getAuthor();
    String authorName = String.format("%s %s", author.getFirstName(), author.getLastName());

    vh.messageAuthorName.setText(authorName);
    vh.messageTime.setText(message.getTime());
    vh.message.setText(message.getMessage());

    Picasso.with(context)
        .load(author.getImageUrl())
        .error(R.drawable.unknown)
        .resize(R.dimen.message_author_image_size, R.dimen.message_author_image_size)
        .centerCrop()
        .into(vh.authorImageView);
  }

  class MessageViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.messageAuthorImage) ImageView authorImageView;
    @Bind(R.id.messageAuthor) TextView messageAuthorName;
    @Bind(R.id.messageTime) TextView messageTime;
    @Bind(R.id.message) TextView message;

    public MessageViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
