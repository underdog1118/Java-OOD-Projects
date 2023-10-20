package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PropertyTest {
  private Residential testResidential;
  private Commercial testCommercial;
  @BeforeEach
  void setUp() {
    testResidential = new Residential("Seattle", 200, 2, 1.0);
    testCommercial = new Commercial("Seattle", 500, 2, true);
  }

  @Test
  void testConstructorsException(){
    // Property - Size - Non-negative
    try{
      testResidential = new Residential("Seattle", -200, 2, 1.0);
    } catch (IllegalArgumentException e){
      assertEquals("The given value should not be negative.", e.getMessage());
    }

    // Residential - numOfBedrooms - Non-negative
    try{
      testResidential = new Residential("Seattle", 200, -2, 1.0);
    } catch (IllegalArgumentException e){
      assertEquals("The given value should not be negative.", e.getMessage());
    }

    // Residential - numOfBathrooms - Non-negative
    try{
      testResidential = new Residential("Seattle", 200, 2, -1.0);
    } catch (IllegalArgumentException e){
      assertEquals("The given value should not be negative.", e.getMessage());
    }

    // Residential - numOfOffices - Non-negative
    try{
      testCommercial = new Commercial("Seattle", 200, -2, false);
    } catch (IllegalArgumentException e){
      assertEquals("The given value should not be negative.", e.getMessage());
    }
  }

  @Test
  void getAddress() {
    assertEquals("Seattle", testResidential.getAddress());
    assertEquals("Seattle", testCommercial.getAddress());
  }

  @Test
  void getSize() {
    assertEquals(200, testResidential.getSize());
    assertEquals(500, testCommercial.getSize());
  }

  @Test
  void getNumOfOffices(){
    assertEquals(2, testCommercial.getNumOfOffices());

  }

  @Test
  void getSuitableForRetail(){
    assertTrue(testCommercial.getSuitableForRetail());
  }

  @Test
  void getNumOfBedrooms(){
    assertEquals(2, testResidential.getNumOfBedrooms());
  }

  @Test
  void getNumOfBathrooms(){
    assertEquals(1.0, testResidential.getNumOfBathrooms());

  }

  @Test
  void testEquals(){
    Residential expectedResidential = new Residential("Seattle", 200, 2, 1.0);
    Commercial expectedCommercial = new Commercial("Seattle", 500, 2, true);
    assertTrue(testResidential.equals(testResidential));
    assertTrue(testCommercial.equals(testCommercial));

    assertTrue(testResidential.equals(expectedResidential));
    assertTrue(testCommercial.equals(expectedCommercial));

    assertFalse(testResidential.equals(testCommercial));
    assertFalse(testCommercial.equals(testResidential));

    assertFalse(testResidential.equals(null));
    assertFalse(testCommercial.equals(null));
  }

  @Test
  void testHashCode(){

    Integer expectedResidentialValue = Objects.hash(Objects.hash("Seattle", 200), 2, 1.0);
    assertEquals(expectedResidentialValue, testResidential.hashCode());
    Integer expectedCommercialValue = Objects.hash(Objects.hash("Seattle", 500), 2, true);
    assertEquals(expectedCommercialValue, testCommercial.hashCode());

  }

  @Test
  void testToString(){
    String expectedResidentialString = "Residential{address='Seattle', size=200, numOfBedrooms=2, numOfBathrooms=1.0}";
    assertEquals(expectedResidentialString, testResidential.toString());

    String expectedCommercialString = "Commercial{address='Seattle', size=500, numOfOffices=2, suitableForRetail=true}";
    assertEquals(expectedCommercialString, testCommercial.toString());
  }

}