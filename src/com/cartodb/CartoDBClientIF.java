package com.cartodb;

/**
 * Interface for CartoDB client implementation
 * @author canadensys
 *
 */
public interface CartoDBClientIF {
	
	/**
	 * Executes a query on the CartoDB server
	 * @param sqlQuery do not URL encode the query, this function will do it.
	 * @return response as JSON string or null if the query cannot be completed
	 */
	public String executeQuery(String sqlQuery);

}
