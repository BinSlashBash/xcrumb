package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class db
  extends dc
{
  private static final String ID = a.ah.toString();
  
  public db()
  {
    super(ID);
  }
  
  protected boolean a(String paramString1, String paramString2, Map<String, d.a> paramMap)
  {
    return paramString1.startsWith(paramString2);
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/android/gms/tagmanager/db.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */