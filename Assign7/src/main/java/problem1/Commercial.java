/**
 * Class Commercial is the child class of class Property,
 * it has numOfOffices and suitableForRetail.
 */
package problem1;

import java.util.Objects;

public class Commercial extends Property{
  private Integer numOfOffices;
  private Boolean suitableForRetail;

  /**
   * Constructor of class Residential
   * @param address - the address of the properties, as a String
   * @param size - the size of the properties, as an Integer
   * @param numOfOffices - the number of offices, as an Integer
   * @param suitableForRetail - true if the Commercial is suitable for retail, as a Boolean value
   */
  public Commercial(String address, Integer size, Integer numOfOffices, Boolean suitableForRetail) {
    super(address, size);
    this.numOfOffices = super.checkNonNegative(numOfOffices, 0);
    this.suitableForRetail = suitableForRetail;
  }

  /**
   * Return the number of offices of the commercial property
   * @return - the number of offices, as an Integer
   */
  public Integer getNumOfOffices() {
    return numOfOffices;
  }
  /**
   * Return if the commercial property is suitable for retail.
   * @return - true if the Commercial is suitable for retail, false otherwise
   */
  public Boolean getSuitableForRetail() {
    return suitableForRetail;
  }
  /**
   * Check is the given object equals to this object
   * @param o - a given object
   * @return true is equals, false otherwise
   */

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
    Commercial that = (Commercial) o;
    return Objects.equals(numOfOffices, that.numOfOffices) && Objects.equals(
        suitableForRetail, that.suitableForRetail);
  }
  /**
   * @return the hashcode of the Property
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), numOfOffices, suitableForRetail);
  }
  /**
   * @return a String of the information about the Property
   */
  @Override
  public String toString() {
    return "Commercial{" +
        super.toString() +
        ", numOfOffices=" + numOfOffices +
        ", suitableForRetail=" + suitableForRetail +
        '}';
  }
}
