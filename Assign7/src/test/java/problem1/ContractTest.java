package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContractTest {
  private Sale testSale;
  private Sale testSale2;
  private Rental testRental;


  @BeforeEach
  void setUp() {
    testRental = new Rental(10.0,true, 12);
    testSale = new Sale(20.0, false);
    assertThrows(IllegalArgumentException.class, () -> {
      testSale2 = new Sale(-5.0, false);
    });

  }

  @Test
  void getAskingPrice() {
    assertEquals(10.0, testRental.getAskingPrice());
    assertEquals(20.0, testSale.getAskingPrice());
  }

  @Test
  void getNegotiable() {
    assertTrue(testRental.getNegotiable());
    assertFalse(testSale.getNegotiable());
  }

  @Test
  void testEquals() {
    assertTrue(testRental.equals(testRental));
    assertTrue(testSale.equals(testSale));

    assertFalse(testRental.equals("string"));
    assertFalse(testSale.equals("string"));

    assertFalse(testRental.equals(null));
    assertFalse(testSale.equals(null));

    Rental testRental1 = new Rental(10.0,true, 12);
    Sale testSale1 = new Sale(20.0,false);
    assertTrue(testRental.equals(testRental1));
    assertTrue(testSale.equals(testSale1));

  }

  @Test
  void testHashCode() {
    Rental expectedRental = new Rental(10.0,true,12);
    assertEquals(expectedRental.hashCode(), testRental.hashCode());
    Sale expectedSale = new Sale(20.0,false);
    assertEquals(expectedSale.hashCode(), testSale.hashCode());

  }

  @Test
  void testToString() {
    String expectedString = "Rental{" +
        "termInMonths=" + testRental.getTermInMonths() +
        '}';
    assertEquals(expectedString, testRental.toString());
    String expectedString2 = "Contract{" +
        "askingPrice=" + testSale.getAskingPrice() +
        ", isNegotiable=" + testSale.getNegotiable() +
        '}';
    assertEquals(expectedString2, testSale.toString());
  }
}