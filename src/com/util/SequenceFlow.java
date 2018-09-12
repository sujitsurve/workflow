package com.util;


public class SequenceFlow {

	String id;
	String sourceRef;
	String targetRef;
	
	
	public SequenceFlow(String id, String sourceRef, String targetRef) {
		super();
		this.id = id;
		this.sourceRef = sourceRef;
		this.targetRef = targetRef;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getSourceRef() {
		return sourceRef;
	}


	public void setSourceRef(String sourceRef) {
		this.sourceRef = sourceRef;
	}


	public String getTargetRef() {
		return targetRef;
	}


	public void setTargetRef(String targetRef) {
		this.targetRef = targetRef;
	}
	
	
	
	
}
