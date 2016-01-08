package com.peppersalt.peppersalt.messages.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates.AbsAdapterDelegate;
import com.peppersalt.peppersalt.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessageLabelAdapterDelegate extends AbsAdapterDelegate<List<Object>> {

  LayoutInflater inflater;

  public MessageLabelAdapterDelegate(Activity activity, int viewType) {
    super(viewType);
    inflater = activity.getLayoutInflater();
  }

  @Override
  public boolean isForViewType(@NonNull List<Object> items, int position) {
    return items.get(position) instanceof String;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
    return new LabelViewHolder(inflater.inflate(R.layout.fragment_messages_label, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull List<Object> items, int position,
                               @NonNull RecyclerView.ViewHolder holder) {
    LabelViewHolder vh = (LabelViewHolder) holder;
    String label = (String)items.get(position);

    vh.label.setText(label);
  }

  class LabelViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.categoryLabel) TextView label;

    public LabelViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
