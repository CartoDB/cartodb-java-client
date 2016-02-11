package com.cartodb;

import org.junit.Before;

import com.cartodb.impl.ApiKeyCartoDBClient;

public class CartoDBApiKeyClient extends CartoDBTestClient {

    // Here you have to put valid credentials in order to test using a valid user
	private final static String USERNAME = "YOUR_USERNAME";
	private final static String API_KEY = "YOUR_USER_API_KEY";
	
	@Before
	public void setUp() throws Exception {
		this.cartoDBCLient = new ApiKeyCartoDBClient(USERNAME, API_KEY);
	}
}
