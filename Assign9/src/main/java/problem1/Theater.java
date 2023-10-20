package problem1;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 The Theater class represents a theater with rows of seats. It contains methods for creating and retrieving rows and seats.
 */
public class Theater {
  private String name;
  private List<Row> rows;
  private List<Integer> accessibleRows;
  private Integer numberOfSeatsPerRow;


  /**
   Constructs a new Theater with the given name, number of rows, number of seats per row, and list of accessible rows.
   @param name the name of the theater
   @param numberOfRows the number of rows in the theater
   @param numberOfSeatsPerRow the number of seats in each row of the theater
   @param accessibleRows a list of row numbers that are accessible for wheelchair users
   @throws IllegalArgumentException if there are no accessible rows in the theater
   */
  public Theater(String name, int numberOfRows, int numberOfSeatsPerRow, List<Integer> accessibleRows) {
    this.name = name;
    this.rows = new ArrayList<>();
    this.accessibleRows = accessibleRows;
    this.numberOfSeatsPerRow = numberOfSeatsPerRow;

    // Ensure there's at least one accessible row
    if (accessibleRows.isEmpty()) {
      throw new IllegalArgumentException("There must be at least one accessible row.");
    }

    // Create rows and add them to the theater
    for (int i = 1; i <= numberOfRows; i++) {
      Boolean isAccessible = accessibleRows.contains(i);
      rows.add(new Row(i, numberOfSeatsPerRow, isAccessible));
    }
  }

  /**
   Returns the name of the theater.
   @return the name of the theater
   */
  public String getName() {
    return name;
  }


  /**
   Returns the list of rows in the theater.
   @return the list of rows in the theater
   */
  public List<Row> getRows() {
    return rows;
  }

  /**
   Returns the number of seats in each row of the theater.
   @return the number of seats in each row of the theater
   */
  public List<Integer> getAccessibleRows() {
    return accessibleRows;
  }

  /**
   * Gets the value of numberOfSeatsPerRow.
   *
   * @return the value of numberOfSeatsPerRow
   */
  public Integer getNumberOfSeatsPerRow() {
    return this.numberOfSeatsPerRow;
  }


  /**
   Compares this theater to the specified object. The result is true if and only if the argument is not null and is a
   theater object that has the same name, rows, accessibleRows, and numberOfSeatsPerRow as this object.
   @param o the object to compare this theater against
   @return true if the given object represents a Theater equivalent to this theater, false otherwise
   */

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Theater theater = (Theater) o;
    return Objects.equals(name, theater.name) && Objects.equals(rows,
        theater.rows) && Objects.equals(accessibleRows, theater.accessibleRows)
        && Objects.equals(numberOfSeatsPerRow, theater.numberOfSeatsPerRow);
  }

  /**
   Returns a hash code value for the theater.
   @return a hash code value for the theater
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, rows, accessibleRows, numberOfSeatsPerRow);
  }


  /**
   Returns a string representation of the theater, which includes a string representation of each row in the theater,
   separated by a newline character.
   @return a string representation of the theater
   */
  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (Row row : rows) {
      stringBuilder.append(row.toString()).append("\n");
    }
    return stringBuilder.toString();

  }
}

