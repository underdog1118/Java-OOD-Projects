/**
 * Class Property is an abstract class, it has address and size.
 */
package problem1;

import java.util.Objects;

public abstract class Property {
  private String address;
  private Integer size;

  /**
   * Constructor of class Property
   * @param address - the address of the properties, as a String
   * @param size - the size of the properties, as an Integer
   */
  public Property(String address, Integer size) {
    this.address = address;
    this.size = this.checkNonNegative(size, 0);
  }

  /**
   * A Generics helper method to check if number is smaller than ZERO
   * @param number - the given number
   * @param zero - the given format of ZERO
   * @return - the size if it's non-negative
   * @param <T> - the given type
   */
  protected <T extends Comparable<T>> T checkNonNegative(T number, T zero){
    if(number.compareTo(zero) < 0){
      throw new IllegalArgumentException("The given value should not be negative.");
    }
    return number;
  }

  /**
   * Return the address of the property
   * @return - address, as a String
   */
  public String getAddress() {
    return address;
  }
  /**
   * Return the size of the property
   * @return - size, as an Integer
   */
  public Integer getSize() {
    return size;
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
    Property property = (Property) o;
    return Objects.equals(address, property.address) && Objects.equals(size,
        property.size);
  }

  /**
   * @return the hashcode of the Property
   */
  @Override
  public int hashCode() {
    return Objects.hash(address, size);
  }

  /**
   * @return a String of the information about the Property
   */
  @Override
  public String toString() {
    return "address='" + address + '\'' +
        ", size=" + size;
  }
}
