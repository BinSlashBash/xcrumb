package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;

public class b
  implements Parcelable.Creator<UserAddress>
{
  static void a(UserAddress paramUserAddress, Parcel paramParcel, int paramInt)
  {
    paramInt = com.google.android.gms.common.internal.safeparcel.b.p(paramParcel);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 1, paramUserAddress.getVersionCode());
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 2, paramUserAddress.name, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 3, paramUserAddress.NB, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 4, paramUserAddress.NC, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 5, paramUserAddress.ND, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 6, paramUserAddress.NE, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 7, paramUserAddress.NF, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 8, paramUserAddress.NG, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 9, paramUserAddress.NH, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 10, paramUserAddress.qd, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 11, paramUserAddress.NI, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 12, paramUserAddress.NJ, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 13, paramUserAddress.NK, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 14, paramUserAddress.NL);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 15, paramUserAddress.NM, false);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 16, paramUserAddress.NN, false);
    com.google.android.gms.common.internal.safeparcel.b.F(paramParcel, paramInt);
  }
  
  public UserAddress aA(Parcel paramParcel)
  {
    int j = a.o(paramParcel);
    int i = 0;
    String str14 = null;
    String str13 = null;
    String str12 = null;
    String str11 = null;
    String str10 = null;
    String str9 = null;
    String str8 = null;
    String str7 = null;
    String str6 = null;
    String str5 = null;
    String str4 = null;
    String str3 = null;
    boolean bool = false;
    String str2 = null;
    String str1 = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.n(paramParcel);
      switch (a.R(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        str14 = a.n(paramParcel, k);
        break;
      case 3: 
        str13 = a.n(paramParcel, k);
        break;
      case 4: 
        str12 = a.n(paramParcel, k);
        break;
      case 5: 
        str11 = a.n(paramParcel, k);
        break;
      case 6: 
        str10 = a.n(paramParcel, k);
        break;
      case 7: 
        str9 = a.n(paramParcel, k);
        break;
      case 8: 
        str8 = a.n(paramParcel, k);
        break;
      case 9: 
        str7 = a.n(paramParcel, k);
        break;
      case 10: 
        str6 = a.n(paramParcel, k);
        break;
      case 11: 
        str5 = a.n(paramParcel, k);
        break;
      case 12: 
        str4 = a.n(paramParcel, k);
        break;
      case 13: 
        str3 = a.n(paramParcel, k);
        break;
      case 14: 
        bool = a.c(paramParcel, k);
        break;
      case 15: 
        str2 = a.n(paramParcel, k);
        break;
      case 16: 
        str1 = a.n(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new UserAddress(i, str14, str13, str12, str11, str10, str9, str8, str7, str6, str5, str4, str3, bool, str2, str1);
  }
  
  public UserAddress[] bu(int paramInt)
  {
    return new UserAddress[paramInt];
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/android/gms/identity/intents/model/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */