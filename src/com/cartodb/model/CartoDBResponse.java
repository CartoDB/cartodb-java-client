package com.cartodb.model;

import java.util.List;

/**
 * Represents a typical CartoDB response object.
 * @author canadensys
 *
 * @param <T> Type of the object represented by the sent query.
 */
public class CartoDBResponse<T> {
	
	private double time;
	private int total_rows;
	private List<T> rows;
	
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public int getTotal_rows() {
		return total_rows;
	}
	public void setTotal_rows(int total_rows) {
		this.total_rows = total_rows;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
