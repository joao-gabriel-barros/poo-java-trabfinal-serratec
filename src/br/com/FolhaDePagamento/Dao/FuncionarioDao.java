package br.com.FolhaDePagamento.Dao;

import br.com.FolhaDePagamento.Dto.FuncionarioDepartamentoDTO;
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

    public void inserirLista(List<Funcionario> funcionarios) {
        for (Funcionario func : funcionarios) {
            inserir(func);
        }
    }

    public void inserir(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (cpf,nome,nascimento,salario_bruto,id_departamento) VALUES (?,?,?,?,?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getCpf());
            stmt.setString(2, funcionario.getNome());
            stmt.setDate(3, java.sql.Date.valueOf(funcionario.getNascimento()));
            stmt.setDouble(4, funcionario.getSalarioBruto());
            stmt.setInt(5, funcionario.getIdDepartamento());
            stmt.execute();
        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate key") ||
                    e.getSQLState().equals("23505")) {
                System.err.println("Erro: Este CPF já está cadastrado no sistema!");
                System.err.println("Não é possível inserir um funcionário com CPF duplicado.");
            } else {
                System.err.println("Erro ao inserir funcionário: " + e.getMessage());
            }
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

    public List<FuncionarioDepartamentoDTO> exibirFuncionariosPorDepartamento() {
        List<FuncionarioDepartamentoDTO> listaFuncionariosPorDepartamento = new ArrayList<>();

        String sql = "SELECT f.nome as func_nome, d.nome as dept_nome " +
                "FROM funcionario f " +
                "INNER JOIN departamento d " +
                "ON f.id_departamento = d.id " +
                "ORDER BY d.nome, f.nome";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String dept_nome = rs.getString("dept_nome");
                String func_nome = rs.getString("func_nome");

                listaFuncionariosPorDepartamento.add(new FuncionarioDepartamentoDTO(func_nome, dept_nome));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar funcionários por departamento: " + e.getMessage());
        }

        return listaFuncionariosPorDepartamento;
    }

    public boolean existePorCpf(String cpf) {
        String sql = "SELECT 1 FROM funcionario WHERE cpf = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar funcionário: " + e.getMessage());
        }
        return false;
    }
}
