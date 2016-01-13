/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.peppersalt.peppersalt.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.peppersalt.peppersalt.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * A collection of utility methods, all static.
 */
public class Utils {

  /*
   * Making sure public utility methods remain static
   */
  private Utils() {
  }

  /**
   * Returns the screen/display size
   */
  public static Point getDisplaySize(Context context) {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    return size;
  }

  /**
   * Shows a (long) toast
   */
  public static void showToast(Context context, String msg) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
  }

  /**
   * Shows a (long) toast.
   */
  public static void showToast(Context context, int resourceId) {
    Toast.makeText(context, context.getString(resourceId), Toast.LENGTH_LONG).show();
  }

  public static int convertDpToPixel(Context ctx, int dp) {
    float density = ctx.getResources().getDisplayMetrics().density;
    return Math.round((float) dp * density);
  }

  /**
   * This method converts device specific pixels to density independent pixels.
   *
   * @param px A value in px (pixels) unit. Which we need to convert into db
   * @param context Context to get resources and device specific display metrics
   * @return A float value to represent dp equivalent to px value
   */
  public static float convertPixelsToDp(float px, Context context){
    Resources resources = context.getResources();
    DisplayMetrics metrics = resources.getDisplayMetrics();
    float dp = px / (metrics.densityDpi / 160f);
    return dp;
  }

  /**
   * Formats time in milliseconds to hh:mm:ss string format.
   */
  public static String formatMillis(int millis) {
    String result = "";
    int hr = millis / 3600000;
    millis %= 3600000;
    int min = millis / 60000;
    millis %= 60000;
    int sec = millis / 1000;
    if (hr > 0) {
      result += hr + ":";
    }
    if (min >= 0) {
      if (min > 9) {
        result += min + ":";
      }
      else {
        result += "0" + min + ":";
      }
    }
    if (sec > 9) {
      result += sec;
    }
    else {
      result += "0" + sec;
    }
    return result;
  }

  public static Map<String, String> getEmojisFromJsonArray(Context context) {
    JSONArray jsonArrayEmojis = getJsonFromByteArray(context);
    Map<String, String> emojis = new HashMap<>();

    try {
      for (int i = 0; i < jsonArrayEmojis.length(); ++i) {
        JSONObject jsonObject = jsonArrayEmojis.getJSONObject(i);

        emojis.put(
            String.format(":%s:", jsonObject.getString("short_name")),
            String.format("[%s]", jsonObject.getString("unified").toLowerCase()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return emojis;
  }

  private static JSONArray getJsonFromByteArray(Context context) {
    InputStream inputStream = context.getResources().openRawResource(R.raw.emoji_pretty);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    int ctr;

    try {
      ctr = inputStream.read();
      while (ctr != -1) {
        byteArrayOutputStream.write(ctr);
        ctr = inputStream.read();
      }
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Log.v("Text Data", byteArrayOutputStream.toString());
    try {
      return new JSONArray(byteArrayOutputStream.toString());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
