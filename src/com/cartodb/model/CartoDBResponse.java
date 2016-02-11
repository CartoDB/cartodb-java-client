package com.cartodb.model;

import java.util.List;
import java.util.Map;

/**
 * Represents a typical CartoDB response object.
 * @author canadensys
 *
 * @param <T> Type of the object represented by the sent query.
 */
public class CartoDBResponse<T> {
	
	private double time;
	private int total_rows;
	private Map<String, Map<String, String>> fields;
	private List<T> rows;
    private List<String> notices;
	
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
	public Map<String, Map<String, String>> getFields() {
		return fields;
	}
	public void setFields(Map<String, Map<String, String>> fields) {
		this.fields = fields;
	}

    public List<String> getNotices() {
        return notices;
    }

    public void setNotices(List<String> notices) {
        this.notices = notices;
    }
}
