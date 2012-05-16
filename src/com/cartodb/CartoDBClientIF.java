package com.cartodb;

import java.io.IOException;

import com.cartodb.model.CartoDBResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Interface for CartoDB client implementation
 * @author canadensys
 * @param <T>
 *
 */
public abstract class CartoDBClientIF {
	
	ObjectMapper jsonMapper = new ObjectMapper();
	/**
	 * Executes a query on the CartoDB server
	 * @param sqlQuery do not URL encode the query, this function will do it.
	 * @return response as JSON string or null if the query cannot be completed
	 * @throws CartoDBException 
	 */
	public abstract String executeQuery(String sqlQuery) throws CartoDBException;
	
	/**
	 * return a CartoDBResponse object with the response json parsed
	 * @param <T> object to map the columns
	 * @param sqlQuery
	 * @return CartoDBResponse object
	 * @throws CartoDBException
	 */
	public <T> CartoDBResponse<T> request(String sqlQuery) throws CartoDBException {
		String json = executeQuery(sqlQuery);
		CartoDBResponse<T> response;
		try {
			response = jsonMapper.readValue(json, new TypeReference<CartoDBResponse<T>>(){});
			
		} catch (Exception e) {
			throw new CartoDBException(e.getMessage());
		}
		return response;
	}
	

}
