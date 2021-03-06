package com.google.android.gms.games.internal.game;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class GameBadgeEntityCreator
  implements Parcelable.Creator<GameBadgeEntity>
{
  static void a(GameBadgeEntity paramGameBadgeEntity, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramGameBadgeEntity.getType());
    b.c(paramParcel, 1000, paramGameBadgeEntity.getVersionCode());
    b.a(paramParcel, 2, paramGameBadgeEntity.getTitle(), false);
    b.a(paramParcel, 3, paramGameBadgeEntity.getDescription(), false);
    b.a(paramParcel, 4, paramGameBadgeEntity.getIconImageUri(), paramInt, false);
    b.F(paramParcel, i);
  }
  
  public GameBadgeEntity ar(Parcel paramParcel)
  {
    int i = 0;
    Uri localUri = null;
    int k = a.o(paramParcel);
    String str1 = null;
    String str2 = null;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = a.n(paramParcel);
      switch (a.R(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        i = a.g(paramParcel, m);
        break;
      case 1000: 
        j = a.g(paramParcel, m);
        break;
      case 2: 
        str2 = a.n(paramParcel, m);
        break;
      case 3: 
        str1 = a.n(paramParcel, m);
        break;
      case 4: 
        localUri = (Uri)a.a(paramParcel, m, Uri.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new GameBadgeEntity(j, i, str2, str1, localUri);
  }
  
  public GameBadgeEntity[] bg(int paramInt)
  {
    return new GameBadgeEntity[paramInt];
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/android/gms/games/internal/game/GameBadgeEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */