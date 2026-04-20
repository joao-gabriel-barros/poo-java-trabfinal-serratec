package br.com.FolhaDePagamento.Services.SalarioLiquidoFinal;

public class CalculoSalarioFinalService {
	
	public double salarioFinal(double salarioBruto, double valorINSS, double irrf) {
		return salarioBruto - valorINSS - irrf;
	}
}
