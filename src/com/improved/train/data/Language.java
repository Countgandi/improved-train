package com.improved.train.data;

public class Language {

	private String index;
	private String name;
	private String type;
	private String script;
	private String url;

	public Language(String index, String name, String type, String script, String url) {
		this.index = index;
		this.name = name;
		this.type = type;
		this.script = script;
		this.url = url;
	}

	public String getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getScript() {
		return script;
	}

	public String getUrl() {
		return url;
	}
	
}
