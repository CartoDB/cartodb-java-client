package com.cartodb;

public interface CartoDBClientIF {
	
	/**
	 * This executes a query on the CartoDB server
	 * @param sqlQuery
	 * @return response as JSON string
	 */
	public String executeQuery(String sqlQuery);

}
