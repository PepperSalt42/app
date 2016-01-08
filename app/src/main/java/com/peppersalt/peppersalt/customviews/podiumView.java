package com.peppersalt.peppersalt.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.peppersalt.peppersalt.R;
import com.peppersalt.peppersalt.api.model.Person;

import java.util.List;

public class podiumView extends View {

  private List<Person> people;
  private int width;
  private int height;
  private Paint rectangle;
  private Paint number;

  /**
   * Default constructor for custom Views
   */
  public podiumView(Context context) {
    super(context);
  }

  /**
   * Default constructor for custom Views
   */
  public podiumView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   * Default constructor for custom Views
   */
  public podiumView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  private void drawPod(Canvas canvas, int x, int y,int w,int h, String n) {
    int xn = (x + w)/2;
    int yn = (y + h)/2;
    canvas.drawRect(x, y, w, h, rectangle);
    Log.e(getClass().getName(), String.format("%d %d %d %d", x, y, w, h));
    canvas.drawText(n, xn, yn, number);
  }

  /**
   * Set the data here. All the objects should be instantiated here and not in the
   * {@link #onDraw(Canvas)} method.
   * @param people the list of people to put on the podium.
   */
  public void setData(List<Person> people) {
    this.people = people;
    width = this.getWidth();
    height = this.getHeight();
    rectangle = new Paint();
    rectangle.setAntiAlias(true);
    rectangle.setColor(getResources().getColor(R.color.red));
    rectangle.setStyle(Paint.Style.FILL);
    number = new Paint();
    number.setColor(getResources().getColor(R.color.white));
    number.setTextSize(getResources().getDimension(R.dimen.podium_number_size));
    invalidate();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    Log.e("height", Integer.toString(height));
    if (width == 0 || height == 0 || rectangle == null || number == null) {
      return ;
    }
    super.onDraw(canvas);
    drawPod(canvas, width/2 - width/10, height/2 - height/10, width/2 + width/10, height/2 + 3*height/5, "1");
    drawPod(canvas, width/2 - 3*width/10, height/2 + height/10, width/2 - width/10, height/2 + 3*height/5, "2");
    drawPod(canvas, width/2 + width/10, height/2 + 3*height/20, width/2 + 3*width/10, height/2 + 3*height/5, "3");
  }
}
