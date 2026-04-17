package br.com.FolhaDePagamento.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    final String url = "jdbc:postgresql://localhost:5432/folha-pagamento";
    final String usuario = "postgres";
    final String senha = "postgres";
    private Connection connection;

    // métodos
    public Connection getConnection() {
        System.out.println("Conectando no banco de dados.....");
        try {
            connection = DriverManager.getConnection(url, usuario, senha);
            if (connection != null) {
                System.out.println("\nConectado com sucesso!");
            } else {
                System.out.println("Erro nos dados da conexão!");
            }
        } catch (SQLException e) {
            System.err.println("\nNão foi possível conectar...");
        }
        return connection;
    }
}
