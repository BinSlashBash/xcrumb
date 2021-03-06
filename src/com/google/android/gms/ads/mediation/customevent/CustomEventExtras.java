package com.google.android.gms.ads.mediation.customevent;

import com.google.ads.mediation.NetworkExtras;
import java.util.HashMap;

public final class CustomEventExtras
  implements NetworkExtras
{
  private final HashMap<String, Object> rQ = new HashMap();
  
  public Object getExtra(String paramString)
  {
    return this.rQ.get(paramString);
  }
  
  public void setExtra(String paramString, Object paramObject)
  {
    this.rQ.put(paramString, paramObject);
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/android/gms/ads/mediation/customevent/CustomEventExtras.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */