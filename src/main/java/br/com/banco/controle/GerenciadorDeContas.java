//Ricardo Lima 2565510
//Geisily Varga 2478404;
//Raí I. Morato 2623420;

package br.com.banco.controle;
import java.util.List;

import br.com.banco.modelo.ContaBancaria;

/**
 * SRP: Gerencia operações de conta (adicionar, buscar, sacar, depositar, transferir, excluir)
 * OCP: Pode ser estendida para novas operações sem alterar métodos existentes
 */
public class GerenciadorDeContas {

    // SRP: Cada método tem uma responsabilidade clara sobre operações de conta
    // OCP: Novos métodos podem ser adicionados sem modificar os existentes
    // LSP: Métodos usam ContaBancaria e suas subclasses de forma polimórfica
    public static boolean adicionarConta(ContaBancaria conta) {
        return BancoDeDados.adicionarConta(conta);
    }

    public static ContaBancaria buscarConta(String numeroConta, String agencia) {
        return BancoDeDados.buscarConta(numeroConta, agencia);
    }
    
    public static ContaBancaria buscarContaPorNumero(String numeroConta) {
        return BancoDeDados.buscarContaPorNumero(numeroConta);
    }

    public static boolean depositar(String numeroConta, String agencia, double valor) {
        ContaBancaria conta = buscarConta(numeroConta, agencia);
        if (conta != null && valor > 0) {
            double novoSaldo = conta.getSaldo() + valor;
            boolean sucesso = BancoDeDados.atualizarSaldo(numeroConta, agencia, novoSaldo);
            if (sucesso) {
                conta.depositar(valor); // Atualiza o objeto em memória também
            }
            return sucesso;
        }
        return false;
    }
    
    public static boolean sacar(String numeroConta, String agencia, double valor) {
        ContaBancaria conta = buscarConta(numeroConta, agencia);
        
        if (conta == null) {
            return false;
        }

        if (conta.getSaldo() >= valor && valor > 0) {
            double novoSaldo = conta.getSaldo() - valor;
            boolean sucesso = BancoDeDados.atualizarSaldo(numeroConta, agencia, novoSaldo);
            if (sucesso) {
                conta.sacar(valor); // Atualiza o objeto em memória também
            }
            return sucesso;
        }
        return false;
    }
    
    public static boolean temSaldoSuficiente(String numeroConta, String agencia, double valor) {
        ContaBancaria conta = buscarConta(numeroConta, agencia);
        return conta != null && conta.getSaldo() >= valor;
    }
    
    public static boolean transferirPix(String numeroContaOrigem, String numeroContaDestino, double valor) {
        ContaBancaria origem = buscarContaPorNumero(numeroContaOrigem);
        ContaBancaria destino = buscarContaPorNumero(numeroContaDestino);

        if (origem == null || destino == null) {
            return false;
        }

        if (valor <= 0 || origem.getSaldo() < valor) {
            return false;
        }

        // Atualizar saldo da conta origem
        double novoSaldoOrigem = origem.getSaldo() - valor;
        boolean sucessoOrigem = BancoDeDados.atualizarSaldoPorNumero(numeroContaOrigem, novoSaldoOrigem);
        
        if (!sucessoOrigem) {
            return false;
        }

        // Atualizar saldo da conta destino
        double novoSaldoDestino = destino.getSaldo() + valor;
        boolean sucessoDestino = BancoDeDados.atualizarSaldoPorNumero(numeroContaDestino, novoSaldoDestino);
        
        if (sucessoDestino) {
            // Atualizar objetos em memória
            origem.sacar(valor);
            destino.depositar(valor);
            return true;
        } else {
            // Se falhou ao atualizar destino, reverter origem
            BancoDeDados.atualizarSaldoPorNumero(numeroContaOrigem, origem.getSaldo() + valor);
            return false;
        }
    }

    public static List<ContaBancaria> listarContas() {
        return BancoDeDados.listarTodasContas();
    }
    
    public static boolean excluirConta(String numeroConta, String agencia) {
        // Verificar se a conta existe antes de tentar excluir
        ContaBancaria conta = buscarConta(numeroConta, agencia);
        if (conta == null) {
            return false;
        }
        
        // Verificar se a conta tem saldo zero antes de permitir exclusão
        if (conta.getSaldo() > 0) {
            return false;
        }
        
        return BancoDeDados.excluirConta(numeroConta, agencia);
    }

    public static boolean atualizarTipoConta(String numeroConta, String agencia, String novoTipo) {
        return BancoDeDados.atualizarTipoConta(numeroConta, agencia, novoTipo);
    }
}