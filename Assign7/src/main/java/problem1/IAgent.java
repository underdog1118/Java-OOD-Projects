package problem1;

/**
 * Interface of agent. Contains method addListing, completeListing,
 * dropListing, getTotalPortfolioValue
 */
public interface IAgent<P extends Property, C extends Contract>{

  /**
   * Adds an (appropriate) listing to the Agent’s current listing collection.
   * @param listing listing to add
   */
  void addListing(Listing<P,C> listing);

  /**
   * This method will be called when an Agent successfully makes a sale / rental of one of their listings.
   * @param listing listing to complete
   */
  void completeListing(Listing<P,C> listing);

  /**
   * Drop a listing from the Agent’s collection without adjusting their earnings.
   * @param listing listing to drop
   */
  void dropListing(Listing<P,C> listing);

  /**
   * This returns the amount of money the Agent would make
   * if they successfully completed all listings in their collection
   * @return the amount of money
   */
  double getTotalPortfolioValue();
}
