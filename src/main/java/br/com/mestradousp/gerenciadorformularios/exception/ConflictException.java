package br.com.mestradousp.gerenciadorformularios.exception;

public class ConflictException extends RuntimeException{
    public ConflictException(String message) {
        super(message);
    }
}
