package problem1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The service class to reserve seats
 */
public class ReservationsService {

  /**
   * Return a sorted rows by their distance to the center row
   *
   * @param t the theater
   * @return a sorted rows by their distance to the center row
   */
  public ArrayList<Integer> sortRows(Theater t) {
    int rows = t.getRows().size();  //number of rows
    double center = rows%2 == 0 ? (double) (rows/2+0.5) : (double) (rows/2+ 1);
    ArrayList<Integer> sortedRows = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      sortedRows.add(i+1);
    }
    // [8,  7,9,  6,11,  5,12,  ...]
    sortedRows.sort(Comparator.comparingDouble(n -> Math.abs((double) n - center)));
    return sortedRows;
  }

  /**
   * Reserves the seats in the theater, based on the choice on wheelchair-accessible seats
   * and the name of the person.
   *
   * @param theater  The theater
   * @param seats The number of seats to reserve
   * @param answer The choice of whether wheelchair-accessible seats are required
   * @param name  The name of the person for whom these seats are reserved
   * @return the row that is selected to reserve
   */
  public int reserve(Theater theater, Integer seats, String answer, String name) {
    ArrayList<Integer> sortedRows = sortRows(theater);
    int selectedRow = -1;
    for (int i = 0; i < sortedRows.size(); i++) {
      int rowNum = sortedRows.get(i)-1;  // i=0, rowNum->8-1 = 7
      Row row = theater.getRows().get(rowNum);

      if ((answer.equals("no") && row.isWheelchairAccessible()) ||
          (answer.equals("yes") && !row.isWheelchairAccessible())) {
        continue;
      }

      int colNum = getAvailableCol(row, seats); //get available seats
      if (colNum == -1) {
        continue;
      }
      selectedRow = rowNum;
      this.updateSeating(theater,selectedRow,colNum,seats, name);
      break;
    }

    //If and only if all other rows are occupied,
    //then the system will reserve seats in accessible rows to users who do not need accessible seats.
    if (selectedRow == -1 && answer.equals("no")) {
      selectedRow = reserve(theater, seats, "yes", name)-1;
    }
    return selectedRow+1;  // <= 0 -- invalid
  }

  /**
   * Return if the row is available, return the start col in the row; else return -1
   *
   * @param row the row need to check
   * @param seatsToReserve the num of seats to reserve
   * @return if the row is available, return the start col in the row;
   * else return -1
   */
  public int getAvailableCol(Row row, int seatsToReserve)  {
    int col = -1;
    for (int i = 0; i < row.getNumSeats(); i++) {
      if (row.get(i).getReservedFor() == null) {
        col = i;
        break;
      }
    }
    return (row.getNumSeats() - col) >= seatsToReserve ? col : -1;
  }


  /**
   * Updates the seats in the Theater based on the given index of row, the index of seat, the number of
   * seats to reserve and the name of the person
   *
   * @param row  The index of the row to reserve
   * @param col  The index of the seat to reserve
   * @param num  The number of seats to reserve
   * @param name The name of the person for whom the seats are reserved
   */
  public void updateSeating(Theater theater, Integer row, Integer col, Integer num, String name) {
    Row selectedRow = theater.getRows().get(row);
    for (Integer c = col; c < num + col; c++) {
      selectedRow.get(c).setReservedFor(name);
    }
  }

  /**
   * Return the view of the theater
   *
   * @param t the theater
   * @return the view of the theater
   */
  public String show(Theater t) {
    return t.toString();
  }

  /**
   * toString method for the class
   */
  @Override
  public String toString() {
    return "ReservationsService{}";
  }
}
