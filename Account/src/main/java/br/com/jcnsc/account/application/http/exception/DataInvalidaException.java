package br.com.jcnsc.account.application.http.exception;

public class DataInvalidaException extends RuntimeException{

	private static final long serialVersionUID = 109043256999662012L;

	public DataInvalidaException() {
    }

    public DataInvalidaException(String message) {
        super(message);
    }
}