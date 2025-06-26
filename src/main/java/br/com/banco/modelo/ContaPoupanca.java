//Ricardo Lima 2565510
//Geisily Varga 2478404;
//Raí I. Morato;

package br.com.banco.modelo;

public class ContaPoupanca extends ContaBancaria {
    private float jurosMensal;

    public ContaPoupanca(String numeroConta, String agencia, String titular, double saldoInicial, float jurosMensal) {
        super(numeroConta, agencia, titular, "Poupança", saldoInicial);
        this.jurosMensal = jurosMensal;
    }

    public float getJurosMensal() {
        return jurosMensal;
    }

    public void setJurosMensal(float jurosMensal) {
        this.jurosMensal = jurosMensal;
    }

    public void aplicarJuros() {
        depositar(getSaldo() * jurosMensal);
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
        return super.toString() + ", Juros Mensal: " + (jurosMensal * 100) + "%";
    }
}
