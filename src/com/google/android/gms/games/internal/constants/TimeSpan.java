package com.google.android.gms.games.internal.constants;

public final class TimeSpan
{
  private TimeSpan()
  {
    throw new AssertionError("Uninstantiable");
  }
  
  public static String bd(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("Unknown time span " + paramInt);
    case 0: 
      return "DAILY";
    case 1: 
      return "WEEKLY";
    }
    return "ALL_TIME";
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/android/gms/games/internal/constants/TimeSpan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */