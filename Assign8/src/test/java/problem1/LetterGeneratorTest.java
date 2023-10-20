package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class LetterGeneratorTest {

  @TempDir
  File testDir;
  File template;
  File cvsFile;
  String templateDir;
  String folderDir;
  String cvsFileDir;
  LetterGenerator testLetterGenerator;

  @BeforeEach
  void setUp() throws IOException {
    // Create template in testDir
    template = new File(testDir, "test_template.txt");
    template.createNewFile();
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(template));
      writer.write("[[company_name]] is the company's name.\n");
      writer.write("[[email]] is the company's email.\n");
      writer.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    //Create cvsFile in testDir
    cvsFile = new File(testDir, "test_cvs.cvs");
    cvsFile.createNewFile();
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(cvsFile));
      writer.write(
          "\"first_name\",\"last_name\",\"company_name\",\"address\",\"city\",\"county\",\"state\",\"zip\",\"phone1\",\"phone2\",\"email\",\"web\"\n");
      writer.write(
          "\"James\",\"Butt\",\"Benton, John B Jr\",\"6649 N Blue Gum St\",\"New Orleans\",\"Orleans\",\"LA\",\"70116\",\"504-621-8927\",\"504-845-1427\",\"jbutt@gmail.com\",\"http://www.bentonjohnbjr.com\"\n");
      writer.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    // Get the above files' path
    folderDir = testDir.getAbsolutePath();
    templateDir = template.getAbsolutePath();
    cvsFileDir = cvsFile.getAbsolutePath();

    // Construct the testLetterGenerator
    testLetterGenerator = new LetterGenerator(templateDir, folderDir, cvsFileDir);

  }

  @Test
  void testGetPRFIX() {
    assertEquals("Letters", testLetterGenerator.getPREFIX());
  }

  @Test
  void testWriteLetters() {
    testLetterGenerator.writeLetters();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(folderDir + "/Letters1.txt"));
      String firstLine = reader.readLine();
      String secondLine = reader.readLine();
      assertEquals("Benton, John B Jr is the company's name.", firstLine);
      assertEquals("jbutt@gmail.com is the company's email.", secondLine);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void testEquals() {
    assertFalse(testLetterGenerator.equals(null));
    assertFalse(testLetterGenerator.equals(template));
    assertTrue(testLetterGenerator.equals(testLetterGenerator));

    LetterGenerator sameLetterGenerator = new LetterGenerator(templateDir, folderDir, cvsFileDir);
    assertTrue(testLetterGenerator.equals(sameLetterGenerator));

    LetterGenerator diffLetterGenerator = new LetterGenerator(templateDir, folderDir+"/letters", cvsFileDir);
    assertFalse(testLetterGenerator.equals(diffLetterGenerator));
  }

  @Test
  void testGetFileName() {
    assertEquals(templateDir, testLetterGenerator.getFileName());
  }

  @Test
  void testGetTemplate() {
    ArrayList<String> template = testLetterGenerator.getTemplate();
    assertEquals(template.get(0), "[[company_name]] is the company's name.");
    assertEquals(template.get(1), "[[email]] is the company's email.");
  }

  @Test
  void testGetPointers() {
    String[] pointers = testLetterGenerator.getPointers();
    assertEquals(pointers[0], "first_name");
  }

  @Test
  void testHashCode() {
    Integer expectedValue = Objects.hash(testLetterGenerator.getFileName(),
        testLetterGenerator.getTemplate(),
        testLetterGenerator.getCsvData(), testLetterGenerator.getFolderName(),
        testLetterGenerator.getFilePath(), testLetterGenerator.getFilePrefix());
    expectedValue = 31 * expectedValue + Arrays.hashCode(testLetterGenerator.getPointers());
    expectedValue = Objects.hash(expectedValue, testLetterGenerator.getPREFIX());
    assertEquals(expectedValue, testLetterGenerator.hashCode());
  }
}
