package com.cartodb;

import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import com.cartodb.model.CartoDBResponse;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * base class for client tests
 * @author javi
 *
 */
@Ignore
public class CartoDBTestClient {

	CartoDBClientIF cartoDBCLient;
	
	/**
	 * wrong sql should raise a CartoDBException
	 * @throws CartoDBException 
	 */
	@Test(expected=CartoDBException.class)
	public void testWrongSql() throws CartoDBException {
		CartoDBResponse<String> res = cartoDBCLient.request("select * from asdasdasdas123asd limit 1");
		assertEquals(res.getTotal_rows(), 1);
	}
	
	@Test
	public void testClientSQL() throws CartoDBException {
		
		CartoDBResponse<Map<String, Object>> res = cartoDBCLient.request("select * from " + Secret.EXISTING_TABLE_WITH_DATA + " limit 1");
		assertEquals(res.getTotal_rows(), 1);
		assertTrue((Integer)res.getRows().get(0).get("cartodb_id") > 0);
		
	  
	}

}
