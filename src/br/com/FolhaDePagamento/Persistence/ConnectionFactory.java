package br.com.FolhaDePagamento.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    final String url = "jdbc:postgresql://localhost:5432/folhapagamento";
    final String usuario = "postgres";
    final String senha = "postgres";
    private Connection connection;
    private static boolean verbose = false;

    public static void setVerbose(boolean v) {
        verbose = v;
    }

    // métodos
    public Connection getConnection() {
        if (verbose) System.out.println("Conectando no banco de dados.....");
        try {
            connection = DriverManager.getConnection(url, usuario, senha);
            if (connection != null) {
                if (verbose) System.out.println("\nConectado com sucesso ao banco de dados.....!");
            } else {
                if (verbose) System.out.println("Erro nos dados da conexão!");
            }
        } catch (SQLException e) {
            System.err.println("\nNão foi possível conectar...");
        }
        return connection;
    }
}
