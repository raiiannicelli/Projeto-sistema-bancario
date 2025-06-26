//Ricardo Lima 2565510
//Geisily Varga 2478404;
//Raí I. Morato 2623420;

package br.com.banco.controle;

import br.com.banco.modelo.ContaBancaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * SRP: Gerencia apenas o acesso ao banco de dados (CRUD)
 * OCP: Pode ser estendida para novos métodos de acesso sem alterar os existentes
 */
public class BancoDeDados {
    private static final String URL = "jdbc:postgresql://localhost:5432/crudbank";
    private static final String USER = "localhost";
    private static final String PASSWORD = "bank-inter";
    private static final String driverJDBC = "org.postgresql.Driver";
    
    static {
        try {
            Class.forName(driverJDBC);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado: " + e.getMessage());
        }
        inicializarBanco();
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    private static void inicializarBanco() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Criar tabela de contas
            String sqlCriarTabela =
                "CREATE TABLE IF NOT EXISTS contas (" +
                "id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                "numero_conta VARCHAR(20) NOT NULL," +
                "agencia VARCHAR(10) NOT NULL," +
                "titular VARCHAR(100) NOT NULL," +
                "tipo_conta VARCHAR(20) NOT NULL," +
                "saldo DOUBLE NOT NULL DEFAULT 0.0," +
                "UNIQUE(numero_conta, agencia)" +
                ")";
            
            stmt.execute(sqlCriarTabela);
            System.out.println("Banco de dados inicializado com sucesso!");
            
        } catch (SQLException e) {
            System.err.println("Erro ao inicializar banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static boolean adicionarConta(ContaBancaria conta) {
        String sql = "INSERT INTO contas (numero_conta, agencia, titular, tipo_conta, saldo) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, conta.getNumeroConta());
            pstmt.setString(2, conta.getAgencia());
            pstmt.setString(3, conta.getTitular());
            pstmt.setString(4, conta.getTipoConta());
            pstmt.setDouble(5, conta.getSaldo());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar conta: " + e.getMessage());
            return false;
        }
    }
    
    public static ContaBancaria buscarConta(String numeroConta, String agencia) {
        String sql = "SELECT * FROM contas WHERE numero_conta = ? AND agencia = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, numeroConta);
            pstmt.setString(2, agencia);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return criarContaDoResultSet(rs);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar conta: " + e.getMessage());
        }
        
        return null;
    }
    
    public static ContaBancaria buscarContaPorNumero(String numeroConta) {
        String sql = "SELECT * FROM contas WHERE numero_conta = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, numeroConta);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return criarContaDoResultSet(rs);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar conta por número: " + e.getMessage());
        }
        
        return null;
    }
    
    public static boolean atualizarSaldo(String numeroConta, String agencia, double novoSaldo) {
        String sql = "UPDATE contas SET saldo = ? WHERE numero_conta = ? AND agencia = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, novoSaldo);
            pstmt.setString(2, numeroConta);
            pstmt.setString(3, agencia);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar saldo: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean atualizarSaldoPorNumero(String numeroConta, double novoSaldo) {
        String sql = "UPDATE contas SET saldo = ? WHERE numero_conta = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, novoSaldo);
            pstmt.setString(2, numeroConta);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar saldo por número: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean atualizarTipoConta(String numeroConta, String agencia, String novoTipo) {
        String sql = "UPDATE contas SET tipo_conta = ? WHERE numero_conta = ? AND agencia = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, novoTipo);
            pstmt.setString(2, numeroConta);
            pstmt.setString(3, agencia);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar tipo de conta: " + e.getMessage());
            return false;
        }
    }
    
    public static List<ContaBancaria> listarTodasContas() {
        List<ContaBancaria> contas = new ArrayList<>();
        String sql = "SELECT * FROM contas ORDER BY numero_conta";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                contas.add(criarContaDoResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar contas: " + e.getMessage());
        }
        
        return contas;
    }
    
    public static boolean excluirConta(String numeroConta, String agencia) {
        String sql = "DELETE FROM contas WHERE numero_conta = ? AND agencia = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, numeroConta);
            pstmt.setString(2, agencia);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir conta: " + e.getMessage());
            return false;
        }
    }
    
    private static ContaBancaria criarContaDoResultSet(ResultSet rs) throws SQLException {
        String numeroConta = rs.getString("numero_conta");
        String agencia = rs.getString("agencia");
        String titular = rs.getString("titular");
        String tipoConta = rs.getString("tipo_conta");
        double saldo = rs.getDouble("saldo");
        
        ContaBancaria conta = new ContaBancaria(numeroConta, agencia, titular, tipoConta, saldo);
        return conta;
    }
} 