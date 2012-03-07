package com.cartodb.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;

import com.cartodb.CartoDBClientIF;

/**
 * CartoDB client implementation to access public resources. You can only send SELECT queries to public
 * resources.
 * @author canadensys
 *
 */
public class CartoDBClient implements CartoDBClientIF{
	
	private static final String DEFAULT_API_VERSION = "1";
	private static final String ENCODING = "UTF-8";
	
	private static final String SQL_API_BASE_URL = "http://%s.cartodb.com/api/v%s/sql/?q=";
	
	private String user;
	private String apiVersion = DEFAULT_API_VERSION;
	private String apiURL = null;
	
	/**
	 * Default constructor
	 */
	public CartoDBClient(){}
	
	/**
	 * After this constructor, the object is ready to use.
	 * @param user
	 */
	public CartoDBClient(String user){
		this.user = user;
		init();
	}
	
	/**
	 * After this constructor, the object is ready to use.
	 * @param user
	 * @param apiVersion
	 */
	public CartoDBClient(String user, String apiVersion){
		this.user = user;
		this.apiVersion = apiVersion;
		init();
	}
	
	/**
	 * Initialization method for a regular CartoDB client.
	 * You only need it if you're using the default constructor.
	 */
	public void init(){
		apiURL = String.format(SQL_API_BASE_URL, user, apiVersion);
	}
	
	/**
	 * Send a sqlQuery to the CartoDB server.
	 * The query will be sent in a URL parameter of a GET so, you should avoid very large query string.
	 * @param sqlQuery
	 */
	@Override
	public String executeQuery(String sqlQuery){
		String json = null;
		
		if(apiURL == null){
			System.out.println("Error : uninitialized " + getClass().getName());
			return null;
		}
		
		try {
			sqlQuery = URLEncoder.encode(sqlQuery,ENCODING);
			json = IOUtils.toString(new URL(apiURL+sqlQuery), ENCODING);
		} catch (MalformedURLException e) {
			System.out.println("Could not get URL " + apiURL+sqlQuery);
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (IOException e) {
			System.out.println("Could not execute " + sqlQuery+ " on CartoDB : ");
			e.printStackTrace();
		}
		return json;
	}
	
	public void setUser(String user){
		this.user = user;
	}
	
	/**
	 * Set the API version to use.
	 * @param apiVersion number part only as String
	 */
	public void setApiVersion(String apiVersion){
		this.apiVersion = apiVersion;
	}
}
