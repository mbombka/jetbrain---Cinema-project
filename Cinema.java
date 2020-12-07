package cinema;
import java.util.Objects;
import java.util.Scanner;
import java.util.Arrays;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here



         Scanner scanner = new Scanner(System.in);
         System.out.println("Enter the number of rows:");
         int numberOfRows = scanner.nextInt();
         System.out.println("Enter the number of seats in each row:");
         int numberOfSeatsInRow = scanner.nextInt();

         CinemaManager cinemaManager = new CinemaManager(numberOfRows, numberOfSeatsInRow);
        int userInput = 0;
         do {
             System.out.println();
             System.out.println("1. Show the seats");
             System.out.println("2. Buy a ticket");
             System.out.println("3. Statistics");
             System.out.println("0. Exit");
             userInput = scanner.nextInt();
             System.out.println();
             switch (userInput) {
                 case 1:
                     cinemaManager.showSeats();
                     break;
                 case 2:
                     cinemaManager.buyTicket();
                     break;
                 case 3:
                    cinemaManager.statistics();
                 default:
                     break;
             }

         } while (userInput != 0);


    }
}

class CinemaManager{
    char[][] seatsArray;
    private int numberOfRows;
    private int numberOfSeatsInRow;
    private int numberOfPurchasedTickets = 0;
    private double percentage = 0;
    private int currentIncome = 0;
    private int totalIncome = 0;

    public CinemaManager(int numberOfRows, int numberOfSeatsInRow) {
        this.numberOfRows = numberOfRows;
        this.numberOfSeatsInRow = numberOfSeatsInRow;
        this.seatsArray = new char[numberOfRows + 1][numberOfSeatsInRow + 1];
        for (int i = 0; i <= numberOfRows; i++) {
            for (int j = 0; j <= numberOfSeatsInRow; j++) {
                if (i == 0 && j == 0) {
                    seatsArray[i][j] = ' ';
                } else if (i == 0 && j > 0) {
                    seatsArray[i][j] = (char) (j + 48);
                } else if (j == 0 && i > 0) {
                    seatsArray[i][j] = (char) (i + 48);
                } else {
                    seatsArray[i][j] = 'S';
                }
            }
        }
        int numberOfSeats = numberOfRows * numberOfSeatsInRow;
        if(numberOfSeats < 60) {
            this.totalIncome = numberOfSeats * 10;
        } else {
            this.totalIncome =  ((numberOfRows / 2)*numberOfSeatsInRow * 10) + (numberOfSeats - (( numberOfRows / 2)*numberOfSeatsInRow)) * 8;
        }
    }



    public void showSeats() {
        System.out.println("Cinema:");
        for(int i = 0; i <= numberOfRows; i++) {
            for (int j = 0; j <= numberOfSeatsInRow; j++) {
                System.out.print(seatsArray[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }

    }

    public void buyTicket() {
        Scanner scanner = new Scanner(System.in);
        int rowNumber = 0;
        int seatNumber = 0;

        do {
            System.out.println();
            System.out.println("Enter a row number:");
             int tmpRowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int tmpSeatNumber = scanner.nextInt();
            if(tmpRowNumber <1 || tmpRowNumber > this.numberOfRows || tmpSeatNumber < 1 || tmpSeatNumber > this.numberOfSeatsInRow) {
                System.out.println();
                System.out.println("Wrong input!");
            } else if(seatsArray[tmpRowNumber][tmpSeatNumber] == 'B') {
                System.out.println();
                System.out.println("That ticket has already been purchased");
            } else {
                rowNumber = tmpRowNumber;
                seatNumber =tmpSeatNumber;
            }
        } while (seatsArray[rowNumber][seatNumber] != 'S');



        seatsArray[rowNumber][seatNumber] = 'B';
        int price = 0;
        int numberOfSeats = numberOfRows*numberOfSeatsInRow;
        if(numberOfSeats<60){
            price= 10;
        } else {
            price = rowNumber > numberOfRows / 2 ?  8 :  10;
        }
        System.out.println("Ticket price:$" + price);

        // do statistics
        this.currentIncome += price;
        this.numberOfPurchasedTickets += 1;
        this.percentage =100 * (float)numberOfPurchasedTickets / ((float)numberOfRows * (float)numberOfSeatsInRow);

    }

    public void statistics(){

        //count tickets
        System.out.println("Number of purchased tickets:" + numberOfPurchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.printf("Current income: $%d\n", currentIncome );
        System.out.printf("Total income: $%d\n", totalIncome );

    }

}