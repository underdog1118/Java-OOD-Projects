package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SeatAndRowTest {

  private Row testRow;
  private Seat testSeat;
  @BeforeEach
  void setUp() {
    testRow = new Row(3, 25, true);
    testSeat = new Seat("A");
    try{
      testSeat = new Seat("a");
    }catch (IllegalArgumentException e){
    }
  }

  @Test
  void getName() {
    assertEquals("A", testSeat.getName());
  }
  @Test
  void getRowNumber() {
    assertEquals(3, testRow.getRowNumber());
  }

  @Test
  void getNumSeats() {
    assertEquals(25, testRow.size());
    assertEquals(25, testRow.getNumSeats());

    // If the numSeats > 26, it will still creat a 26 seats row
    testRow = new Row(3, 27, true);
    assertEquals(26, testRow.size());
    assertEquals(26, testRow.getNumSeats());
  }

  @Test
  void isWheelchairAccessible() {
    assertTrue(testRow.isWheelchairAccessible());
  }

  @Test
  void testEquals() {
    assertFalse(testRow.equals(null));
    assertTrue(testRow.equals(testRow));

    assertTrue(testRow.equals(new Row(3, 25, true)));
    assertFalse(testRow.equals(new Row(3, 3, true)));

    assertFalse(testSeat.equals(null));
    assertTrue(testSeat.equals(testSeat));

    assertTrue(testSeat.equals(new Seat("A")));
    assertFalse(testSeat.equals(new Seat("X")));

  }

  @Test
  void testHashCode() {
    Integer expectedValue = testRow.hashCode();
    assertEquals(expectedValue, testRow.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "3 = = = = = = = = = = = = = = = = = = = = = = = = =";
    assertEquals(expectedString, testRow.toString());

    testRow = new Row(3, 25, false);
    String expectedString1 = "3 _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _";
    assertEquals(expectedString1, testRow.toString());

    for (int i = 0; i < testRow.size(); i++) {
      Seat seat = testRow.get(i);
      seat.setReservedFor("name");
    }
    String expectedString2 = "3 X X X X X X X X X X X X X X X X X X X X X X X X X";
    assertEquals(expectedString2, testRow.toString());


    String expectedSeat = "Seat{" +
        "name='" + testSeat.getName() + '\'' +
        ", reservedFor='" + testSeat.getReservedFor() + '\'' +
        '}';
    assertEquals(expectedSeat, testSeat.toString());
  }
}