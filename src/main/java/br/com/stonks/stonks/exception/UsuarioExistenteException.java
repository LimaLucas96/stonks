package br.com.stonks.stonks.exception;

public class UsuarioExistenteException extends Exception {
    public UsuarioExistenteException(String mensage) {
        super(mensage);
    }
}
