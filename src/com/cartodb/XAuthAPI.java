package com.cartodb;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

public class XAuthAPI extends DefaultApi10a {

	@Override
	public String getAccessTokenEndpoint() {
		return null;
	}

	@Override
	public String getAuthorizationUrl(Token requestToken) {
		return null;
	}

	@Override
	public String getRequestTokenEndpoint() {
		return null;
	}
	
	public String getUsername() {
		throw new UnsupportedOperationException("getUsername method must be implemented");
	}
	
	public String getPassword() {
		throw new UnsupportedOperationException("getPassword method must be implemented");
	}

}
