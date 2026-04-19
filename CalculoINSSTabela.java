package FinalPOO;

public class CalculoINSSTabela implements CalculoINSS {
	double aliquota;
    double parcelaDeduzir;
    private static final double TETO_MAXIMO = 951.62;
    

    public double calcular(double salarioBruto) {    
    
    	   if (salarioBruto <= 0) {
       	    return 0;
    }

             if (salarioBruto > 8157.41) {
               return TETO_MAXIMO;
       }
        	
        if (salarioBruto <= 1518.00) {
            aliquota = 0.075; 
            parcelaDeduzir = 0.00;
        } else if (salarioBruto <= 2793.88) {
            aliquota = 0.09; 
            parcelaDeduzir = 22.77;
        } else if (salarioBruto <= 4190.83) {
            aliquota = 0.12; 
            parcelaDeduzir = 106.60;
        } else {
            aliquota = 0.14;
            parcelaDeduzir = 190.42;
        }
        
        return (salarioBruto * aliquota) - parcelaDeduzir;
    
    }
}
