package br.com.rcp.receitafederal.service;

import br.com.rcp.receitafederal.domain.model.Account;
import br.com.rcp.receitafederal.domain.service.*;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultFileProcessorService implements FileProcessorService {
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";

    @Setter(onMethod = @__({@Autowired}))
    private FileReaderService fileReaderService;

    @Setter(onMethod = @__({@Autowired}))
    private FileValidatorService fileValidatorService;

    @Setter(onMethod = @__({@Autowired}))
    private AccountTransformerService accountTransformerService;

    @Setter(onMethod = @__({@Autowired}))
    private SimulationReceitaService receitaService;

    @Override
    public List<Account> process(String file) {
        return fileReaderService.read(file).map(data -> {
            if (fileValidatorService.isValidFormat(data)) {
                return accountTransformerService.transform(data).stream().peek(conta -> {
                    try {
                        if (receitaService.atualizarConta(conta.getAgencia(), conta.getConta().replaceAll("-", ""), conta.getSaldo(), conta.getStatus())) {
                            conta.setRetornado(SUCCESS);
                        } else {
                            conta.setRetornado(FAILURE);
                        }
                    } catch (RuntimeException | InterruptedException e) {
                        e.printStackTrace();
                        conta.setRetornado(FAILURE);
                    }
                }).collect(Collectors.toList());
            }
            return new ArrayList<Account>();
        }).orElse(new ArrayList<>());
    }
}
