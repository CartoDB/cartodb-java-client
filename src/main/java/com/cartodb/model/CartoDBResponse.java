package com.cartodb.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents a typical CartoDB response object.
 * @author canadensys
 *
 * @param <T> Type of the object represented by the sent query.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartoDBResponse<T> {
	
	private double time;
	private int total_rows;
	private List<T> rows;
	/*
	private String fields;
	
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	*/
	
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
