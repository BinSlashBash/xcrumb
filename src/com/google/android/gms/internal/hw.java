package com.google.android.gms.internal;

import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.b;
import com.google.android.gms.panorama.Panorama;
import com.google.android.gms.panorama.PanoramaApi;
import com.google.android.gms.panorama.PanoramaApi.PanoramaResult;

public class hw
  implements PanoramaApi
{
  public PendingResult<PanoramaApi.PanoramaResult> loadPanoramaInfo(GoogleApiClient paramGoogleApiClient, final Uri paramUri)
  {
    paramGoogleApiClient.a(new a()
    {
      protected void a(hx paramAnonymoushx)
      {
        paramAnonymoushx.a(this, paramUri, false);
      }
    });
  }
  
  public PendingResult<PanoramaApi.PanoramaResult> loadPanoramaInfoAndGrantAccess(GoogleApiClient paramGoogleApiClient, final Uri paramUri)
  {
    paramGoogleApiClient.a(new a()
    {
      protected void a(hx paramAnonymoushx)
      {
        paramAnonymoushx.a(this, paramUri, true);
      }
    });
  }
  
  private static abstract class a
    extends a.b<PanoramaApi.PanoramaResult, hx>
  {
    public a()
    {
      super();
    }
    
    public PanoramaApi.PanoramaResult X(final Status paramStatus)
    {
      new PanoramaApi.PanoramaResult()
      {
        public Status getStatus()
        {
          return paramStatus;
        }
        
        public Intent getViewerIntent()
        {
          return null;
        }
      };
    }
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/google/android/gms/internal/hw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */