package br.com.rcp.receitafederal.service;

import br.com.rcp.receitafederal.domain.model.Account;
import br.com.rcp.receitafederal.domain.service.AccountTransformerService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultAccountTransformerService implements AccountTransformerService {
    private static final String COLUMN_SEPARATOR = ";";
    private static final String LINE_SEPARATOR = "\\r?\\n";

    @Override
    public List<Account> transform(String input) {
        return Arrays.stream(input.split(LINE_SEPARATOR))
                .skip(1)
                .map(line -> line.split(COLUMN_SEPARATOR))
                .map(columns -> Account.builder()
                        .agencia(columns[0])
                        .conta(columns[1])
                        .saldo(Double.parseDouble(columns[2].replaceAll(",", ".")))
                        .status(columns[3])
                        .build()
                ).collect(Collectors.toList());
    }
}
