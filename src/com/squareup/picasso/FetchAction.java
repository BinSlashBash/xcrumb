package com.squareup.picasso;

import android.graphics.Bitmap;

class FetchAction
  extends Action<Void>
{
  FetchAction(Picasso paramPicasso, Request paramRequest, boolean paramBoolean, String paramString)
  {
    super(paramPicasso, null, paramRequest, paramBoolean, false, 0, null, paramString);
  }
  
  void complete(Bitmap paramBitmap, Picasso.LoadedFrom paramLoadedFrom) {}
  
  public void error(Exception paramException) {}
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/squareup/picasso/FetchAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */