package com.nearsoft.myflights.model.fs;

public class FSConnection {

	private Object request;
	private FSAppendix appendix;

	public Object getRequest() {
		return request;
	}

	public void setRequest(Object request) {
		this.request = request;
	}

	public FSAppendix getAppendix() {
		return appendix;
	}

	public void setAppendix(FSAppendix appendix) {
		this.appendix = appendix;
	}

	@Override
	public String toString() {
		return "FSConnection [request=" + request + ", appendix=" + appendix
				+ "]";
	}
}
