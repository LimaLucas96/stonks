package br.com.stonks.stonks.services;

import java.io.IOException;

import br.com.stonks.stonks.exception.EmailInvalidoException;

public interface EmailService {

    public String envairEmail(String email) throws EmailInvalidoException ;

}
