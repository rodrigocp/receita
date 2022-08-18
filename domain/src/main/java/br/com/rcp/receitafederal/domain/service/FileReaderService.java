package br.com.rcp.receitafederal.domain.service;

import java.util.Optional;

/**
 * Leitor de arquivo.
 * Lê o arquivo no formato a ser especificado na implementação
 * do método read()
 */
public interface FileReaderService {
    /**
     * Lê arquivo informado.
     * @param name nome do arquivo a ser lido
     * @return string representando conteúdo do arquivo lido
     */
    Optional<String> read(String name);
}
