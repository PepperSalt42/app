package com.peppersalt.peppersalt.announce;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.peppersalt.peppersalt.R;
import com.peppersalt.peppersalt.base.PepperSaltFragment;
import com.peppersalt.peppersalt.utils.RoundedTransformation;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AnnounceFragment extends PepperSaltFragment {

  @Bind(R.id.announceImage1) ImageView announceImageView1;

  private String url1;
  private boolean url1Changed = true;

  private static int REFRESH_TIME = 60000;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_announce, container, false);
  }

  @Override
  public void onViewCreated(final View view, Bundle savedInstanceState) {
    final Context context = view.getContext();
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);

    final Runnable runnable = new Runnable() {
      @Override
      public void run() {
        getAnnounceUrl();

        if (url1Changed) {
          int radius = (int) context.getResources().getDimension(R.dimen.announce_corner_radius);

          Picasso.with(context)
              .load(url1)
              .transform(new RoundedTransformation(radius, 0))
              .into(announceImageView1);
          url1Changed = false;
        }
        view.postDelayed(this, REFRESH_TIME);
      }
    };
    view.postDelayed(runnable, 2000);
  }

  private void getAnnounceUrl() {
    String url;

    url = "https://ludovicpost.me/announce.png ";
    if (!url.equals(url1)) {
      url1 = url;
      url1Changed = true;
    }
  }
}
