package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        boolean exit = false;

        int totalSeats = rows * seats;
        int totalIncome;
        int currentIncome = 0;
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            totalIncome = rows / 2 * seats * 10 + (rows - rows / 2) * seats * 8;
        }

        char[][] schemeOfCinema = new char[rows + 1][seats + 1];

        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (i == 0 && j == 0) {
                    schemeOfCinema[i][j] = ' ';
                } else if (i == 0) {
                    schemeOfCinema[i][j] = Character.forDigit(j, 10);
                } else if (j == 0) {
                    schemeOfCinema[i][j] = Character.forDigit(i, 10);
                } else {
                    schemeOfCinema[i][j] = 'S';
                }
            }
        }

        int soldTickets = 0;

        while (exit != true) {
            showMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    printSeats(schemeOfCinema);
                    break;
                case 2:
                    currentIncome += buyTicket(schemeOfCinema, totalSeats, rows, seats);
                    soldTickets++;
                    break;
                case 3:
                    showStatistics(totalSeats, soldTickets, currentIncome, totalIncome);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    break;
            }
        }

    }

    public static int buyTicket(char[][] schemeOfCinema, int totalSeats, int rows, int seats) {
        Scanner scanner = new Scanner(System.in);
        int price = 0;

        for (int i = 0; i < 1;) {
            System.out.println("Enter a row number:");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt();

            if (row != 0 && seat != 0 && row <= rows && seat <= seats) {
                if (schemeOfCinema[row][seat] == 'B') {
                    System.out.println("\nThat ticket has already been purchased!\n");
                } else {
                    schemeOfCinema[row][seat] = 'B';

                    if (totalSeats > 60 && row > rows / 2) {
                        System.out.println("\nTicket price: $8");
                        price = 8;
                    } else {
                        System.out.println("\nTicket price: $10");
                        price = 10;
                    }
                    return price;
                }
            } else {
                System.out.println("\nWrong input!\n");
            }
        }
        return price;
    }

    public static void showStatistics(int totalSeats, int soldTickets, int currentIncome, int totalIncome) {
        double pers = ((double) 100 / totalSeats * soldTickets);
        System.out.printf("\nNumber of purchased tickets: %d \n" +
                "Percentage: %.2f%% \n" +
                "Current income: $%d \n" +
                "Total income: $%d\n", soldTickets, pers, currentIncome, totalIncome);

    }

    public static void printSeats(char[][] s) {
        System.out.println();
        System.out.println("Cinema:");
        for (char[] k: s) {
            for (char m: k) {
                System.out.print(m + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void showMenu() {
        System.out.println("\n1. Show the seats \n" +
                "2. Buy a ticket \n" +
                "3. Statistics \n" +
                "0. Exit");
    }
}