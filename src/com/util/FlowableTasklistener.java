package com.util;

public class FlowableTasklistener {

	String event;
	String classs;
	
	
	public FlowableTasklistener(String event, String classs) {
		super();
		this.event = event;
		this.classs = classs;
	}


	public String getEvent() {
		return event;
	}


	public void setEvent(String event) {
		this.event = event;
	}


	public String getClasss() {
		return classs;
	}


	public void setClasss(String classs) {
		this.classs = classs;
	}
	
	
}
