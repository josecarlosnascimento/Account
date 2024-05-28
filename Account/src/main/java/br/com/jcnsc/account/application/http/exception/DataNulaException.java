package br.com.jcnsc.account.application.http.exception;

public class DataNulaException extends RuntimeException{

	private static final long serialVersionUID = 2807706627674289257L;

	public DataNulaException() {
    }

    public DataNulaException(String message) {
        super(message);
    }
}