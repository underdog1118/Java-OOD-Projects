package problem1;

import java.util.Objects;

/**
 * abstract class contract contains information for a contract. This includes the asking price and
 * if the contract is negotiable.
 */
public abstract class Contract {

  private Double askingPrice;
  private Boolean isNegotiable;

  /**
   * constructor for class contract
   *
   * @param askingPrice  asking price of contract
   * @param isNegotiable if contract is negotiable
   */
  public Contract(Double askingPrice, Boolean isNegotiable) {
    this.askingPrice = checkAskingPrice(askingPrice);
    this.isNegotiable = isNegotiable;
  }

  /**
   * checks if asking price is negative or not
   *
   * @param askingPrice price of contract
   * @return askingPrice
   */
  private Double checkAskingPrice(Double askingPrice) {
    if (askingPrice < 0) {
      throw new IllegalArgumentException("Asking price cannot be negative.");
    } else {
      return askingPrice;
    }
  }

  /**
   * gettter for askingPrice
   *
   * @return askingPrice
   */
  public Double getAskingPrice() {
    return askingPrice;
  }

  /**
   * getter for if negotiable
   *
   * @return if contract is negotiable
   */
  public Boolean getNegotiable() {
    return isNegotiable;
  }

  /**
   * calculates the value  of contract
   * @return asking price
   */
  protected Double calculatePrice(Double commissionPrice){
    return askingPrice * commissionPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contract contract = (Contract) o;
    return Objects.equals(askingPrice, contract.askingPrice) && Objects.equals(
        isNegotiable, contract.isNegotiable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(askingPrice, isNegotiable);
  }

  @Override
  public String toString() {
    return "Contract{" +
        "askingPrice=" + askingPrice +
        ", isNegotiable=" + isNegotiable +
        '}';
  }
}
