//Ricardo Lima 2565510
//Geisily Varga 2478404;
//Raí I. Morato;

package br.com.banco.modelo;

public class ContaCorrente extends ContaBancaria {
    private double limiteChequeEspecial;
    private float taxaMensal;

    public ContaCorrente(String numeroConta, String agencia, String titular, double saldoInicial, double limiteChequeEspecial, float taxaMensal) {
        super(numeroConta, agencia, titular, "Corrente", saldoInicial);
        this.limiteChequeEspecial = limiteChequeEspecial;
        this.taxaMensal = taxaMensal;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public float getTaxaMensal() {
        return taxaMensal;
    }

    public void setTaxaMensal(float taxaMensal) {
        this.taxaMensal = taxaMensal;
    }

    @Override
    public boolean sacar(double valor) {
        if (valor > 0 && (getSaldo() + limiteChequeEspecial) >= valor) {
            depositar(-valor); 
            return true;
        }
        return false;
    }

    public boolean fazerPix(double valor, ContaBancaria destino) {
        if (sacar(valor)) {
            destino.depositar(valor);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + ", Limite Cheque Especial: R$ " + limiteChequeEspecial + ", Taxa Mensal: R$ " + taxaMensal;
    }
}
