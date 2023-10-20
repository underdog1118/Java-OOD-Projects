package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListingTest {

  private Residential residential1;
  private Residential residential2;
  private Commercial commercial1;
  private Commercial commercial2;
  private Sale sale1;
  private Sale sale2;
  private Rental rental1;
  private Rental rental2;
  private Listing<Property, Contract> listing1;
  private Listing<Property, Contract> listing2;
  private Listing<Residential, Rental> listing3;

  @BeforeEach
  void setUp() {
    residential1 = new Residential("Seattle", 1000, 2, 1.5);
    commercial1 = new Commercial("Bellevue", 1500, 10, true);
    sale1 = new Sale(150000.00, true);
    rental1 = new Rental(2000.00, false, 5);
    residential2 = new Residential("Main", 2000, 3, 2.5);
    commercial2 = new Commercial("Kent", 1000, 5, false);
    sale2 = new Sale(200000.00, false);
    rental2 = new Rental(1000.00, true, 3);

    Listing<Property, Contract> listing = new Listing<>(new Residential("Seattle", 1000,
        2, 1.5), new Sale(150000.00, true));
    assertTrue(listing.equals(listing));

    listing1 = new Listing<>(residential1, sale1);
    listing2 = new Listing<>(commercial1, rental1);
    listing3 = new Listing<>(residential2, rental2);
  }

  @Test
  void getProperty() {
    assertEquals(residential1, listing1.getProperty());
    assertEquals(commercial1, listing2.getProperty());
  }

  @Test
  void getContract() {
    assertEquals(sale1, listing1.getContract());
    assertEquals(rental1, listing2.getContract());
  }

  @Test
  void testEquals() {
    Residential residential3 = new Residential("Seattle", 1000, 2,
        1.5);
    Residential residential4 = new Residential("Seattle", 1000, 2, 1.5);
    Listing<Property, Contract> listing4 = new Listing<>(residential1, sale1);
    Listing<Property, Contract> listing5 = new Listing<>(residential1, sale1);
    assertTrue(listing4.equals(listing5));
    assertFalse(residential3.equals(commercial1));
    assertFalse(residential3.equals(commercial2));
    assertFalse(residential3.equals(null));
    assertFalse(residential3.equals("string"));
    assertTrue(residential3.equals(residential4));
    assertFalse(listing1.equals(listing2));
    assertFalse(listing1.equals(listing3));

    Residential residential1 = new Residential("Seattle", 1000, 2, 1.5);
    Sale sale1 = new Sale(150000.00, true);
    Listing<Property, Contract> listing1 = new Listing<>(residential1, sale1);

    Object object = new Object();

    assertFalse(listing1.equals(object));
  }

  @Test
  void testHashCode() {
    Listing<Property, Contract> listing1Copy = new Listing<>(residential1, sale1);
    assertEquals(listing1.hashCode(), listing1Copy.hashCode());
    assertNotEquals(listing1.hashCode(), listing2.hashCode());
    assertNotEquals(listing1.hashCode(), listing3.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("Listing{property=Residential{address='Seattle', size=1000, "
        + "numOfBedrooms=2, numOfBathrooms=1.5}, contract=Contract{askingPrice=150000.0, "
        + "isNegotiable=true}}",listing1.toString());

  }
}