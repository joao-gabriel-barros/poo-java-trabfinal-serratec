package br.com.FolhaDePagamento.Dao;

import br.com.FolhaDePagamento.Model.Departamento;
import br.com.FolhaDePagamento.Persistence.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDao {

    public List<Departamento> listar() {
        String sql = "SELECT * FROM departamento";
        List<Departamento> departamentos = new ArrayList<>();
        try (Connection conn = new ConnectionFactory().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Departamento departamento = new Departamento(rs.getInt("id"), rs.getString("nome"));
                departamentos.add(departamento);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao acessar departamentos: " + e.getMessage());
        }
        return departamentos;
    }

    public Departamento buscarId(int idDpt) {
        String sql = "SELECT * FROM departamento WHERE id = ?";
        Departamento dpt = null;
        try (Connection conn = new ConnectionFactory().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDpt);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    dpt = new Departamento();
                    dpt.setId(rs.getInt("id"));
                    dpt.setNome(rs.getString("nome"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível achar o departamento pelo ID: " + e.getMessage());
        }
        return dpt;
    }
}


