//Ricardo Lima 2565510
//Geisily Varga 2478404;
//Raí I. Morato 2623420;

package br.com.banco.modelo;

import br.com.banco.modelo.ContaBancaria;

public class ContaBancaria{
    private String numeroConta;
    private String agencia;
    private String titular;
    private double saldo;
    private String tipoConta;

    public ContaBancaria(String numeroConta, String agencia, String titular, String tipoConta, double saldoInicial) {
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.titular = titular;
        this.tipoConta = tipoConta;
        this.saldo = saldoInicial;
    }
    
    public String getNumeroConta() {
        return numeroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }
    
    public String getTipoConta() {
        return tipoConta;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
        }
    }
    
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= saldo) { 
            this.saldo -= valor; 
            return true; 
        }
        return false; 
    }
    
    // Método para definir saldo diretamente (útil para sincronização com banco)
    public void setSaldo(double novoSaldo) {
        if (novoSaldo >= 0) {
            this.saldo = novoSaldo;
        }
    }
    
    @Override
    public String toString() {
        return "Conta{" +
                "Número='" + numeroConta + '\'' +
                ", Agência='" + agencia + '\'' +
                ", Titular='" + titular + '\'' +
                ", Tipo='" + tipoConta + '\'' +
                ", Saldo='" + String.format("%.2f", saldo) + '\'' +
                '}';
    }
}
