package br.com.FolhaDePagamento.Services.Validators;

import br.com.FolhaDePagamento.Dao.DependenteDao;
import br.com.FolhaDePagamento.Dao.FuncionarioDao;

public class ExistsValidators {
    public static boolean verificarSeFuncionarioExiste(String cpf){
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        return funcionarioDao.existePorCpf(cpf);
    }

    public static boolean verificarSeDependenteExiste(String cpf){
        DependenteDao dependenteDao = new DependenteDao();
        return dependenteDao.existePorCpf(cpf);
    }

    public static boolean validarArquivo(String caminho) {
        java.io.File arquivo = new java.io.File(caminho);
        return arquivo.exists() && arquivo.isFile() && arquivo.canRead();
    }

    public static boolean validarCaminhoSaida(String caminho) {
        try {
            java.io.File arquivo = new java.io.File(caminho);
            java.io.File diretorio = arquivo.getParentFile();
            return diretorio != null && (diretorio.exists() || diretorio.mkdirs());
        } catch (Exception e) {
            return false;
        }
    }
}
