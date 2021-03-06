package com.crumby.impl.konachan;

import com.crumby.impl.BooruPoolGalleryProducer;
import com.crumby.lib.fragment.GalleryGridFragment;
import com.crumby.lib.fragment.producer.GalleryProducer;

public class KonachanPoolGalleryFragment
  extends GalleryGridFragment
{
  public static final String BASE_URL = "http://konachan.com/pool/show/";
  public static final boolean BREADCRUMB_ALT_NAME = true;
  public static final int BREADCRUMB_ICON = 2130837613;
  public static final String BREADCRUMB_NAME = "pools";
  public static final Class BREADCRUMB_PARENT_CLASS = KonachanPoolFragment.class;
  public static final String REGEX_BASE = KonachanPoolFragment.REGEX_BASE + "/show/";
  public static final String REGEX_URL = REGEX_BASE + "([0-9]+)*";
  public static final boolean SUGGESTABLE = true;
  
  public static String matchIdFromUrl(String paramString)
  {
    return matchIdFromUrl(REGEX_URL, paramString);
  }
  
  protected GalleryProducer createProducer()
  {
    return new BooruPoolGalleryProducer("http://konachan.com/pool/show/", KonachanFragment.class, REGEX_URL, "http://konachan.com", false);
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/crumby/impl/konachan/KonachanPoolGalleryFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */