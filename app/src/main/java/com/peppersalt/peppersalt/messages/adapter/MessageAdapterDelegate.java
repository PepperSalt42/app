package com.peppersalt.peppersalt.messages.adapter;

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
import com.peppersalt.peppersalt.emoji.customview.EmojiTextView;
import com.peppersalt.peppersalt.emoji.EmojiParser;
import com.peppersalt.peppersalt.utils.RoundedTransformation;
import com.peppersalt.peppersalt.utils.Utils;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessageAdapterDelegate extends AbsAdapterDelegate<List<Object>> {

  private Context context;
  private Map<String, String> emojis;

  public MessageAdapterDelegate(View view, int viewType) {
    super(viewType);
    this.context = view.getContext();
    emojis = Utils.getEmojisFromJsonArray(context);
  }

  @Override
  public boolean isForViewType(@NonNull List<Object> items, int position) {
    return items.get(position) instanceof Message;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
    return new MessageViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.fragment_messages_row, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull List<Object> items, int position,
                               @NonNull RecyclerView.ViewHolder holder) {
    MessageViewHolder vh = (MessageViewHolder) holder;
    Message message = (Message) items.get(position);
    Person author = message.getAuthor();
    DateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm");

    if (author != null) {
      String authorName = String.format("%s %s", author.getFirstName(), author.getLastName());
      vh.messageAuthorName.setText(authorName);
      Picasso.with(context)
          .load(author.getImageUrl())
          .error(R.drawable.unknown)
          .transform(new RoundedTransformation(
              (int)context.getResources().getDimension(R.dimen.avatar_corner_radius), 0))
          .into(vh.authorImageView);
    }

    if (position % 2 == 1) {
      ((View)vh.authorImageView.getParent())
          .setBackgroundColor(context.getResources().getColor(R.color.message_row_odd_color));
    }
    else {
      ((View)vh.authorImageView.getParent())
          .setBackgroundColor(context.getResources().getColor(R.color.background_fragments_color));
    }
    vh.messageTime.setText(dateFormat.format(message.getTime()));
    vh.message.setText(EmojiParser.demojizedText(emojis, message.getMessage()));
  }

  class MessageViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.messageAuthorImage) ImageView authorImageView;
    @Bind(R.id.messageAuthor) TextView messageAuthorName;
    @Bind(R.id.messageTime) TextView messageTime;
    @Bind(R.id.message) EmojiTextView message;

    public MessageViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
