package br.com.FolhaDePagamento.Persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private DatabaseInitializer() {
    }

    public static void run(Connection connection) throws SQLException {
        String script;

        try {
            script = Files.readString(Paths.get("db", "init.sql"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new SQLException("Nao foi possivel ler db/init.sql", e);
        }

        try (Statement stmt = connection.createStatement()) {
            for (String sql : script.split(";")) {
                String command = sql.trim();
                if (!command.isEmpty()) {
                    stmt.execute(command);
                }
            }
        }
    }
}

