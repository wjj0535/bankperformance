package org.wangjj.bankperformance.Bean;

import java.util.ArrayList;
import java.util.List;

public class QueryResult<E> {
	private String size;
	private List<E> dataSet = new ArrayList<>();
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public List<E> getDataSet() {
		return dataSet;
	}
	public void setDataSet(List<E> dataSet) {
		this.dataSet = dataSet;
	}
	
}
