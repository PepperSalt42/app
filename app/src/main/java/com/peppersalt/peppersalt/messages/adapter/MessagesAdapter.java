package com.peppersalt.peppersalt.messages.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates.AdapterDelegatesManager;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter {

  private AdapterDelegatesManager<List<Object>> manager;
  private List<Object> data;

  public MessagesAdapter(Activity activity, List<Object> data) {
    this.data = data;

    manager = new AdapterDelegatesManager<>();
    manager.addDelegate(new MessageLabelAdapterDelegate(activity, 0));
    manager.addDelegate(new MessageAdapterDelegate(activity, 1));
  }

  @Override
  public int getItemViewType(int position) {
    return manager.getItemViewType(data, position);
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return manager.onCreateViewHolder(parent, viewType);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    manager.onBindViewHolder(data, position, holder);
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  public void setData(List<Object> dataForView) {
    this.data = dataForView;
    this.notifyDataSetChanged();
  }

  public List<Object> getData() {
    return data;
  }
}
