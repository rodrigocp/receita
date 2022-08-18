package br.com.rcp.receitafederal.domain.service;

import br.com.rcp.receitafederal.domain.model.Account;

import java.util.List;

public interface FileWriterService {
    void write(String file, List<Account> accounts);
}
