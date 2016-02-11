package com.cartodb;

import org.junit.Before;

import com.cartodb.impl.SecuredCartoDBClient;

public class CartoDBSecureClientTest extends CartoDBTestClient {

    // Here you have to put valid credentials in order to test using a valid user
    private final static String USERNAME = "YOUR_USERNAME";
    private final static String API_KEY = "YOUR_USER_API_KEY";
    private final static String CONSUMER_KEY = "YOUR_CONSUMER_KEY";
    private final static String CONSUMER_SECRET = "YOUR_CONSUMER_SECRET";

	@Before
	public void setUp() throws Exception {
		this.cartoDBCLient = new SecuredCartoDBClient(USERNAME, API_KEY, CONSUMER_KEY,
                CONSUMER_SECRET);
	}
}
