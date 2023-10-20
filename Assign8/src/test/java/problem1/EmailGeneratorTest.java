package problem1;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import static java.text.NumberFormat.Field.PREFIX;
class EmailGeneratorTest {
  private EmailGenerator testEmailGenerator;

  @TempDir
  File testDir;
  File testTemplate;
  File testCSV;
  String testFolderName = "test_folder";
  String templateDir;
  String folderDir;
  String cvsFileDir;

  @BeforeEach
  void setUp() throws IOException {
    testTemplate = new File(testDir, "test_template.txt");
    testTemplate.createNewFile();
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(testTemplate));
      writer.write("Dear [[name]],\n");
      writer.write(
          "We are writing to inform you that your policy number [[policy_number]] has been renewed.\n");
      writer.write("Sincerely,\n");
      writer.write("[[company_name]]");
      writer.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    testCSV = new File(testDir, "test.csv");
    testCSV.createNewFile();
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(testCSV));
      writer.write("\"name\",\"email\",\"policy_number\"\n");
      writer.write("\"Alice\",\"alice@example.com\",\"001\"\n");
      writer.write("\"Bob\",\"bob@example.com\",\"002\"\n");
      writer.write("\"Charlie\",\"charlie@example.com\",\"003\"\n");
      writer.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    folderDir = testDir.getAbsolutePath() + "/" + testFolderName;
    ;
    templateDir = testTemplate.getAbsolutePath();
    cvsFileDir = testCSV.getAbsolutePath();

    testEmailGenerator = new EmailGenerator(templateDir, folderDir, cvsFileDir);
  }

  @Test
  void testGetPrefix() {
    assertEquals("Email", testEmailGenerator.getPREFIX());
  }

  @Test
  void testGetFileName() {
    assertEquals(testTemplate.getAbsolutePath(), testEmailGenerator.getFileName());
  }

  @Test
  void testGetFolderName() {
    String expectedFolderName = testDir.getAbsolutePath() + "/test_folder";
    assertEquals(expectedFolderName, testEmailGenerator.getFolderName());
  }

  @Test
  void testGetCSVFilePath() {
    assertEquals(testCSV.getAbsolutePath(), testEmailGenerator.getFilePath());
  }
  @Test
  public void testEquals() {
    // Create two instances of EmailGenerator with the same properties
    EmailGenerator email1 = new EmailGenerator("email-template.txt", "emails_and_letters", "insurance-company-members.csv");
    email1.writeLetters();
    EmailGenerator email2 = new EmailGenerator("email-template.txt", "emails_and_letters", "insurance-company-members.csv");
    email2.writeLetters();

    // Test that the equals method returns true
    assertTrue(email1.equals(email2));

    // Change a property of email2
    email2 = new EmailGenerator("new-email-template.txt", "emails_and_letters", "insurance-company-members.csv");
    email2.writeLetters();

    // Test that the equals method returns false
    assertFalse(email1.equals(email2));
  }

  @Test
  void testHashCode() {
    Integer expectedValue = Objects.hash(
        testEmailGenerator.getFileName(),
        testEmailGenerator.getTemplate(),
        testEmailGenerator.getCsvData(),
        testEmailGenerator.getFolderName(),
        testEmailGenerator.getFilePath(),
        testEmailGenerator.getFilePrefix()
    );
    expectedValue = 31 * expectedValue + Arrays.hashCode(testEmailGenerator.getPointers());
    expectedValue = Objects.hash(expectedValue, testEmailGenerator.getPREFIX());
    assertEquals(expectedValue, testEmailGenerator.hashCode());
  }
}