package gr.aueb.cf.ex3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class JokerApp {

    public static void main(String[] args) {
        try {
            fileInOut();
        } catch (FileNotFoundException e) {
            System.out.println("Σφάλμα: Δεν βρέθηκε το αρχείο!");
        }
    }

    public static void fileInOut() throws FileNotFoundException {

        File inFile = new File("c:/tmp/numbers.txt");
        File outFile = new File("c:/tmp/Joker_numbers.txt");

        Scanner in = new Scanner(inFile);

        PrintStream ps = new PrintStream(outFile);

        ArrayList<Integer> numbers = new ArrayList<>();

        while (in.hasNextInt()) {
            numbers.add(in.nextInt());
        }

        ps.println("The Joker numbers are:");

        final int N = 6;
        int[] row = new int[N];

        if (numbers.size() < N) {
            System.out.println("Not enough numbers in the file.");
            return;
        }

        for (int i = 0; i < numbers.size() - N + 1; i++) {
            for (int j = i + 1; j < numbers.size() - N + 2; j++) {
                for (int k = j + 1; k < numbers.size() - N + 3; k++) {
                    for (int l = k + 1; l < numbers.size() - N + 4; l++) {
                        for (int m = l + 1; m < numbers.size() - N + 5; m++) {
                            for (int p = m + 1; p < numbers.size(); p++) {

                                row[0] = numbers.get(i);
                                row[1] = numbers.get(j);
                                row[2] = numbers.get(k);
                                row[3] = numbers.get(l);
                                row[4] = numbers.get(m);
                                row[5] = numbers.get(p);

                                ps.println(Arrays.toString(row));

                                boolean isEven = false;
                                boolean isOdd = false;
                                boolean sameEnding = false;
                                boolean consecutive = false;

                                if (!isEven(row, 5) && !isOdd(row, 5) && !sameEnding(row, 3) && !consecutive(row)) {
                                    ps.println("1.More tha four evens: " + isEven + "\n" +
                                            "2.More than four odds: " + !isOdd + "\n" +
                                            "3.More than three with the same ending: " + !sameEnding + "\n" +
                                            "4.More than three numbers: " + consecutive);
                                }

                            }
                        }
                    }
                }
            }
        }
        in.close();
        ps.close();

    }

    public static boolean isEven(int[] arr, int threshold) {
        long evenCount;

        evenCount = Arrays.stream(arr).filter(num -> num % 2 == 0).count();

        return evenCount >= threshold;
    }

    public static boolean isOdd(int[] arr, int threshold) {
        long oddCount = Arrays.stream(arr).filter(num -> num % 2 != 0).count();

        return oddCount >= threshold;
    }

    public static boolean sameEnding(int[] arr, int threshold) {
        int[] endings = Arrays.stream(arr).map(num -> num % 10).toArray();
        long count = Arrays.stream(endings).distinct().count();
        return count <= threshold;
    }

    public static boolean consecutive(int[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] + 1 != arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}

