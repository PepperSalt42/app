package com.peppersalt.peppersalt.announce;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.peppersalt.peppersalt.R;
import com.peppersalt.peppersalt.api.RestClient;
import com.peppersalt.peppersalt.api.RestService;
import com.peppersalt.peppersalt.api.model.Announce;
import com.peppersalt.peppersalt.base.PepperSaltFragment;
import com.peppersalt.peppersalt.utils.RoundedTransformation;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AnnounceFragment extends PepperSaltFragment {

  @Bind(R.id.announceImage) ImageView announceImageView;
  @Bind(R.id.progress_bar) ProgressBar progressBar;
  @Bind(R.id.empty_view) TextView emptyView;
  @Bind(R.id.error_view) TextView errorView;

  private String url1;
  private boolean url1Changed = true;
  private Context context;

  private static int REFRESH_TIME = 10000;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_announce, container, false);
  }

  @Override
  public void onViewCreated(final View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);

    final Runnable runnable = new Runnable() {
      @Override
      public void run() {
        getAnnounceUrl(view);
        view.postDelayed(this, REFRESH_TIME);
      }
    };
    view.post(runnable);
  }

  private void getAnnounceUrl(final View view) {
    RestService service = RestClient.getInstance().getRestService();

    service.getLastImage(new Callback<Announce>() {
      @Override
      public void success(Announce announce, Response response) {
        String url = announce.getImageUrl();
        Context context = view.getContext();

        if (!url.equals(url1)) {
          int radius = (int) context.getResources().getDimension(R.dimen.announce_corner_radius);
          url1 = url;

          Picasso.with(context)
              .load(url1)
              .error(R.drawable.background_fragment_default)
              .transform(new RoundedTransformation(radius, 0))
              .into(announceImageView);
        }
        showContent();
      }

      @Override
      public void failure(RetrofitError error) {
        showError();
      }
    });
  }

  private void showContent() {
    announceImageView.setVisibility(View.VISIBLE);
    progressBar.setVisibility(View.GONE);
    errorView.setVisibility(View.GONE);
    emptyView.setVisibility(View.GONE);
  }

  private void showEmpty() {
    announceImageView.setVisibility(View.GONE);
    progressBar.setVisibility(View.GONE);
    errorView.setVisibility(View.GONE);
    emptyView.setVisibility(View.VISIBLE);
  }

  private void showError() {
    announceImageView.setVisibility(View.GONE);
    progressBar.setVisibility(View.GONE);
    emptyView.setVisibility(View.GONE);
    errorView.setVisibility(View.VISIBLE);
  }
}
