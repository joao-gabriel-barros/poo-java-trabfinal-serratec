package br.com.FolhaDePagamento;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String opcaoDeSaida = "";

        do{
            try{
                exibirMenu();

            }catch(Exception e) {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }

            System.out.println("Deseja continuar(S/N):");
            opcaoDeSaida = sc.nextLine();
        }
        while(!opcaoDeSaida.equalsIgnoreCase("S"));

        sc.close();
        System.out.println("\n\n====================");
        System.out.println("Programa finalizado com sucesso.");
        System.out.println("====================");
    }

    public static void exibirMenu(){
        System.out.println("\n\n\n===============================================");
        System.out.println("------ Sistema de Pagamentos - Serratec ------- ");
        System.out.println("===============================================");
        System.out.println("\n --- PROCESSAMENTO DE DADOS ---");
        System.out.println("\t1. Calcular Folha em Lote (Via Arquivo CSV)");
        System.out.println("\t2. Calcular Folha Avulsa (Cadastro Manual)");
        System.out.println("\n --- RELATÓRIOS DO BANCO DE DADOS ---");
        System.out.println("\t3. Listar Todos os Departamentos");
        System.out.println("\t4. Listar Funcionários por Departamento");
        System.out.println("\t5. Listar Histórico de Folhas de Pagamento");
        System.out.println("\n\t6. Sair");
        System.out.println("--------------------------------------- ");
        System.out.println("Escolha uma opção: ");
    }
}