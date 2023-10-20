package problem1;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReservationsServiceTest {
  private Theater t;
  private ReservationsService r;

  @BeforeEach
  void setUp() {
    List<Integer> rowsForWheelchair = new ArrayList<>();
    rowsForWheelchair.add(5);
    t = new Theater("A", 15, 10, rowsForWheelchair);
    r = new ReservationsService();
  }

  @Test
  void sortRows() {
    List<Integer> rowsForWheelchair = new ArrayList<>();
    rowsForWheelchair.add(5);
    Theater t2 = new Theater("B", 10, 10, rowsForWheelchair);
    r.sortRows(t);
    r.sortRows(t2);
  }

  @Test
  void reserve() {
    r.reserve(t,5,"no", "John");
    r.reserve(t,11,"no", "Kami");
    r.reserve(t,9,"no", "Timi");
    r.reserve(t,9,"no", "Ant");
    r.reserve(t,10,"no", "Bob");
    r.reserve(t,8,"yes", "Kami");
  }

  @Test
  void show() {
    r.show(t);
  }

  @Test
  void testToString() {
    r.toString();
  }
}