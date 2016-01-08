package com.peppersalt.peppersalt.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.peppersalt.peppersalt.api.model.Person;

import java.util.List;

public class podiumView extends View {

  private List<Person> people;

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

  /**
   * Set the data here. All the objects should be instantiated here and not in the
   * {@link #onDraw(Canvas)} method.
   * @param people the list of people to put on the podium.
   */
  public void setData(List<Person> people) {
    this.people = people;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
  }
}
