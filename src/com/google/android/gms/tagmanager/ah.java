package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ah
  extends aj
{
  private static final String ID = a.I.toString();
  private final cs WL;
  
  public ah(cs paramcs)
  {
    super(ID, new String[0]);
    this.WL = paramcs;
  }
  
  public boolean jX()
  {
    return false;
  }
  
  public d.a x(Map<String, d.a> paramMap)
  {
    paramMap = this.WL.lx();
    if (paramMap == null) {
      return dh.lT();
    }
    return dh.r(paramMap);
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/android/gms/tagmanager/ah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */