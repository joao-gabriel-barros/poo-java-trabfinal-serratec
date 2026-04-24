package br.com.FolhaDePagamento.Dao;

import br.com.FolhaDePagamento.Enum.Parentesco;
import br.com.FolhaDePagamento.Exceptions.CpfInvalidoException;
import br.com.FolhaDePagamento.Model.Dependente;
import br.com.FolhaDePagamento.Model.Funcionario;
import br.com.FolhaDePagamento.Persistence.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DependenteDao {
    public Connection getConnection() {
        return new ConnectionFactory().getConnection();
    }

    public void inserirLista(List<Dependente> dependentes) {
        for (Dependente dep : dependentes) {
            inserir(dep);
        }
    }

    public void inserir(Dependente dependente) {
        String sql = "INSERT INTO dependente (cpf,nome,nascimento,parentesco,cpf_funcionario) VALUES (?,?,?,?,?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dependente.getCpf());
            stmt.setString(2, dependente.getNome());
            stmt.setDate(3, java.sql.Date.valueOf(dependente.getNascimento()));
            stmt.setString(4, dependente.getParentesco().name().toUpperCase());
            stmt.setString(5, dependente.getCpfFuncionario());
            stmt.execute();
        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate key") ||
                    e.getSQLState().equals("23505")) {
                System.err.println("Erro: Este CPF já está cadastrado no sistema!");
                System.err.println("Não é possível inserir um dependente com CPF duplicado.");
            } else {
                System.err.println("Erro ao inserir dependente: " + e.getMessage());
            }
        }
    }

    public List<Dependente> listar() {
        String sql = "SELECT * FROM dependente";
        List<Dependente> dependentes = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Dependente dependente = new Dependente(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getDate("nascimento").toLocalDate(),
                        Parentesco.valueOf(rs.getString("parentesco")),
                        rs.getString("cpf_funcionario")
                );
                dependentes.add(dependente);
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível recuperar os dependentes no banco");
        } catch (CpfInvalidoException e) {
            throw new RuntimeException(e);
        }
        return dependentes;
    }

    public boolean existePorCpf(String cpf) {
        String sql = "SELECT 1 FROM dependente WHERE cpf = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar dependente: " + e.getMessage());
        }
        return false;
    }
}
