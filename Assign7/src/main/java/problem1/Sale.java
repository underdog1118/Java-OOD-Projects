package problem1;

/**
 * class sale extends abstract class contract
 */
public class Sale extends Contract {

  /**
   * constructor for class Sale
   *
   * @param askingPrice  asking price of contract
   * @param isNegotiable if contract is negotiable
   */
  public Sale(Double askingPrice, Boolean isNegotiable) {
    super(askingPrice, isNegotiable);
  }

  /**
   * get asking price of contract
   *
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
  protected Double calculatePrice(Double commissionRate) {
    return super.calculatePrice(commissionRate);
  }
}
