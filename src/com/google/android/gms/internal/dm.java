package com.google.android.gms.internal;

import android.os.Bundle;

public class dm
{
  private final Object li = new Object();
  private final String qA;
  private int qV;
  private int qW;
  private final dj qx;
  
  dm(dj paramdj, String paramString)
  {
    this.qx = paramdj;
    this.qA = paramString;
  }
  
  public dm(String paramString)
  {
    this(dj.bq(), paramString);
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    synchronized (this.li)
    {
      this.qV = paramInt1;
      this.qW = paramInt2;
      this.qx.a(this.qA, this);
      return;
    }
  }
  
  public Bundle toBundle()
  {
    synchronized (this.li)
    {
      Bundle localBundle = new Bundle();
      localBundle.putInt("pmnli", this.qV);
      localBundle.putInt("pmnll", this.qW);
      return localBundle;
    }
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/android/gms/internal/dm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */