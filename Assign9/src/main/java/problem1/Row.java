package problem1;
/**
 * Class Row represent a row object, it has rowNumber, numSeats and isWheelchairAccessible fields.
 */

import java.util.ArrayList;
import java.util.Objects;

public class Row extends ArrayList<Seat> {
  private Integer MAX_NUM_SEAT = 26;
  private Integer rowNumber;
  private Integer numSeats;
  private boolean isWheelchairAccessible;

  /**
   * Constructor of the row
   * @param rowNumber - the row number, 1 is the closest to screen
   * @param numSeats - the number of seats
   * @param isWheelchairAccessible - true if the row is Wheelchair Accessible
   */
  public Row(Integer rowNumber, Integer numSeats, boolean isWheelchairAccessible) {
    this.rowNumber = rowNumber;
    this.numSeats = numSeats;
    this.isWheelchairAccessible = isWheelchairAccessible;
    this.placeSeats();
  }

  /**
   * Helper method for the constructor
   * Place the seats with the given numSeats
   */
  private void placeSeats(){
    String seatName = "A";
    for(int i=0; i < this.numSeats; i++){
      // If the seat number is exceed 26, or the name is not [A-Z]
      // the exception would be called -> No new seats can be created
      // So there would be only 26 seats in a row even when trying to create a 27 seats row
      try{
        Seat seat = new Seat(String.valueOf((char) (seatName.charAt(0) + i)));
        this.add(seat);
      }catch (IllegalArgumentException e){
        this.numSeats = MAX_NUM_SEAT;
        System.out.println("The number of seats exceeds, number of seats set to 26.");
        System.out.println(e.getMessage());
      }
    }
  }


  /**
   * Getters for rowNumber
   * @return - the rowNumber, as an Integer
   */
  public Integer getRowNumber() {
    return rowNumber;
  }

  /**
   * Getters for numSeats
   * @return - the numSeats, as an Integer
   */
  public Integer getNumSeats() {
    return numSeats;
  }

  /**
   * Getters for isWheelchairAccessible
   * @return - the isWheelchairAccessible, as a boolean value
   */
  public boolean isWheelchairAccessible() {
    return isWheelchairAccessible;
  }

  /**
   * @param o - the given object
   * @return - true, if its equals, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Row seats = (Row) o;
    return isWheelchairAccessible == seats.isWheelchairAccessible && Objects.equals(
        rowNumber, seats.rowNumber) && Objects.equals(numSeats, seats.numSeats);
  }

  /**
   * @return - the information of the row, as an integer
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), rowNumber, numSeats, isWheelchairAccessible);
  }

  /**
   * Helper method for toString method
   * @param seat - the given seat object
   * @return - the mark of the seat depends on the seat's status
   */
  private String findSeatStatus(Seat seat){
    String seatStatus = seat.getReservedFor();
    if(seatStatus == null){
      if(this.isWheelchairAccessible){
        return " =";
      }else{
        return " _";
      }
    }else{
      return " X";
    }
  }

  /**
   * @return - the row status, as a String.
   */
  @Override
  public String toString() {
    String rowStatus = String.valueOf(this.rowNumber);
    for(Seat seat : this){
      rowStatus += this.findSeatStatus(seat);
    }
    return rowStatus;
  }
}

