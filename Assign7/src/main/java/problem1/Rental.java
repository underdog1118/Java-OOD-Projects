package problem1;

import java.util.Objects;

/**
 * class Rental extends abstract class Contract
 */
public class Rental extends Contract {

  private Integer termInMonths;

  /**
   * constructor for class Rental
   *
   * @param askingPrice  price of contract
   * @param isNegotiable if contract is negotiable
   * @param termInMonths contract length in month that is not negative
   */
  public Rental(Double askingPrice, Boolean isNegotiable, Integer termInMonths) {
    super(askingPrice, isNegotiable);
    this.termInMonths = checkTermInMonths(termInMonths);
  }

  /**
   * checks if terms in month is negative or not
   *
   * @param termInMonths
   * @return
   */
  private Integer checkTermInMonths(Integer termInMonths) {
    if (termInMonths < 0) {
      throw new IllegalArgumentException("Terms in months must be greater than 0.");
    } else {
      return termInMonths;
    }
  }

  /**
   * getter for terms in month
   * @return months
   */
  public Integer getTermInMonths() {
    return termInMonths;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Rental rental = (Rental) o;
    return Objects.equals(termInMonths, rental.termInMonths);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), termInMonths);
  }

  @Override
  public String toString() {
    return "Rental{" +
        "termInMonths=" + termInMonths +
        '}';
  }

  /**
   * get asking price of contract
   * @return asking price
   */
  @Override
  public Double getAskingPrice() {
    return super.getAskingPrice();
  }

  /**
   * calculates the value  of contract
   * @return value of contract
   */
  @Override
  public Double calculatePrice(Double commissionRate) {
    return super.calculatePrice(commissionRate) * this.termInMonths;
  }

}
