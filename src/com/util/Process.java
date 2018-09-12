package com.util;
public class Process {

	String id;
	String name;
	boolean isExecutable;
	
	public Process(String id, String name, boolean isExecutable) {
		super();
		this.id = id;
		this.name = name;
		this.isExecutable = isExecutable;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isExecutable() {
		return isExecutable;
	}

	public void setExecutable(boolean isExecutable) {
		this.isExecutable = isExecutable;
	}
	
	
}
