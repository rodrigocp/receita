package br.com.rcp.receitafederal.domain.error;

/**
 * Exceção para formato de arquivo inválido
 */
public class InvalidFileFormatException extends Exception {
    public InvalidFileFormatException(String message) {
        super(message);
    }
}
