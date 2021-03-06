package com.google.android.gms.games.request;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class GameRequestBuffer
  extends d<GameRequest>
{
  public GameRequestBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  protected GameRequest getEntry(int paramInt1, int paramInt2)
  {
    return new GameRequestRef(this.BB, paramInt1, paramInt2);
  }
  
  protected String getPrimaryDataMarkerColumn()
  {
    return "external_request_id";
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/android/gms/games/request/GameRequestBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */