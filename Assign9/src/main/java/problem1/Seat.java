/**
 * Class Seat represent a seat object, it has name and reservedFor as fields.
 */
package problem1;
import java.util.Objects;

public class Seat {
  private String name;
  private String reservedFor;

  /**
   * Constructor for Seat
   * @param name - the name of the seat, a capital letter from A to Z.
   */
  public Seat(String name) throws IllegalArgumentException{
    // If the seat number is exceed 26, or the name is not [A-Z]
    // the exception would be called -> No new seats can be created
    if(name.matches("[A-Z]")){
      this.name = name;
    } else{
      throw new IllegalArgumentException("The seat's name should be a capital letter from A to Z.");
    }
    this.reservedFor = null;
  }

  /**
   * Method to reserve the seat for the person
   * @param name - the name of the person who make reservation of the seat
   */
  public void setReservedFor(String name){
    this.reservedFor = name;
  }

  /**
   * Getters for name of the seat
   * @return - name, as a String
   */
  public String getName() {
    return name;
  }

  /**
   * Getters for ReservedFor of the seat
   * @return - reservedFor, as a String
   */
  public String getReservedFor() {
    return reservedFor;
  }

  /**
   * @return - the information of the seat, as a String
   */
  @Override
  public String toString() {
    return "Seat{" +
        "name='" + name + '\'' +
        ", reservedFor='" + reservedFor + '\'' +
        '}';
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
    Seat seat = (Seat) o;
    return Objects.equals(name, seat.name) && Objects.equals(reservedFor,
        seat.reservedFor);
  }

  /**
   * @return - the information of the seat, as an integer
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, reservedFor);
  }
}
