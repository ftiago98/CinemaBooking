package cinema;

import java.text.NumberFormat;
import java.util.Scanner;

public class CinemaRoom {

    //Cinema Room
    Character[][] seatingArrangement;
    int rows;
    int column;
    int numberOfSeats = 1;

    //Statistics
    int numberOfSoldTickets = 0;
    float percentageSold;
    int currentIncome = 0;
    int totalIncome = 0;

    Scanner scanner = new Scanner(System.in);

    public CinemaRoom() {

        System.out.printf("Enter the number of rows:");
        int rows = scanner.nextInt();
        this.rows = rows;

        System.out.println("Enter the number of seats in each row:");
        int column = scanner.nextInt();
        this.column = column;


        this.column = column;
        this.rows = rows;
        this.numberOfSeats = rows * column;
        this.seatingArrangement = new Character[rows + 1][column];

        for (int i = 0; i < seatingArrangement.length; i++) {
            for (int j = 0; j < seatingArrangement[i].length; j++) {
                seatingArrangement[i][j] = 'S';
            }
        }

        calculateProfit();
    }

    public boolean showNavigation(){
        System.out.printf("%n1. Show the seats%n2. Buy a ticket%n3. Statistics%n0. Exit");
        int input = scanner.nextInt();
        boolean exit = false;

        switch (input){
            case 1:
                printCinemaRoom();
                break;
            case 2:
                buyTicket();
                break;
            case 3:
                showStatistics();
                break;
            case 0:
                exit = true;
                break;
            default:
                System.out.println("Invalid!");
                break;
        }
        return exit;
    }

    public void printCinemaRoom() {

        // Print first row with seat numbers
        for (int i = 0; i < column + 2; i++) {
            if (i == 0) {
                System.out.printf("%nCinema:%n ");
            } else if (i == column + 1) {
                System.out.printf("%n");
            } else {
                System.out.printf(" %d", i);
            }
        }

        //print rows
        for (int i = 0; i < seatingArrangement.length - 1; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < seatingArrangement[i].length; j++) {
                System.out.print(" " + seatingArrangement[i][j]);
            }
            System.out.printf("%n");
        }
    }

    public void buyTicket(){

        boolean seatfound = false;

        do {
            System.out.printf("%nEnter a row number:");
            int row = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            int column = scanner.nextInt();

            try {

                if (seatingArrangement.length <= row){
                    System.out.println("Wrong input!");
                    break;
                }

                if (seatingArrangement[--row][--column] == 'B') {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    if (numberOfSeats <= 60 || row < rows / 2) {
                        System.out.printf("%nTicket price: $10%n");
                        numberOfSoldTickets++;
                        currentIncome += 10;
                    } else {
                        System.out.printf("%nTicket price: $8%n");
                        numberOfSoldTickets++;
                        currentIncome += 8;
                    }
                    seatingArrangement[row][column] = 'B';
                    seatfound = true;
                }
            } catch (Exception e) {
                System.out.println("Wrong input!");
            }

        } while (seatfound != true);
    }

    public void calculateProfit(){

        int profit = 0;
        int firstHalf = 0;
        int secondHalf = 0;

        //if <= 60 = 10$ each
        //if > 60 -> first half 10$ each / second half 8$

        if (numberOfSeats <= 60) {
            totalIncome = numberOfSeats * 10;
        } else {
            int firstHalfSeats = (rows / 2) * column;
            int secondHalfSeats = (rows - (rows/2)) * column;

            totalIncome = firstHalfSeats * 10 + secondHalfSeats * 8;
        }
    }

    public void showStatistics() {

        float numberofSeatsFloat = numberOfSeats;
        float numberOfSoldTicketsFloat = numberOfSoldTickets;

        percentageSold = (numberOfSoldTicketsFloat / numberofSeatsFloat) * 100.0f;

        System.out.printf("%nNumber of purchased tickets: " + numberOfSoldTickets + "%n");
        System.out.println("Percentage: " + String.format("%.2f", percentageSold) + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }
}

