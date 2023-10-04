package com.example.spring.config;

public class NoteNotFoundException extends RuntimeException{
	
	public NoteNotFoundException(String message) {
		super(message);
	}

}
