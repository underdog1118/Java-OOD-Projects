package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CSVReaderTest {
  private CSVReader testCSVReader;

  @TempDir
  File testDir;
  File testCSV;

  @BeforeEach
  void setUp() throws IOException{
    testCSV = new File(testDir, "test.csv");
    testCSV.createNewFile();
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(testCSV));
      writer.write("\"a\",\"b\",\"c\"\n");
      writer.write("\"1\",\"2\",\"3\"\n");
      writer.write("\"x\",\"y\",\"z\"\n");
      writer.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    testCSVReader = new CSVReader(testCSV.getAbsolutePath());

  }
  @Test
  void testGetFilePath() {
    assertEquals(testCSV.getAbsolutePath(), testCSVReader.getFilePath());
  }

  @Test
  void testGetData() {
    List<String[]> expectedData = new ArrayList<>();
    expectedData.add(new String[]{"a", "b", "c"});
    expectedData.add(new String[]{"1", "2", "3"});
    expectedData.add(new String[]{"x", "y", "z"});

    List<String[]> actualData = testCSVReader.readCSV();

    assertEquals(expectedData.size(), actualData.size());

    for (int i = 0; i < expectedData.size(); i++) {
      assertArrayEquals(expectedData.get(i), actualData.get(i));
    }
  }
  @Test
  void testReadCSV() throws IOException {
    // Create a temporary file with some test data
    File tempFile = File.createTempFile("test", ".csv");
    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
    writer.write("\"a\",\"b\",\"c\"\n");
    writer.write("\"1\",\"2\",\"3\"\n");
    writer.write("\"x\",\"y\",\"z\"\n");
    writer.close();

    // Create a CSVReader object and read the temporary file
    CSVReader reader = new CSVReader(tempFile.getAbsolutePath());
    List<String[]> data = reader.readCSV();

    // Check that the data was read correctly
    assertEquals(3, data.size());
    assertArrayEquals(new String[]{"a", "b", "c"}, data.get(0));
    assertArrayEquals(new String[]{"1", "2", "3"}, data.get(1));
    assertArrayEquals(new String[]{"x", "y", "z"}, data.get(2));

    // Delete the temporary file
    assertTrue(tempFile.delete());
  }


  @Test
  void testEquals() {
    assertFalse(testCSVReader.equals(null));
    assertFalse(testCSVReader.equals(testCSV));
    assertTrue(testCSVReader.equals(testCSVReader));
    CSVReader expectedCSVReader = new CSVReader(testCSV.getAbsolutePath());
    assertTrue(testCSVReader.equals(expectedCSVReader));
  }
  @Test
  void testHashCode() {
    CSVReader expectedCSVReader = new CSVReader(testCSV.getAbsolutePath());
    Integer expectedValue = Objects.hashCode(expectedCSVReader);
    assertEquals(expectedValue, testCSVReader.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "CSVReader{" +
        "filePath='" + testCSVReader.getFilePath() + '\'' +
        ", data=" + Arrays.deepToString(testCSVReader.getData().toArray()) +
        '}';
    assertEquals(expectedString, testCSVReader.toString());
  }
}
