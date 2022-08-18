package br.com.rcp.receitafederal.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Account {
    private String agencia;
    private String conta;
    private double saldo;
    private String status;
    private String retornado;

    @Override
    public String toString() {
        return  agencia + ";" +
                conta + ";" +
                Double.toString(saldo).replace(".", ",") + ";" +
                status + ";" +
                retornado;
    }
}
