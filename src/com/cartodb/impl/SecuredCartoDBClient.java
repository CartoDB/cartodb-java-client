package com.cartodb.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.cartodb.CartoDBAPI;
import com.cartodb.CartoDBClientIF;
import com.cartodb.CartoDBException;

/**
 * CartoDB client implementation to access protected resources on CartoDB using OAuth.
 * You can perform any SQL queries so please, be careful.
 * @author canadensys
 *
 */
public class SecuredCartoDBClient extends CartoDBClientIF{
	
	private static final String DEFAULT_API_VERSION = "1";
	private static final String SQL_API_BASE_URL = "https://%s.carto.com/api/v%s/sql";
	
	private OAuthService oAuthService = null;
	private Token accessToken = null;
	private String apiVersion = DEFAULT_API_VERSION;
	private String securedApiUrl = null;
	
	private String user;
	private String password;
	private String consumerKey;
	private String consumerSecret;
	
	/**
	 * Default constructor
	 */
	public SecuredCartoDBClient(){}
	
	/**
	 * After this constructor, the object is ready to use.
	 * @param user
	 * @param password
	 * @param consumerKey
	 * @param consumerSecret
	 */
	public SecuredCartoDBClient(String user, String password, String consumerKey, String consumerSecret){
		this.user = user;
		this.password = password;
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		init();
	}
	
	/**
	 * Initialization method for a secured CartoDB client.
	 * You only need it if you're using the default constructor.
	 */
	public void init(){
		oAuthService = new ServiceBuilder()
			.provider(new CartoDBAPI.SSL(user, password))
			.apiKey(consumerKey)
			.apiSecret(consumerSecret)
			.build();
		accessToken = oAuthService.getAccessToken(null, null);
		securedApiUrl = String.format(SQL_API_BASE_URL, user, apiVersion);
	}
	
	/**
	 * Send a sqlQuery (or command) to the CartoDB server.
	 * The query/command will be sent in the body of a POST so, it's possible to send
	 * a large query/command string.
	 * @param sqlQuery 
	 */
	@Override
	public String executeQuery(String sqlQuery) throws CartoDBException {
		String json = null;
		if(oAuthService == null){
			System.out.println("Error : uninitialized " + getClass().getName());
			return null;
		}
		OAuthRequest request = new OAuthRequest(Verb.POST, securedApiUrl);
		request.addBodyParameter("q", sqlQuery);
		oAuthService.signRequest(accessToken, request);
		
		Response response = request.send();
		
		if(!response.isSuccessful()){
			throw new CartoDBException("The query " + sqlQuery + " failed. Response code : " + response.getCode());
		}
		json = response.getBody();
		return json;
	}
	
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}
	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public void setUser(String user) {
		this.user = user;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Set the API version to use.
	 * @param apiVersion number part only as String
	 */
	public void setApiVersion(String apiVersion){
		this.apiVersion = apiVersion;
	}
}
