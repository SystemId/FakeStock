package com.ani.stock.datasvc.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Query {
	
	private Result results;
	private String count;
	private String lang;
	private String created;
	private Diagnostics diagnostics;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public Diagnostics getDiagnostics() {
		return diagnostics;
	}

	public void setDiagnostics(Diagnostics diagnostics) {
		this.diagnostics = diagnostics;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public Result getResults() {
		return results;
	}

	public void setResults(Result results) {
		this.results = results;
	}

}
