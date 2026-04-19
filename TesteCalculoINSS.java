package FinalPOO;

public class TesteCalculoINSS {

	public static void main(String[] args) {
		        CalculoINSS calculo = new CalculoINSSTabela();
		        
		        double salario1 = 1730.00;
		        double salario2 = 3150.00;
		        double salario3 = 9000.00;
		        double salario4 = 0;
		        
		        System.out.println("Salário R$" + salario1 + " -> INSS: R$" + String.format("%.2f", calculo.calcular(salario1)));
		        System.out.println("Salário R$" + salario2 + " -> INSS: R$" + String.format("%.2f", calculo.calcular(salario2)));
		        System.out.println("Salário R$" + salario3 + " -> INSS: R$" + String.format("%.2f", calculo.calcular(salario3)));
		        System.out.println("Salário R$" + salario4 + " -> INSS: R$" + String.format("%.2f", calculo.calcular(salario4)));
		       
	}
}

	