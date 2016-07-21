package com.cartodb;

import org.scribe.model.OAuthConfig;
import org.scribe.oauth.OAuthService;

public class CartoDBAPI extends XAuthAPI
{
  private static final String REQUEST_TOKEN_RESOURCE = ".carto.com/oauth/request_token";
  private static final String ACCESS_TOKEN_RESOURCE = ".carto.com/oauth/access_token";
  
  protected String username;
  protected String password;
  
  public CartoDBAPI(String user, String password) {
	  this.username = user;
	  this.password = password;
  }
  
  public String getUsername() {
	  return username;
  }
	
  public String getPassword() {
	  return password;
  }

	
  @Override
  public OAuthService createService(OAuthConfig config)
  {
    return new XAuth(this, config);
  }
		  
  @Override
  public String getAccessTokenEndpoint()
  {
    return "http://" + username +  ACCESS_TOKEN_RESOURCE;
  }

  @Override
  public String getRequestTokenEndpoint()
  {
    return "http://" + username + REQUEST_TOKEN_RESOURCE;
  }


  public static class SSL extends CartoDBAPI
  {
    public SSL(String user, String password) {
		super(user, password);
	}

	@Override
    public String getAccessTokenEndpoint()
    {
      return "https://" + username + ACCESS_TOKEN_RESOURCE;
    }

    @Override
    public String getRequestTokenEndpoint()
    {
      return "https://" + username + REQUEST_TOKEN_RESOURCE;
    }
    
    public OAuthService createService(OAuthConfig config)
    {
      return new XAuth(this, config);
    }
  }

}

