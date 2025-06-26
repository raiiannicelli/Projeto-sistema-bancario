package br.com.banco.controle;

import br.com.banco.modelo.ContaBancaria;
import java.util.List;

public interface InterfaceGerenteBD {
    boolean adicionarConta(ContaBancaria conta);
    List<ContaBancaria> buscarContas(ContaBancaria conta);
    ContaBancaria buscarContaPorNumero(String numeroConta, String agencia);
    void atualizarSaldo(String numeroConta, String agencia, double novoSaldo);
    void atualizarSaldoPorNumero(String numeroConta, double novoSaldo);
    List<ContaBancaria> listarTodasContas();
}
