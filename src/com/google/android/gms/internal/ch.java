package com.google.android.gms.internal;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;

public final class ch
  extends FrameLayout
  implements View.OnClickListener
{
  private final Activity nS;
  private final ImageButton oB;
  
  public ch(Activity paramActivity, int paramInt)
  {
    super(paramActivity);
    this.nS = paramActivity;
    setOnClickListener(this);
    this.oB = new ImageButton(paramActivity);
    this.oB.setImageResource(17301527);
    this.oB.setBackgroundColor(0);
    this.oB.setOnClickListener(this);
    this.oB.setPadding(0, 0, 0, 0);
    paramInt = dv.a(paramActivity, paramInt);
    addView(this.oB, new FrameLayout.LayoutParams(paramInt, paramInt, 17));
  }
  
  public void i(boolean paramBoolean)
  {
    ImageButton localImageButton = this.oB;
    if (paramBoolean) {}
    for (int i = 4;; i = 0)
    {
      localImageButton.setVisibility(i);
      return;
    }
  }
  
  public void onClick(View paramView)
  {
    this.nS.finish();
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/android/gms/internal/ch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */