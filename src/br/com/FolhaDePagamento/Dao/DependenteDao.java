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
            System.err.println("Não foi possível inserir o dependente no banco de dados");
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
}
