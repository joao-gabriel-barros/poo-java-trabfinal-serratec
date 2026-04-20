package br.com.FolhaDePagamento.Services.SalarioLiquidoFinal;

public class CalculoSalarioLiquidoService {
	
	public static double salarioLiquido(double salarioBruto, double valorINSS, double irrf) {
		return salarioBruto - valorINSS - irrf;
	}
}
