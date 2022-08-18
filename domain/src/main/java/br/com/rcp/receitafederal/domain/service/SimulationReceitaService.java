package br.com.rcp.receitafederal.domain.service;

/**
 * Simulação do serviço da receita federal
 * Código fornecido com teste
 */
public interface SimulationReceitaService {
    /**
     * @return true se processado
     * @throws InterruptedException Serviço interrompido
     * @throws RuntimeException Exceção no serviço (500)
     */
    boolean atualizarConta(String agencia, String conta, double saldo, String status) throws RuntimeException, InterruptedException;
}
