package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fo;
import com.google.android.gms.internal.fo.a;
import com.google.android.gms.internal.fq;

public class StreetViewPanoramaCamera
  implements SafeParcelable
{
  public static final StreetViewPanoramaCameraCreator CREATOR = new StreetViewPanoramaCameraCreator();
  private StreetViewPanoramaOrientation Tr;
  public final float bearing;
  public final float tilt;
  private final int xH;
  public final float zoom;
  
  public StreetViewPanoramaCamera(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this(1, paramFloat1, paramFloat2, paramFloat3);
  }
  
  StreetViewPanoramaCamera(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    boolean bool;
    if ((-90.0F <= paramFloat2) && (paramFloat2 <= 90.0F))
    {
      bool = true;
      fq.b(bool, "Tilt needs to be between -90 and 90 inclusive");
      this.xH = paramInt;
      this.zoom = paramFloat1;
      this.tilt = (0.0F + paramFloat2);
      if (paramFloat3 > 0.0D) {
        break label100;
      }
    }
    label100:
    for (paramFloat1 = paramFloat3 % 360.0F + 360.0F;; paramFloat1 = paramFloat3)
    {
      this.bearing = (paramFloat1 % 360.0F);
      this.Tr = new StreetViewPanoramaOrientation.Builder().tilt(paramFloat2).bearing(paramFloat3).build();
      return;
      bool = false;
      break;
    }
  }
  
  public static Builder builder()
  {
    return new Builder();
  }
  
  public static Builder builder(StreetViewPanoramaCamera paramStreetViewPanoramaCamera)
  {
    return new Builder(paramStreetViewPanoramaCamera);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof StreetViewPanoramaCamera)) {
        return false;
      }
      paramObject = (StreetViewPanoramaCamera)paramObject;
    } while ((Float.floatToIntBits(this.zoom) == Float.floatToIntBits(((StreetViewPanoramaCamera)paramObject).zoom)) && (Float.floatToIntBits(this.tilt) == Float.floatToIntBits(((StreetViewPanoramaCamera)paramObject).tilt)) && (Float.floatToIntBits(this.bearing) == Float.floatToIntBits(((StreetViewPanoramaCamera)paramObject).bearing)));
    return false;
  }
  
  public StreetViewPanoramaOrientation getOrientation()
  {
    return this.Tr;
  }
  
  int getVersionCode()
  {
    return this.xH;
  }
  
  public int hashCode()
  {
    return fo.hashCode(new Object[] { Float.valueOf(this.zoom), Float.valueOf(this.tilt), Float.valueOf(this.bearing) });
  }
  
  public String toString()
  {
    return fo.e(this).a("zoom", Float.valueOf(this.zoom)).a("tilt", Float.valueOf(this.tilt)).a("bearing", Float.valueOf(this.bearing)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    StreetViewPanoramaCameraCreator.a(this, paramParcel, paramInt);
  }
  
  public static final class Builder
  {
    public float bearing;
    public float tilt;
    public float zoom;
    
    public Builder() {}
    
    public Builder(StreetViewPanoramaCamera paramStreetViewPanoramaCamera)
    {
      this.zoom = paramStreetViewPanoramaCamera.zoom;
      this.bearing = paramStreetViewPanoramaCamera.bearing;
      this.tilt = paramStreetViewPanoramaCamera.tilt;
    }
    
    public Builder bearing(float paramFloat)
    {
      this.bearing = paramFloat;
      return this;
    }
    
    public StreetViewPanoramaCamera build()
    {
      return new StreetViewPanoramaCamera(this.zoom, this.tilt, this.bearing);
    }
    
    public Builder orientation(StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation)
    {
      this.tilt = paramStreetViewPanoramaOrientation.tilt;
      this.bearing = paramStreetViewPanoramaOrientation.bearing;
      return this;
    }
    
    public Builder tilt(float paramFloat)
    {
      this.tilt = paramFloat;
      return this;
    }
    
    public Builder zoom(float paramFloat)
    {
      this.zoom = paramFloat;
      return this;
    }
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/android/gms/maps/model/StreetViewPanoramaCamera.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */