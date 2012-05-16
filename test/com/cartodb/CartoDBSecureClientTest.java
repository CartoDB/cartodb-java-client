package com.cartodb;

import org.junit.Before;

import com.cartodb.impl.SecuredCartoDBClient;

public class CartoDBSecureClientTest extends CartoDBTestClient {
	

	
	@Before
	public void setUp() throws Exception {
		this.cartoDBCLient = new SecuredCartoDBClient(Secret.user, Secret.password, Secret.CONSUMER_KEY, Secret.CONSUMER_SECRET);
	}
}
