package br.com.FolhaDePagamento.Services.Csv;

public class CsvReader {
    try {
        Scanner sc = new Scanner(new File("/Aula/exercicio.csv"));
        sc.useDelimiter(";");
        while (sc.hasNext()) {
            System.out.println(sc.nextLine());
        }
        sc.close();
    } catch (FileNotFoundException e) {
        System.out.println("Arquivo não encontrado !");
        e.printStackTrace();
    }
}
