package br.com.felipeborges.picpay_desafio_backend.exception;

public class UnauthorizedTransactionException extends RuntimeException{

    public UnauthorizedTransactionException(String message){
        super(message);
    }
}
