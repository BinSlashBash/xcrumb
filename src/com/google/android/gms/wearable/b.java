package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.internal.kc;

public class b
  extends d<a>
  implements Result
{
  private final Status wJ;
  
  public b(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
    this.wJ = new Status(paramDataHolder.getStatusCode());
  }
  
  protected a g(int paramInt1, int paramInt2)
  {
    return new kc(this.BB, paramInt1, paramInt2);
  }
  
  protected String getPrimaryDataMarkerColumn()
  {
    return "path";
  }
  
  public Status getStatus()
  {
    return this.wJ;
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/android/gms/wearable/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */