package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.drive.query.internal.FieldWithSortOrder;
import java.util.ArrayList;

public class b
  implements Parcelable.Creator<SortOrder>
{
  static void a(SortOrder paramSortOrder, Parcel paramParcel, int paramInt)
  {
    paramInt = com.google.android.gms.common.internal.safeparcel.b.p(paramParcel);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 1000, paramSortOrder.xH);
    com.google.android.gms.common.internal.safeparcel.b.b(paramParcel, 1, paramSortOrder.GF, false);
    com.google.android.gms.common.internal.safeparcel.b.F(paramParcel, paramInt);
  }
  
  public SortOrder[] aH(int paramInt)
  {
    return new SortOrder[paramInt];
  }
  
  public SortOrder ad(Parcel paramParcel)
  {
    int j = a.o(paramParcel);
    int i = 0;
    ArrayList localArrayList = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.n(paramParcel);
      switch (a.R(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 1: 
        localArrayList = a.c(paramParcel, k, FieldWithSortOrder.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new SortOrder(i, localArrayList);
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/android/gms/drive/query/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */