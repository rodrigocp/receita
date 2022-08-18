package br.com.rcp.receitafederal.service;

import br.com.rcp.receitafederal.domain.model.Account;
import br.com.rcp.receitafederal.domain.service.AccountTransformerService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DefaultAccountTransformerServiceTest {
    @InjectMocks
    private AccountTransformerService service = mock(DefaultAccountTransformerService.class);

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenCallAccountTransformerServiceExpectListOfAccounts() {
        when(service.transform(getData())).thenCallRealMethod();
        assertEquals(5, service.transform(getData()).size());
        verify(service, times(1)).transform(anyString());
    }

    private String getData() {
        return  "agencia;conta;saldo;status\n" +
                "0101;12225-6;100,00;A\n" +
                "0101;12226-8;3200,50;A\n" +
                "3202;40011-1;-35,12;I\n" +
                "3202;54001-2;0,00;P\n" +
                "3202;00321-2;34500,00;B";
    }
}
