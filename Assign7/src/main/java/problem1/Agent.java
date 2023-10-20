package problem1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An agent has a name, a list of listings, a commission rate, and a total earning.
 * @param <P> property type
 * @param <C> contract type
 */
public class Agent<P extends Property, C extends Contract> implements IAgent<P,C>{
  private String name;
  private List<Listing<P,C>> collectionOfListing;
  private Double commissionRate;
  private Double totalEarning;

  /**
   * @param name -- name
   * @param collectionOfListing -- listings
   * @param commissionRate -- commission rate
   * @param totalEarning -- total Earning
   */
  public Agent(String name, List<Listing<P, C>> collectionOfListing, Double commissionRate,
      Double totalEarning) {
    this.name = name;
    this.collectionOfListing = new ArrayList<>();
    this.setCommissionRate(commissionRate);
    this.setTotalEarning(totalEarning);
  }

  /**
   * Gets the value of name.
   *
   * @return the value of name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name.
   *
   * @param name name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the value of collectionOfListing.
   *
   * @return the value of collectionOfListing
   */
  public List<Listing<P, C>> getCollectionOfListing() {
    return this.collectionOfListing;
  }

  /**
   * Sets the collectionOfListing.
   *
   * @param collectionOfListing collectionOfListing
   */
  public void setCollectionOfListing(List<Listing<P, C>> collectionOfListing) {
    this.collectionOfListing = collectionOfListing;
  }

  /**
   * Gets the value of commissionRate.
   *
   * @return the value of commissionRate
   */
  public Double getCommissionRate() {
    return this.commissionRate;
  }

  /**
   * Sets the commissionRate.
   *
   * @param commissionRate commissionRate
   */
  public void setCommissionRate(Double commissionRate) {
    if (commissionRate >= 0 && commissionRate <= 1.0) {
      this.commissionRate = commissionRate;
    } else {
      throw new IllegalArgumentException("The rate is not valid");
    }
  }

  /**
   * Gets the value of totalEarning.
   *
   * @return the value of totalEarning
   */
  public Double getTotalEarning() {
    return this.totalEarning;
  }

  /**
   * Sets the totalEarning.
   *
   * @param totalEarning totalEarning
   */
  public void setTotalEarning(Double totalEarning) {
    if (totalEarning >= 0) {
      this.totalEarning = totalEarning;
    } else {
      throw new IllegalArgumentException("The total earning is not valid");
    }
  }

  /**
   * Adds an (appropriate) listing to the Agent’s current listing collection.
   *
   * @param listing listing to add
   */
  @Override
  public void addListing(Listing<P,C> listing) {
    this.collectionOfListing.add(listing);
  }

  /**
   * This method will be called when an Agent successfully makes a sale / rental of one of their
   * listings.
   *
   * @param listing listing to complete
   */
  @Override
  public void completeListing(Listing<P,C> listing) {
    if (!this.collectionOfListing.contains(listing)) {
      throw new IllegalArgumentException("This listing doesn't exist");
    }
    this.collectionOfListing.remove(listing);

    this.totalEarning += listing.getContract().calculatePrice(this.commissionRate);
  }

  /**
   * Drop a listing from the Agent’s collection without adjusting their earnings.
   *
   * @param listing listing to drop
   */
  @Override
  public void dropListing(Listing<P,C> listing) {
    if (!this.collectionOfListing.contains(listing)) {
      throw new IllegalArgumentException("This listing doesn't exist");
    }
    this.collectionOfListing.remove(listing);
  }

  /**
   * This returns the amount of money the Agent would make if they successfully completed all
   * listings in their collection
   *
   * @return the amount of money
   */
  @Override
  public double getTotalPortfolioValue() {
    double res = 0.0;
    for (Listing<P,C> listing : this.collectionOfListing) {
      res += listing.getContract().calculatePrice(this.commissionRate);
    }
    return res;
  }

  /**
   * equals method for the class
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Agent<?, ?> agent = (Agent<?, ?>) o;
    return Objects.equals(name, agent.name) && Objects.equals(collectionOfListing,
        agent.collectionOfListing) && Objects.equals(commissionRate, agent.commissionRate)
        && Objects.equals(totalEarning, agent.totalEarning);
  }

  /**
   * hashCode method for the class
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, collectionOfListing, commissionRate, totalEarning);
  }

  /**
   * toString method for the class
   */
  @Override
  public String toString() {
    return "Agent{" +
        "name='" + name + '\'' +
        ", collectionOfListing=" + collectionOfListing +
        ", commissionRate=" + commissionRate +
        ", totalEarning=" + totalEarning +
        '}';
  }
}
