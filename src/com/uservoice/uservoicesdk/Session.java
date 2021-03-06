package com.uservoice.uservoicesdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.uservoice.uservoicesdk.model.AccessToken;
import com.uservoice.uservoicesdk.model.ClientConfig;
import com.uservoice.uservoicesdk.model.Forum;
import com.uservoice.uservoicesdk.model.RequestToken;
import com.uservoice.uservoicesdk.model.Topic;
import com.uservoice.uservoicesdk.model.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

public class Session
{
  private static Session instance;
  private AccessToken accessToken;
  private ClientConfig clientConfig;
  private Config config;
  private Context context;
  private Map<String, String> externalIds = new HashMap();
  private Forum forum;
  private OAuthConsumer oauthConsumer;
  private RequestToken requestToken;
  private Runnable signinListener;
  private List<Topic> topics;
  private User user;
  
  public static Session getInstance()
  {
    if (instance == null) {
      instance = new Session();
    }
    return instance;
  }
  
  public static void reset()
  {
    instance = null;
  }
  
  public AccessToken getAccessToken()
  {
    return this.accessToken;
  }
  
  public ClientConfig getClientConfig()
  {
    return this.clientConfig;
  }
  
  public Config getConfig()
  {
    return this.config;
  }
  
  public Context getContext()
  {
    return this.context;
  }
  
  public String getEmail()
  {
    if (this.user != null) {
      return this.user.getEmail();
    }
    return getSharedPreferences().getString("user_email", null);
  }
  
  public Map<String, String> getExternalIds()
  {
    return this.externalIds;
  }
  
  public Forum getForum()
  {
    return this.forum;
  }
  
  public String getName()
  {
    if (this.user != null) {
      return this.user.getName();
    }
    return getSharedPreferences().getString("user_name", null);
  }
  
  public OAuthConsumer getOAuthConsumer()
  {
    if (this.oauthConsumer == null)
    {
      if (this.config.getKey() == null) {
        break label47;
      }
      this.oauthConsumer = new CommonsHttpOAuthConsumer(this.config.getKey(), this.config.getSecret());
    }
    for (;;)
    {
      return this.oauthConsumer;
      label47:
      if (this.clientConfig != null) {
        this.oauthConsumer = new CommonsHttpOAuthConsumer(this.clientConfig.getKey(), this.clientConfig.getSecret());
      }
    }
  }
  
  public RequestToken getRequestToken()
  {
    return this.requestToken;
  }
  
  public SharedPreferences getSharedPreferences()
  {
    return this.context.getSharedPreferences("uv_" + this.config.getSite().replaceAll("\\W", "_"), 0);
  }
  
  public List<Topic> getTopics()
  {
    return this.topics;
  }
  
  public User getUser()
  {
    return this.user;
  }
  
  public void persistIdentity(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences().edit();
    localEditor.putString("user_name", paramString1);
    localEditor.putString("user_email", paramString2);
    localEditor.commit();
  }
  
  public void setAccessToken(Context paramContext, AccessToken paramAccessToken)
  {
    this.accessToken = paramAccessToken;
    paramAccessToken.persist(getSharedPreferences(), "access_token", "access_token");
    if (this.signinListener != null) {
      this.signinListener.run();
    }
  }
  
  public void setAccessToken(AccessToken paramAccessToken)
  {
    this.accessToken = paramAccessToken;
  }
  
  public void setClientConfig(ClientConfig paramClientConfig)
  {
    this.clientConfig = paramClientConfig;
  }
  
  public void setConfig(Config paramConfig)
  {
    this.config = paramConfig;
    if (paramConfig.getEmail() != null) {
      persistIdentity(paramConfig.getName(), paramConfig.getEmail());
    }
  }
  
  public void setContext(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public void setExternalId(String paramString1, String paramString2)
  {
    this.externalIds.put(paramString1, paramString2);
  }
  
  public void setForum(Forum paramForum)
  {
    this.forum = paramForum;
  }
  
  public void setRequestToken(RequestToken paramRequestToken)
  {
    this.requestToken = paramRequestToken;
  }
  
  public void setSignInListener(Runnable paramRunnable)
  {
    this.signinListener = paramRunnable;
  }
  
  public void setTopics(List<Topic> paramList)
  {
    this.topics = paramList;
  }
  
  public void setUser(User paramUser)
  {
    this.user = paramUser;
    persistIdentity(paramUser.getName(), paramUser.getEmail());
  }
}


/* Location:              /home/dev/Downloads/apk/dex2jar-2.0/crumby-dex2jar.jar!/com/uservoice/uservoicesdk/Session.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */