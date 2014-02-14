package com.alibaba.domain;

import java.io.FileInputStream;
import java.io.Serializable;

public class FileResponseObject implements Serializable{
	
	private String order;
	
	private MyFileInputStream mfis;

	public MyFileInputStream getMfis() {
		return mfis;
	}

	public void setMfis(MyFileInputStream mfis) {
		this.mfis = mfis;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}


	public FileResponseObject(String order, MyFileInputStream mfis) {
		super();
		this.order = order;
		this.mfis = mfis;
	}
	

}
