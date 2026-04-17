package br.com.FolhaDePagamento;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String opcaoDeSaida = "";

        do{

            System.out.println("Deseja continuar(S/N):");
            opcaoDeSaida = sc.nextLine();
        }
        while(!opcaoDeSaida.equalsIgnoreCase("S"));

        sc.close();
        System.out.println("====================");
        System.out.println("Programa finalizado com sucesso.");
        System.out.println("====================");
    }
}