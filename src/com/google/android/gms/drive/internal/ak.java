package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

public class ak
  implements Parcelable.Creator<RemoveEventListenerRequest>
{
  static void a(RemoveEventListenerRequest paramRemoveEventListenerRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramRemoveEventListenerRequest.xH);
    b.a(paramParcel, 2, paramRemoveEventListenerRequest.Ew, paramInt, false);
    b.c(paramParcel, 3, paramRemoveEventListenerRequest.ES);
    b.F(paramParcel, i);
  }
  
  public RemoveEventListenerRequest Y(Parcel paramParcel)
  {
    int i = 0;
    int k = a.o(paramParcel);
    DriveId localDriveId = null;
    int j = 0;
    if (paramParcel.dataPosition() < k)
    {
      int m = a.n(paramParcel);
      switch (a.R(m))
      {
      default: 
        a.b(paramParcel, m);
      }
      for (;;)
      {
        break;
        j = a.g(paramParcel, m);
        continue;
        localDriveId = (DriveId)a.a(paramParcel, m, DriveId.CREATOR);
        continue;
        i = a.g(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new RemoveEventListenerRequest(j, localDriveId, i);
  }
  
  public RemoveEventListenerRequest[] aC(int paramInt)
  {
    return new RemoveEventListenerRequest[paramInt];
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/android/gms/drive/internal/ak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */