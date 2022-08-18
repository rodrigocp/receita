package br.com.rcp.receitafederal.domain.service;

/**
 * Interface validadora de formato de arquivo.
 */
public interface FileValidatorService {
    /**
     * Verifica se arquivo está no formato correto.
     * @param data arquivo a ser validado.
     */
    boolean isValidFormat(String data);
}
