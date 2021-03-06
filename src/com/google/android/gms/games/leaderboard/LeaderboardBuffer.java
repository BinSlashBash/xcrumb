package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class LeaderboardBuffer
  extends d<Leaderboard>
{
  public LeaderboardBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  protected Leaderboard getEntry(int paramInt1, int paramInt2)
  {
    return new LeaderboardRef(this.BB, paramInt1, paramInt2);
  }
  
  protected String getPrimaryDataMarkerColumn()
  {
    return "external_leaderboard_id";
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/android/gms/games/leaderboard/LeaderboardBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */