package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.b;

public class j
{
  static void a(TileOverlayOptions paramTileOverlayOptions, Parcel paramParcel, int paramInt)
  {
    paramInt = b.p(paramParcel);
    b.c(paramParcel, 1, paramTileOverlayOptions.getVersionCode());
    b.a(paramParcel, 2, paramTileOverlayOptions.iG(), false);
    b.a(paramParcel, 3, paramTileOverlayOptions.isVisible());
    b.a(paramParcel, 4, paramTileOverlayOptions.getZIndex());
    b.F(paramParcel, paramInt);
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/android/gms/maps/model/j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */