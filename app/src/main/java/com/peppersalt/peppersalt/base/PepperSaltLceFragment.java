package com.peppersalt.peppersalt.base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.peppersalt.peppersalt.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class PepperSaltLceFragment extends Fragment {

  @Bind(R.id.content_view) View contentView;
  @Bind(R.id.progress_bar) View progressBar;
  @Bind(R.id.empty_view) View emptyView;
  @Bind(R.id.error_view) View errorView;

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
  }

  protected void showContent() {
    contentView.setVisibility(View.VISIBLE);
    progressBar.setVisibility(View.GONE);
    errorView.setVisibility(View.GONE);
    emptyView.setVisibility(View.GONE);
  }

  protected void showEmpty() {
    contentView.setVisibility(View.GONE);
    progressBar.setVisibility(View.GONE);
    errorView.setVisibility(View.GONE);
    emptyView.setVisibility(View.VISIBLE);
  }

  protected void showError() {
    contentView.setVisibility(View.GONE);
    progressBar.setVisibility(View.GONE);
    emptyView.setVisibility(View.GONE);
    errorView.setVisibility(View.VISIBLE);
  }
}
