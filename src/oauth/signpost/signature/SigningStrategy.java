package oauth.signpost.signature;

import java.io.Serializable;
import oauth.signpost.http.HttpParameters;
import oauth.signpost.http.HttpRequest;

public abstract interface SigningStrategy
  extends Serializable
{
  public abstract String writeSignature(String paramString, HttpRequest paramHttpRequest, HttpParameters paramHttpParameters);
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/oauth/signpost/signature/SigningStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */