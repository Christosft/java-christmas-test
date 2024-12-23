package gr.aueb.cf.ex5;


import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class TheaterApp {

    public static boolean[][] seats = new boolean[30][12];

    public static void main(String[] args) {
        theaterApp();
    }


    public static void theaterApp() {
        Scanner scanner = new Scanner(System.in);
        char column;
        int row;

        while (true) {
            System.out.println("Theater reservations menu: ");
            System.out.println("1. Reservation");
            System.out.println("2. Cansel reservation");
            System.out.println("3. Seat status");
            System.out.println("4. Print ticket");
            System.out.println("5. Exit");
            System.out.println("Please make a choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Please make a seat selection: ");
                    printSpectatorSeat();
                    System.out.println("Please enter column (A-L): ");
                    column = scanner.next().charAt(0);
                    System.out.println("Please enter row (1-30): ");
                    row = scanner.nextInt();
                    reserve(column, row);
                    break;

                case 2:
                    System.out.println("Please enter column (A-L): ");
                    column = scanner.next().charAt(0);
                    System.out.println("Please enter row (1-30): ");
                    row = scanner.nextInt();
                    cancelReserve(column, row);
                    break;


                case 3:
                    printSpectatorSeat();
                    break;

                case 4:
                    System.out.println("Ticket printing.");
                    printTicket();
                    break;

                case 5:
                    System.out.println("Exit menu.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Wrong entry. \n" +
                            "Please make a selection.");
            }

        }
    }

    public static void reserve(char column, int row) {
        int col = column - 'A';
        int r = row - 1;

        System.out.println(Arrays.deepToString(seats));
        if (r < 0 || r > 30 || col < 0 || col > 12) {
            System.out.println("Wrong seat input. \n" +
                    "Please give a valid choice");
        } else if (!seats[r][col]) {
            seats[r][col] = true;
            System.out.println("The seat " + column + row + " is successfully reserved! \n" +
                    "Your ticket will be available after the payment is confirmed.");

        } else {
            System.out.println("The seat " + column + row + " is already reserved");
        }
    }

    public static void cancelReserve(char column, int row) {
        int col = column - 'A';
        int r = row - 1;

        if (r < 0 || r >= 30 || col < 0 || col >= 12) {
            System.out.println("Wrong seat input. \n" +
                    "Please give a valid choice");
        } else if (seats[r][col]) {
            seats[r][col] = false;
            System.out.println("The seat " + column + row + " is successfully canceled!\n" +
                    "The ticket money will be refunded in a period of three days.");
        } else {
            System.out.println("The seat " + column + row + " is not reserved!");
        }
    }

    public static void printSpectatorSeat() {
        System.out.println("Theater seat status: ");

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 12; j++) {
                if (seats[i][j]) {
                    System.out.print("[X] ");
                } else {
                    System.out.print("[ ] ");
                }

            }
            System.out.println();
        }
    }

    public static void printTicket() {

        File outTicket = new File("c:/tmp/printTicket.txt");

        try (PrintStream ticket = new PrintStream(outTicket, StandardCharsets.UTF_8)) {
            Scanner in = new Scanner(System.in);
            System.out.println("Please enter your firstname:  ");
            String firstname = in.nextLine();
            System.out.println("Please enter your lastname:  ");
            String lastname = in.nextLine();
            System.out.println("Please enter your address: ");
            String streetAddress = in.nextLine();
            System.out.println("Please enter your phone number: ");
            String phoneNumber = in.nextLine();
            System.out.println("Please enter your seat number:  ");
            String seat = in.nextLine();

            ticket.println("Firstname: " + firstname +"\nLastname: " + lastname + "\nStreet Address: " + streetAddress + "\nPhone number: " + phoneNumber + "\nSeat: " + seat );
            ticket.println();
            ticket.println("""
                    Thank you for choosing our theater. Please don not loose your ticket\s
                    (If that happens please attend to the information desk\s
                    Refunds will not be available two days before the play\s
                    THANK YOU AND ENJOY THE SHOW!""");
            System.out.println("Your ticket is ready for printing.");
                } catch (IOException e) {
            System.out.println(e.getMessage());

            }
        }
    }