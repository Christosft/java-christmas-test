package gr.aueb.cf.ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class JokerAppWithRandom {

    /**
     * Δημιουργια μια εφαρμογης τυπου Joker στην οποια διαβαζουμε απο ενα αρχειο
     * στον δισκο τυχαιους αριθμους και εμφανιζουμαι στην κονσολα και σε αρχειο αποθηκευσης
     * ολους τους δυνατους συνδυασμους.
     * Στους συνδυασμους αυτους ελεγχουμε και διαφορα αλλα στοιχεια.
     */

    public static void main(String[] args) {
        try {
            fileInOut();
        } catch (FileNotFoundException e) {
            System.out.println("Σφάλμα: Δεν βρέθηκε το αρχείο!");
        }
    }

    public static void fileInOut() throws FileNotFoundException {

        File inFile = new File("c:/tmp/numbers.txt");
        File outFile = new File("c:/tmp/Joker_numbers1.txt");

        Scanner in = new Scanner(inFile);

        PrintStream ps = new PrintStream(outFile);

        ArrayList<Integer> numbers = new ArrayList<>();

        while (in.hasNextInt()) {
            numbers.add(in.nextInt());
        }

        ps.println("Παραγόμενοι αριθμοί Joker:");

        if (numbers.size() < 6) {
            System.out.println("Δεν υπάρχουν αρκετοί αριθμοί στο αρχείο.");
            return;
        }

        Random rand = new Random();

        for (int i = 0; i < 6; i++) {
            int[] row = new int[6];

            for (int j = 0; j < 6; j++) {
                row[j] = numbers.get(rand.nextInt(numbers.size()));
            }

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

