package br.com.FolhaDePagamento.Services.Csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CsvFileReader {
    public static void lerArquivoCsv(String arquivo) {
        try {
            Scanner arquivoScanner = new Scanner(new File(arquivo));
            arquivoScanner.useDelimiter(";");
            while (arquivoScanner.hasNext()) {
                System.out.println(arquivoScanner.nextLine());
            }
            arquivoScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado !");
            System.out.println(e.getMessage());
        }
    }
}

