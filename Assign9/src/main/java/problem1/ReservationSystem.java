package problem1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationSystem {

  public static final Integer NUM_OF_ROWS = 15;
  public static final Integer NUM_OF_SEATS_IN_A_ROW = 10;

  public static void main(String[] args) {
    /**
     * Setting up theater object
     */

    //add rows for wheelchair
    List<Integer> rowsForChair = new ArrayList<>();
    rowsForChair.add(1);
    rowsForChair.add(5);

    Theater theater = new Theater("Good Theater", NUM_OF_ROWS, NUM_OF_SEATS_IN_A_ROW, rowsForChair);
    ReservationsService service = new ReservationsService();  //~1

    /**
     * prompt to user for instructions
     */
    Scanner sc = new Scanner(System.in);
    while (true) {
      System.out.println("What would you like to do?");
      String command = sc.nextLine();
      command = command.trim();
      // Show the seat map of Theater
      if (command.equals("show")) {
        System.out.println(service.show(theater));  // ~2
        // Exit the program
      } else if (command.equals("done")) {
        System.out.println("Have a nice day!");
        break;
        // Book seats under a given name and number of seats.
      } else if (command.length() > 7 && command.startsWith("reserve")) {
        String subString = command.substring(7).trim();
        // Check if there's enough seat to reserve
        if (subString.matches("-?\\d+")) {
          Integer count = Integer.parseInt(subString);

          if (count <= theater.getNumberOfSeatsPerRow()) {
            System.out.println("Do you need wheelchair accessible seats?");
            String command2 = sc.nextLine().trim();
            if (command2.equals("yes") || command2.equals("no")) {
              System.out.println("What's your name?");
              String name = sc.nextLine().trim();
              // Reserve for accessible seats.
              int rowNumber = service.reserve(theater, count, command2, name); //~3
              if (rowNumber > 0) {
                System.out.println(
                    "I’ve reserved " + count + " seats for you at the " + theater.getName()
                        + " in row, " + rowNumber + ", " + name + ".");
              } else {
                System.out.println(
                    "Sorry We don't have that many seats available together to you.");
              }
            } else {
              System.out.println("Command not found!");
            }
          } else {
            System.out.println("Sorry, we don’t have that many seats together for you.");
          }
        } else {
          System.out.println("Command not found!");
        }
      } else {
        System.out.println("Command not found!");
      }
    }
  }
}
