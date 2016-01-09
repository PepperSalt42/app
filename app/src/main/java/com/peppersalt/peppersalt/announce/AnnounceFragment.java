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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AnnounceFragment extends PepperSaltFragment {

  @Bind(R.id.announceImage1) ImageView announceImageView1;
  @Bind(R.id.announceImage2) ImageView announceImageView2;

  private String url1;
  private String url2;
  private boolean url1Changed = true;
  private boolean url2Changed = true;

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
        getAnnouncesUrls();

        if (url1Changed) {
          Picasso.with(context)
              .load(url1)
              .into(announceImageView1);
        }
        if (url2Changed) {
          Picasso.with(context)
              .load(url2)
              .into(announceImageView2);
        }
        view.postDelayed(this, REFRESH_TIME);
      }
    };
    view.postDelayed(runnable, 2000);
  }

  private void getAnnouncesUrls() {
    List<String> urls = new ArrayList<>();
    int numberNewUrls = 0;

    urls.add("https://scontent-frt3-1.xx.fbcdn.net/hphotos-prn2/v/t1.0-9/10262128_10152411339397156_4357710638328506801_n.jpg?oh=bec1970dc397eb571be19fc5d9700eca&oe=56FF813B");
    urls.add("https://scontent-frt3-1.xx.fbcdn.net/hphotos-xfa1/v/t1.0-9/11150619_10206617126809825_7907319737566181179_n.jpg?oh=99fb072e8a8002121c19c2a11edfaecd&oe=57109646");
    for (String url : urls) {
      if (!url.equals(url1) && !url.equals(url2)) {
        numberNewUrls++;
      }
    }
    if (numberNewUrls == 0) {
      url1Changed = false;
      url2Changed = false;
      return ;
    }
    if (numberNewUrls == 2) {
      url1 = urls.get(0);
      url2 = urls.get(1);
      url1Changed = true;
      url2Changed = true;
      return ;
    }
    if (!url1.equals(urls.get(0)) || !url1.equals(urls.get(1))) {
      if (!url2.equals(urls.get(0))) {
        url1 = urls.get(0);
      }
      else {
        url1 = urls.get(1);
      }
      url1Changed = true;
    }
    else {
      if (!url1.equals(urls.get(0))) {
        url2 = urls.get(0);
      }
      else {
        url2 = urls.get(1);
      }
      url2Changed = true;
    }
  }
}
