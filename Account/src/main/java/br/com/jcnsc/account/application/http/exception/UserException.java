package br.com.jcnsc.account.application.http.exception;

public class UserException extends RuntimeException{

	private static final long serialVersionUID = 8848564597696486073L;

	public UserException() {
    }

    public UserException(String message) {
        super(message);
    }
}