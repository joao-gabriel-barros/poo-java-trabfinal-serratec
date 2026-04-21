package br.com.FolhaDePagamento.Dao;

import br.com.FolhaDePagamento.Model.FolhaDePagamento;
import br.com.FolhaDePagamento.Persistence.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FolhaDePagamentoDao {
    public void inserir(FolhaDePagamento folhaDePagamento) {
        String sql = "INSERT INTO folha_pagamento (cpf_funcionario, data , inss, ir, liquido) VALUES (?,?,?,?,?)";
        try (Connection conn = new ConnectionFactory().getConnection();
        	PreparedStatement stmt = conn.prepareStatement(sql)) {

        	stmt.setString(1, folhaDePagamento.getCpf_funcionario());
            stmt.setString(2, folhaDePagamento.getData().toString());
            stmt.setDouble(3, folhaDePagamento.getInss());
            stmt.setDouble(4, folhaDePagamento.getIr());
            stmt.setDouble(5, folhaDePagamento.getLiquido());
            stmt.execute();

            System.out.println("Folha de pagamento inserida com sucesso!");

        } catch (SQLException e) {

        	System.err.println("Não foi possível inserir a folha de pagamento no banco de dados");
        }
    }

    public List<FolhaDePagamento> listar() {
        String sql = "SELECT * FROM folha_pagamento";
        List<FolhaDePagamento> lista = new ArrayList<>();

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
        		
        		while (rs.next()) {
        			FolhaDePagamento folhaDePagamento = new FolhaDePagamento(
                    rs.getString("cpf_funcionario"),
                    rs.getDate("data").toLocalDate(),
                    rs.getDouble("inss"),
                    rs.getDouble("ir"),
                    rs.getDouble("liquido"));

                lista.add(folhaDePagamento);
            }

        } catch (SQLException e) {

        	System.err.println("Não foi possível listar as folhas de pagamento.");
        }

        return lista;
    }
}