package br.com.jcnsc.account.application.http.exception;

public class DataInvalidaException extends RuntimeException{

    public DataInvalidaException() {
    }

    public DataInvalidaException(String message) {
        super(message);
    }
}