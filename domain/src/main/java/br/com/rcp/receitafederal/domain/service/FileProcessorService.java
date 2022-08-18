package br.com.rcp.receitafederal.domain.service;

import br.com.rcp.receitafederal.domain.model.Account;

import java.util.List;

public interface FileProcessorService {
    List<Account> process(String file);
}
