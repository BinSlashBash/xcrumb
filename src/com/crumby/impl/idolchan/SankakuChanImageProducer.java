package com.crumby.impl.idolchan;

import com.crumby.lib.universal.UniversalImageProducer;

public class SankakuChanImageProducer
  extends UniversalImageProducer
{
  protected String getBaseUrl()
  {
    return "https://idol.sankakucomplex.com";
  }
  
  protected String getRegexForMatchingId()
  {
    return SankakuChanImageFragment.REGEX_URL;
  }
  
  protected String getScriptName()
  {
    return SankakuChanImageFragment.class.getSimpleName();
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/crumby/impl/idolchan/SankakuChanImageProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */