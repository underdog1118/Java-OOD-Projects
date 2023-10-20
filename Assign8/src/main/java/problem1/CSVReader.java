package problem1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class reads a CSV file and converts its content into a list of string arrays.
 * Each string array represents a row in the CSV file.
 */
public class CSVReader {

  /**
   * The file path of the CSV file to be read.
   */
  private String filePath;

  /**
   * The list that stores the content of the CSV file as string arrays.
   */
  private List<String[]> data = new ArrayList<>();

  /**
   * Constructor for creating a CSVReader object with the given file path.
   *
   * @param filePath the file path of the CSV file to be read
   */
  public CSVReader(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Reads the CSV file and returns its content as a list of string arrays.
   *
   * @return the content of the CSV file as a list of string arrays
   */
  public List<String[]> readCSV(){
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(filePath));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] row = line.split("\",\"");
        row[0] = row[0].replaceAll("\"", "");
        row[row.length - 1] = row[row.length - 1].replaceAll("\"", "");
        data.add(row);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return data;
  }
  /**
   * Getters for filePath
   * @return filePath, as a String
   */
  public String getFilePath() {
    return filePath;
  }
  /**
   * Getters for data
   * @return data, as a List<String[]>
   */
  public List<String[]> getData() {
    return data;
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
    CSVReader csvReader = (CSVReader) o;
    return Objects.equals(filePath, csvReader.filePath) && Objects.equals(data,
        csvReader.data);
  }
  /**
   *
   * @return the hashcode of the object, as an integer
   */
  @Override
  public int hashCode() {
    return Objects.hash(filePath, data);
  }
  /**
   * @return the information of the CSVReader, as a String
   */
  @Override
  public String toString() {
    return "CSVReader{" +
        "filePath='" + filePath + '\'' +
        ", data=" + data +
        '}';
  }
}