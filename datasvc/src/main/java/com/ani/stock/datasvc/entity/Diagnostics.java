package com.ani.stock.datasvc.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Diagnostics {
	
	private String publiclyCallable;

	public String getPubliclyCallable() {
		return publiclyCallable;
	}

	public void setPubliclyCallable(String publiclyCallable) {
		this.publiclyCallable = publiclyCallable;
	}

}
