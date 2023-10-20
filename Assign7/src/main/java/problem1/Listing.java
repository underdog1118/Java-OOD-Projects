package problem1;

import java.util.Objects;

/**
 * A class representing a listing in a real estate agency.
 *
 * @param <P> the type of property associated with the listing
 * @param <C> the type of contract associated with the listing
 */
public class Listing<P extends Property, C extends Contract> {
  /**
   * The property associated with the listing.
   */
  private P property;

  /**
   * The contract associated with the listing.
   */
  private C contract;

  /**
   * Constructs a new listing with the given property and contract.
   *
   * @param property the property associated with the listing
   * @param contract the contract associated with the listing
   */
  public Listing(P property, C contract) {
    this.property = property;
    this.contract = contract;
  }

  /**
   * Returns the property associated with the listing.
   *
   * @return the property associated with the listing
   */
  public P getProperty() {
    return property;
  }

  /**
   * Returns the contract associated with the listing.
   *
   * @return the contract associated with the listing
   */
  public C getContract() {
    return contract;
  }

  /**
   *Equals method for the class
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Listing<P, C> listing = (Listing<P, C>) o;
    return Objects.equals(property, listing.property) && Objects.equals(contract,
        listing.contract);
  }

  /**
   * Hashcode method for the class
   */
  @Override
  public int hashCode() {

    return Objects.hash(property, contract);
  }

  /**
   *
   * To String method for the class
   */
  @Override
  public String toString() {
    return "Listing{" +
        "property=" + property +
        ", contract=" + contract +
        '}';
  }
}