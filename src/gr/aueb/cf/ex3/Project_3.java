package gr.aueb.cf.ex3;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Project_3 {

    public static int[][] count = new int[128][2];


    public static void main(String[] args) {
        try {
            lettersNumbersArray();
            printResults();
        } catch (FileNotFoundException e) {
            System.err.println("Error. The file in not found");
        }
    }
    public static void lettersNumbersArray () throws FileNotFoundException {
        try (BufferedReader bf = new BufferedReader(new FileReader("c:/tmp/project_3.txt"))) {
            int a;
            while ((a = bf.read()) != -1) {
                if (Character.isWhitespace(a)) {
                    continue;
                }
                count[a][0]++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        public static void printResults() {
            try (PrintStream results = new PrintStream(new PrintStream("c:/tmp/project_3_results.txt"))) {
                results.println("The results of the program. \n" +
                        "Are:");
                for (int i = 0; i < 128; i++) {
                    if (count[i][0] > 0) {
                        System.out.println("The letter [" + (char) i + "] = Is repeating [" + count[i][0] + "] times!");
                        results.println("The letter [" + (char) i + "] = Is repeating [" + count[i][0] + "] times!");
                    }
                }

            } catch (FileNotFoundException e) {
                System.err.println("The is not found or cannot be created.");
            }
        }
    }





