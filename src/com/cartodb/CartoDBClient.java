package com.cartodb;


import org.scribe.builder.*;
import org.scribe.model.*;
import org.scribe.oauth.*;

/**
 * sample code to access cartodb
 * @author javi
 *
 */
public class CartoDBClient
{
	
	protected static String user =  "YOURUSERNAME";
	protected static String password =  "YOUPASSWORD";
	protected static String CONSUMER_KEY="YOUR_CARTODB_CONSUMER";
	protected static String CONSUMER_SECRET="YOUR_CARTODB_SECRET";

	// sample SQL
	private static final String PROTECTED_RESOURCE_URL = "https://yourusuer.cartodb.com/api/v1/sql?q=select%20*%20from%20table";

	public static void main(String[] args)
	{
		OAuthService service = new ServiceBuilder()
			.provider(new CartoDBAPI.SSL(user, password))
			.apiKey(CONSUMER_KEY)
			.apiSecret(CONSUMER_SECRET)
			.build();

		Token accessToken = service.getAccessToken(null, null);
		// you could save the token attributes to use it later
		OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
		service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println("--- response ---");
		System.out.println(response.getBody());
  }

}