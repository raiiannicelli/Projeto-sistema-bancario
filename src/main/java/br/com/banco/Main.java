//Ricardo Lima 2565510
//Geisily Varga 2478404;
//Raí I. Morato 2623420;

package br.com.banco;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.com.banco.visao.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Configurar look and feel do sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Erro ao configurar look and feel: " + e.getMessage());
        }

        // Inicializar o banco de dados
        System.out.println("Inicializando sistema bancário...");
        
        // Iniciar a interface principal - FormEntrarConta
        SwingUtilities.invokeLater(() -> {
            FormEntrarConta formEntrar = new FormEntrarConta();
            formEntrar.setVisible(true);
        });
    }
} 