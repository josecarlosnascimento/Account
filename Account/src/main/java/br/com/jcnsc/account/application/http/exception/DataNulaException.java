package br.com.jcnsc.account.application.http.exception;

public class DataNulaException extends RuntimeException{

    public DataNulaException() {
    }

    public DataNulaException(String message) {
        super(message);
    }
}