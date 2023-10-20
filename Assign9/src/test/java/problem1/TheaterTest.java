package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TheaterTest {
  private Theater Theater;

  @BeforeEach
  void setUp() {
    List<Integer> accessibleRows = new ArrayList<>(Arrays.asList(2, 4));
    Theater = new Theater("Test Theater", 5, 10, accessibleRows);
  }

  @Test
  void getName() {
    assertEquals("Test Theater", Theater.getName());
  }

  @Test
  void getRows() {
    List<Row> rows = Theater.getRows();
    assertEquals(5, rows.size());
    assertEquals(1, rows.get(0).getRowNumber());
    assertEquals(10, rows.get(0).size());
    assertEquals(2, rows.get(1).getRowNumber());
    assertTrue(rows.get(1).isWheelchairAccessible());
    assertEquals(4, rows.get(3).getRowNumber());
    assertTrue(rows.get(3).isWheelchairAccessible());
  }

  @Test
  void getAccessibleRows() {
    List<Integer> accessibleRows = Theater.getAccessibleRows();
    assertEquals(2, accessibleRows.size());
    assertTrue(accessibleRows.contains(2));
    assertTrue(accessibleRows.contains(4));
  }

  @Test
  void getNumberOfSeatsPerRow() {
    assertEquals(10, Theater.getNumberOfSeatsPerRow());
  }

  @Test
  void testEquals() {
    List<Integer> accessibleRows = new ArrayList<>(Arrays.asList(2, 4));
    Theater Theater2 = new Theater("Test Theater", 5, 10, accessibleRows);
    assertEquals(Theater, Theater2);
  }

  @Test
  void testHashCode() {
    List<Integer> accessibleRows = new ArrayList<>(Arrays.asList(2, 4));
    Theater Theater2 = new Theater("Test Theater", 5, 10, accessibleRows);
    assertEquals(Theater.hashCode(), Theater2.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "1 _ _ _ _ _ _ _ _ _ _\n" +
        "2 = = = = = = = = = =\n" +
        "3 _ _ _ _ _ _ _ _ _ _\n" +
        "4 = = = = = = = = = =\n" +
        "5 _ _ _ _ _ _ _ _ _ _\n";
    assertEquals(expectedString, Theater.toString());
  }
}