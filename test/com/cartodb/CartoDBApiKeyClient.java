package com.cartodb;

import org.junit.Before;

import com.cartodb.impl.ApiKeyCartoDBClient;
import com.cartodb.impl.SecuredCartoDBClient;

public class CartoDBApiKeyClient extends CartoDBTestClient {
	
	

	
	@Before
	public void setUp() throws Exception {
		this.cartoDBCLient = new ApiKeyCartoDBClient(Secret.user, Secret.API_KEY);
	}
}
