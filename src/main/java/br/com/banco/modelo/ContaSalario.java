//Ricardo Lima 2565510
//Geisily Varga 2478404;
//Raí I. Morato;

package br.com.banco.modelo;

public class ContaSalario extends ContaBancaria {
    private String empresa;
    private int limiteSaques;
    private int saquesRealizados;

    public ContaSalario(String numeroConta, String agencia, String titular, double saldoInicial, String empresa, int limiteSaques) {
        super(numeroConta, agencia, titular, "Salário", saldoInicial);
        this.empresa = empresa;
        this.limiteSaques = 3;
        this.saquesRealizados = 0;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getLimiteSaques() {
        return limiteSaques;
    }

    public void setLimiteSaques(int limiteSaques) {
        this.limiteSaques = limiteSaques;
    }

    @Override
    public boolean sacar(double valor) {
        if (saquesRealizados >= limiteSaques) {
            return false; // Limite de saques atingido
        }
        boolean sucesso = super.sacar(valor);
        if (sucesso) {
            saquesRealizados++;
        }
        return sucesso;
    }

    public void resetarSaques() {
        saquesRealizados = 0;
    }

    @Override
    public String toString() {
        return super.toString() + ", Empresa: " + empresa + ", Saques restantes hoje: " + (limiteSaques - saquesRealizados);
    }
}
