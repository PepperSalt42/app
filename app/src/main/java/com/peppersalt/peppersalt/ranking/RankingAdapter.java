package com.peppersalt.peppersalt.ranking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.peppersalt.peppersalt.R;
import com.peppersalt.peppersalt.api.model.Person;
import com.peppersalt.peppersalt.utils.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RankingAdapter extends RecyclerView.Adapter {

  private List<Person> data;

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new RankingCellViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.fragment_ranking_cell, parent, false));
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    RankingCellViewHolder vh = (RankingCellViewHolder) holder;
    Context context = vh.rankTv.getContext();
    Person person = data.get(position);
    int rank = position + 1;

    vh.rankTv.setText(String.format("%d", rank));
    vh.fullNameTv.setText(String.format("%s %s", person.getFirstName(), person.getLastName()));
    vh.pointsTv.setText(String.format("%dpts", person.getPoints()));

    Picasso.with(context)
        .load(person.getImageUrl())
        .error(R.drawable.unknown)
        .transform(new RoundedTransformation(
            (int)context.getResources().getDimension(R.dimen.avatar_corner_radius), 0))
        .into(vh.avatarIv);

    switch (rank) {
      default:
        ((View)vh.rankTv.getParent())
            .setBackground(context.getDrawable(R.drawable.background_ranking_cell_default));
        break ;
      case 1:
        ((View)vh.rankTv.getParent())
            .setBackground(context.getDrawable(R.drawable.background_ranking_cell_gold));
        break ;
      case 2:
        ((View)vh.rankTv.getParent())
            .setBackground(context.getDrawable(R.drawable.background_ranking_cell_silver));
        break ;
      case 3:
        ((View)vh.rankTv.getParent())
            .setBackground(context.getDrawable(R.drawable.background_ranking_cell_bronze));
        break ;
    }
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  public void setData(List<Person> data) {
    this.data = data;
    notifyDataSetChanged();
  }

  class RankingCellViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.rank) TextView rankTv;
    @Bind(R.id.avatar) ImageView avatarIv;
    @Bind(R.id.fullName) TextView fullNameTv;
    @Bind(R.id.points) TextView pointsTv;

    public RankingCellViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
