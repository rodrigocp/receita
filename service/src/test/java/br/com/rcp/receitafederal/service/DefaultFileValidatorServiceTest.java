package br.com.rcp.receitafederal.service;

import br.com.rcp.receitafederal.domain.service.FileValidatorService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
public class DefaultFileValidatorServiceTest {
    @InjectMocks
    private FileValidatorService service = mock(DefaultFileValidatorService.class);

    @Before
    public void init() {
        openMocks(this);
    }

    @Test
    public void whenCallAccountTransformerServiceExpectListOfAccounts() {
        when(service.isValidFormat(getData())).thenCallRealMethod();
        assertTrue(service.isValidFormat(getData()));
        verify(service, times(1)).isValidFormat(anyString());
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
