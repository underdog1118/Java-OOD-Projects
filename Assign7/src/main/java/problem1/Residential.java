/**
 * Class Residential is the child class of class Property,
 * it has numOfBedrooms and numOfBathrooms.
 */
package problem1;

import java.util.Objects;

public class Residential extends Property{
  private Integer numOfBedrooms;
  private Double numOfBathrooms;

  /**
   * Constructor of class Residential
   * @param address - the address of the properties, as a String
   * @param size - the size of the properties, as an Integer
   * @param numOfBedrooms - the number of bedrooms, as an Integer
   * @param numOfBathrooms - the number of bathrooms, as a Double
   */
  public Residential(String address, Integer size, Integer numOfBedrooms, Double numOfBathrooms) {
    super(address, size);
    this.numOfBedrooms = super.checkNonNegative(numOfBedrooms, 0);
    this.numOfBathrooms = super.checkNonNegative(numOfBathrooms, 0.0);
  }

  /**
   * Return the number of bedrooms of the residential property
   * @return - the number of bedrooms, as an Integer
   */
  public Integer getNumOfBedrooms() {
    return numOfBedrooms;
  }

  /**
   * Return the number of bathrooms of the residential property
   * @return - the number of bathrooms, as a Double
   */
  public Double getNumOfBathrooms() {
    return numOfBathrooms;
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
    Residential that = (Residential) o;
    return Objects.equals(numOfBedrooms, that.numOfBedrooms) && Objects.equals(
        numOfBathrooms, that.numOfBathrooms);
  }
  /**
   * @return the hashcode of the Property
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), numOfBedrooms, numOfBathrooms);
  }

  /**
   * @return a String of the information about the Property
   */
  @Override
  public String toString() {
    return "Residential{" +
        super.toString() +
        ", numOfBedrooms=" + numOfBedrooms +
        ", numOfBathrooms=" + numOfBathrooms +
        '}';
  }
}
