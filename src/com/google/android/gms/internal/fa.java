package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;

public final class fa
  extends fu<a, Drawable>
{
  public fa()
  {
    super(10);
  }
  
  public static final class a
  {
    public final int CR;
    public final int CS;
    
    public a(int paramInt1, int paramInt2)
    {
      this.CR = paramInt1;
      this.CS = paramInt2;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = true;
      boolean bool1;
      if (!(paramObject instanceof a)) {
        bool1 = false;
      }
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (this == paramObject);
        paramObject = (a)paramObject;
        if (((a)paramObject).CR != this.CR) {
          break;
        }
        bool1 = bool2;
      } while (((a)paramObject).CS == this.CS);
      return false;
    }
    
    public int hashCode()
    {
      return fo.hashCode(new Object[] { Integer.valueOf(this.CR), Integer.valueOf(this.CS) });
    }
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/android/gms/internal/fa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */