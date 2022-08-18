package br.com.rcp.receitafederal.service;

import br.com.rcp.receitafederal.domain.model.Account;
import br.com.rcp.receitafederal.domain.service.FileWriterService;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class DefaultFileWriterService implements FileWriterService {
    @Override
    public void write(String file, List<Account> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Account account : accounts) {
                writer.write(account.toString() + "\n");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
