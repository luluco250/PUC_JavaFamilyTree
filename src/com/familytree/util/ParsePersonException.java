package com.familytree.util;

public class ParsePersonException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ParsePersonException(String msg) {
		super(msg);
	}
	
	public ParsePersonException(Throwable err) {
		super(err);
	}
	
	public ParsePersonException(String msg, Throwable err) {
		super(msg, err);
	}
}
