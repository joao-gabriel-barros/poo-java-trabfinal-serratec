package br.com.FolhaDePagamento.Dao;

import br.com.FolhaDePagamento.Exceptions.CpfInvalidoException;
import br.com.FolhaDePagamento.Model.Departamento;
import br.com.FolhaDePagamento.Model.Funcionario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Teste {
    public static void main(String[] args) {
        DepartamentoDao depDao = new DepartamentoDao();
        FuncionarioDao funcDao = new FuncionarioDao();
        try {
            List<Funcionario> funcionarios = funcDao.listar();
            for (Funcionario funcionario : funcionarios){
                System.out.println(funcionario);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
