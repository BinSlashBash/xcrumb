package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;

public class a
  extends com.google.android.gms.drive.metadata.a<Boolean>
{
  public a(String paramString, int paramInt)
  {
    super(paramString, paramInt);
  }
  
  protected void a(Bundle paramBundle, Boolean paramBoolean)
  {
    paramBundle.putBoolean(getName(), paramBoolean.booleanValue());
  }
  
  protected Boolean d(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    return Boolean.valueOf(paramDataHolder.getBoolean(getName(), paramInt1, paramInt2));
  }
  
  protected Boolean f(Bundle paramBundle)
  {
    return Boolean.valueOf(paramBundle.getBoolean(getName()));
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/android/gms/drive/metadata/internal/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */