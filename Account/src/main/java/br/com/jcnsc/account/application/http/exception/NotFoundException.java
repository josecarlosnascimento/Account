package br.com.jcnsc.account.application.http.exception;

public class NotFoundException extends RuntimeException{

	private static final long serialVersionUID = -4052120396158602981L;

	public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}