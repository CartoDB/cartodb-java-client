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
	
	private static final String ENCODING = "UTF-8";
	private static final String SQL_API_BASE_URL = "http://%s.cartodb.com/api/v1/sql/?q=";
	
	private String user;
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
	 * Initialization method for a regular CartoDB client.
	 * You only need it if you're using the default constructor.
	 */
	public void init(){
		apiURL = String.format(SQL_API_BASE_URL, user);
	}
	
	public void setUser(String user){
		this.user = user;
	}

	@Override
	public String executeQuery(String sqlQuery){
		String json = null;
		try {
			sqlQuery = URLEncoder.encode(sqlQuery,ENCODING);
			json = IOUtils.toString(new URL(apiURL+sqlQuery), ENCODING);
		} catch (MalformedURLException e) {
			System.out.println("Could not get URL " + apiURL+sqlQuery);
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (IOException e) {
			System.out.println("Could not execute " + sqlQuery+ " on CartoDO : ");
			e.printStackTrace();
		}
		return json;
	}

}
