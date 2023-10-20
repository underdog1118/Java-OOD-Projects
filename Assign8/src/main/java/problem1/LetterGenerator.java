package problem1;

import java.util.Objects;

/**
 * This class generates emails for insurance company members to the given filePath
 * based on a given template file and a CSV file containing member information.
 */
public class LetterGenerator extends Generator{
  private final String PREFIX = "Letters";

  /**
   Constructor for the Generator class.
   @param fileName the name of the template file.
   @param folderName the name of the folder where the generated files will be saved.
   @param filePath the name of the csv file
   */
  public LetterGenerator(String fileName, String folderName, String filePath) {
    super(fileName, folderName, filePath);
    super.filePrefix = this.PREFIX;
  }

  /**
   * @return the filePrefix, as String
   */
  public String getPREFIX() {
    return PREFIX;
  }
  /**
   * @param o - the given object
   * @return true if equals, false otherwise
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
    LetterGenerator that = (LetterGenerator) o;
    return Objects.equals(PREFIX, that.PREFIX);
  }
  /**
   * @return the hashcode of the object, as an integer
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), PREFIX);
  }
  /**
   * @return the information of the LetterGenerator, as a String
   */
  @Override
  public String toString() {
    return "LetterGenerator{" +
        "PREFIX='" + PREFIX + '\'' +
        ", " + super.toString();
  }
}