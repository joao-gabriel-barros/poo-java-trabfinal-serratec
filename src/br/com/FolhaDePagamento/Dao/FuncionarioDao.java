package br.com.FolhaDePagamento.Dao;

import br.com.FolhaDePagamento.Exceptions.CpfInvalidoException;
import br.com.FolhaDePagamento.Model.Departamento;
import br.com.FolhaDePagamento.Model.Funcionario;
import br.com.FolhaDePagamento.Persistence.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {

    public Connection getConnection() {
        return new ConnectionFactory().getConnection();
    }

    public void inserir(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (cpf,nome,nascimento,salario_bruto,id_departamento) VALUES (?,?,?,?,?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getCpf());
            stmt.setString(2, funcionario.getNome());
            stmt.setDate(3, java.sql.Date.valueOf(funcionario.getNascimento()));
            stmt.setDouble(4, funcionario.getSalarioBruto());
            stmt.setInt(5, funcionario.getDepartamento().getId());
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("Não foi possível inserir o funcionário no banco de dados");
        }
    }

    public List<Funcionario> listar() {
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> funcionarios = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idDpt = rs.getInt("id_departamento");
                DepartamentoDao dptDao = new DepartamentoDao();
                Departamento departamento = dptDao.buscarId(idDpt);

                Funcionario funcionario = new Funcionario(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getDate("nascimento").toLocalDate(),
                        rs.getDouble("salario_bruto"),
                        departamento.getId());
                funcionarios.add(funcionario);
            }
        } catch (SQLException | CpfInvalidoException e) {
            System.err.println("Não foi possível recuperar os funcionários do banco");
        }
        return funcionarios;
    }
}
