package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AgentTest {
  private Agent<Residential, Sale> agent;
  private Agent<Residential, Sale> agent2;
  private Agent<Residential, Rental> agent3;
  private Residential testProperty;
  private Sale testContract;
  private Sale testContract2;
  private Rental testRental;
  private Listing<Residential,Sale> listing;
  private Listing<Residential,Sale> listing2;
  private Listing<Residential,Rental> listing3;
  @BeforeEach
  void setUp(){
    testProperty = new Residential("100 Central Way", 1000, 3,
        1.5);
    testContract = new Sale(100000.00, true);
    testContract2 = new Sale(300000.00, true);
    testRental = new Rental(100.0,true,12);
    listing = new Listing<>(testProperty,testContract);
    listing2 = new Listing<>(testProperty, testContract2);
    listing3 = new Listing<>(testProperty, testRental);
    agent = new Agent<>("Abby Abby", new ArrayList<>(),0.05,0.0);
    agent2 = new Agent<>("Abby Abby", new ArrayList<>(),0.09,0.0);
    agent3 = new Agent<>("Abby Abby", new ArrayList<>(),0.1,0.0);

  }

  @Test
  void testGetName(){
    assertEquals("Abby Abby", agent.getName());

  }

  @Test
  void testSetName(){
    agent.setName("Abby Li");
    assertEquals("Abby Li", agent.getName());

  }

  @Test
  void testGetCollectionListing(){
    assertTrue(agent.getCollectionOfListing().isEmpty());

  }

  @Test
  void testSetCollectionListing(){
    List<Listing<Residential,Sale>> listings = new ArrayList<>();
    listings.add(listing);
    agent.setCollectionOfListing(listings);
    assertFalse(agent.getCollectionOfListing().isEmpty());
    assertEquals(1,agent.getCollectionOfListing().size());
    assertEquals(listing,agent.getCollectionOfListing().get(0));

  }

  @Test
  void getCommissionRate(){
    assertEquals(0.05, agent.getCommissionRate(),0.001);
  }

  @Test
  void setCommissionRate(){
    agent.setCommissionRate(0.1);
    assertEquals(0.1,agent.getCommissionRate(),0.001);
  }

  @Test
  void testGetTotalEarning(){
    assertThrows(IllegalArgumentException.class, () ->{
      agent.setCommissionRate(1.5);
    });
  }

  @Test
  void testSetTotalEarning(){
    agent.setTotalEarning(1000.00);
    assertEquals(1000.00, agent.getTotalEarning(),0.001);
    Exception IllegalArgumentException = assertThrows(Exception.class, () -> agent.setTotalEarning(-4.4));
    assertEquals("The total earning is not valid", IllegalArgumentException.getMessage());
  }


  @Test
  void addListing() {
    agent.addListing(listing);
    agent.addListing(listing2);

//    agent.addListing(listing3);  //invalid input listing, not an appropriate type
    agent3.addListing(listing3);  // Corresponding agent and listing
  }

  @Test
  void completeListing() {
    Exception IllegalArgumentException = assertThrows(Exception.class, () -> agent.completeListing(listing));
    assertEquals("This listing doesn't exist", IllegalArgumentException.getMessage());
    agent.addListing(listing);
    agent.addListing(listing2);
    agent.completeListing(listing);
    assertEquals(5000.0,agent.getTotalEarning());

    agent3.addListing(listing3);
    agent3.completeListing(listing3);
    assertEquals(120.0, agent3.getTotalEarning());
  }

  @Test
  void dropListing() {
    Exception IllegalArgumentException = assertThrows(Exception.class, () -> agent.dropListing(listing));
    assertEquals("This listing doesn't exist", IllegalArgumentException.getMessage());
    agent.addListing(listing);
    agent.dropListing(listing);
  }

  @Test
  void getTotalPortfolioValue() {
    assertEquals(0.0, agent.getTotalPortfolioValue());
    agent.addListing(listing);
    agent.addListing(listing2);
    assertEquals(20000.0, agent.getTotalPortfolioValue());

    agent3.addListing(listing3);
    assertEquals(120.0, agent3.getTotalPortfolioValue());
  }

  @Test
  void testEquals() {
    assertEquals(agent,agent);
    assertNotEquals(agent,agent2);
    assertNotEquals(agent,"agent");
    assertNotEquals(agent,null);
  }

  @Test
  void testHashCode() {
    assertEquals(agent.hashCode(), agent.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("Agent{name='Abby Abby', collectionOfListing=[], commissionRate=0.05, totalEarning=0.0}", agent.toString());
  }

}