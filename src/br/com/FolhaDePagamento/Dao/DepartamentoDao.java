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
    private Connection connection;

    public DepartamentoDao() {
        connection = new ConnectionFactory().getConnection();
    }

    public List<Departamento> listar (){
        String sql = "SELECT * FROM departamento";
        List<Departamento> departamentos = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Departamento departamento = new Departamento(rs.getInt("id"),rs.getString("nome"));
                departamentos.add(departamento);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao acessar departamentos");
            e.printStackTrace();
        }
        return departamentos;
    }
}
