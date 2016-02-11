package com.cartodb;

import java.util.Map;

import org.junit.Test;

import com.cartodb.model.CartoDBResponse;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * base class for client tests
 * @author javi
 *
 */
public class CartoDBTestClient {

	CartoDBClientIF cartoDBCLient;

	// Put here your test table in order to execute this tests
	private static final String TEST_TABLE = "TEST_TABLE_NAME";
	
	@Test(expected=CartoDBException.class)
	public void testWrongSql() throws CartoDBException {
		CartoDBResponse<String> res = cartoDBCLient.request("select * from asdasdasdas123asd limit 1");
		assertEquals(res.getTotal_rows(), 1);
	}
	
	@Test
	public void testClientSQL() throws CartoDBException {
		CartoDBResponse<Map<String, Object>> res = cartoDBCLient.request("select * FROM " + TEST_TABLE + " limit 1");
		assertEquals(res.getTotal_rows(), 1);
		assertTrue((Integer)res.getRows().get(0).get("cartodb_id") > 0);
	}

	@Test
	public void testClientSQLInsert() throws CartoDBException {
		CartoDBResponse<Map<String, Object>> res = cartoDBCLient.request("insert into " + TEST_TABLE + " (name) values ('test')");
		assertEquals(1, res.getTotal_rows());
	}

	@Test
	public void testIsWrite() {
		assertEquals(CartoDBClientIF.isWriteQuery("select * from bla"), false);
		assertEquals(CartoDBClientIF.isWriteQuery("updatE blabala"), true);
		assertEquals(CartoDBClientIF.isWriteQuery("deleTe from bla"), true);
		assertEquals(CartoDBClientIF.isWriteQuery("INSERT into ...from bla"), true);
	}
	
}
