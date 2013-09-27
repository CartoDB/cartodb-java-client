package com.cartodb;

import java.util.Map;

import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuth10aServiceImpl;


class XAuth extends OAuth10aServiceImpl {

  private OAuthConfig config;
  private XAuthAPI api;
	  
  public XAuth(XAuthAPI api, OAuthConfig config) {
		super(api, config);
		this.api = api;
		this.config = config;
  }
  
  public Token getAccessToken(Token requestToken, Verifier verifier)
  {
    OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), api.getAccessTokenEndpoint());
    if(requestToken != null) {
	    request.addOAuthParameter(OAuthConstants.TOKEN, requestToken.getToken());
	    request.addOAuthParameter(OAuthConstants.VERIFIER, verifier.getValue());
    }
    request.addBodyParameter("x_auth_username", api.getUsername());
    request.addBodyParameter("x_auth_password", api.getPassword());
    request.addBodyParameter("x_auth_mode", "client_auth");
    addOAuthParams(request, requestToken);
    addSignature(request);
    Response response = request.send();
    String body = response.getBody();
    return api.getAccessTokenExtractor().extract(body);
  }
  
  private void addOAuthParams(OAuthRequest request, Token token)
  {
    request.addOAuthParameter(OAuthConstants.TIMESTAMP, api.getTimestampService().getTimestampInSeconds());
    request.addOAuthParameter(OAuthConstants.NONCE, api.getTimestampService().getNonce());
    request.addOAuthParameter(OAuthConstants.CONSUMER_KEY, config.getApiKey());
    request.addOAuthParameter(OAuthConstants.SIGN_METHOD, api.getSignatureService().getSignatureMethod());
    request.addOAuthParameter(OAuthConstants.VERSION, getVersion());
    if(config.hasScope()) request.addOAuthParameter(OAuthConstants.SCOPE, config.getScope());
    request.addOAuthParameter(OAuthConstants.SIGNATURE, getSignature(request, token));
  }
  
  private String getSignature(OAuthRequest request, Token token)
  {
    String baseString = api.getBaseStringExtractor().extract(request);
    return api.getSignatureService().getSignature(baseString, config.getApiSecret(), "");
  }

  private void addSignature(OAuthRequest request)
  {
    switch (config.getSignatureType())
    {
      case Header:
        String oauthHeader = api.getHeaderExtractor().extract(request);
        request.addHeader(OAuthConstants.HEADER, oauthHeader);
        break;
      case QueryString:
        for (Map.Entry<String, String> entry : request.getOauthParameters().entrySet())
        {
          request.addQuerystringParameter(entry.getKey(), entry.getValue());
        }
        break;
    }
  }
	
}