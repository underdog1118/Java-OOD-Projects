package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class TemplateReaderTest {
  private TemplateReader testTemplateReader;

  @TempDir
  File testDir;
  File testTemplate;

  @BeforeEach
  void setUp() throws IOException {
    testTemplate = new File(testDir, "test_template.txt");
    testTemplate.createNewFile();
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(testTemplate));
      writer.write("[[company_name]]");
      writer.write(" is the company's name.\n");
      writer.write("[[email]]");
      writer.write(" is the company's email.\n");
      writer.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    testTemplateReader = new TemplateReader(testTemplate.getAbsolutePath());
  }
  @Test
  void testWrongFilePath(){
    // template does not exist
    // the NoSuchFileException | FileNotFoundException would be caught.
    TemplateReader templateReader = new TemplateReader("non-existing-template.txt");
  }

  @Test
  void testGetTemplateName(){
    assertEquals(testTemplate.getAbsolutePath(), testTemplateReader.getTemplateName());
  }

  @Test
  void testGetTemplate(){
    assertEquals("[[company_name]] is the company's name.", testTemplateReader.getTemplate().get(0));
    assertEquals("[[email]] is the company's email.", testTemplateReader.getTemplate().get(1));
  }

  @Test
  void testEquals(){
    assertFalse(testTemplateReader.equals(null));
    assertFalse(testTemplateReader.equals(testTemplate));
    assertTrue(testTemplateReader.equals(testTemplateReader));
    TemplateReader expectedTemplateReader = new TemplateReader(testTemplate.getAbsolutePath());
    assertTrue(testTemplateReader.equals(expectedTemplateReader));
  }

  @Test
  void testHashCode(){
    TemplateReader expectedTemplateReader = new TemplateReader(testTemplate.getAbsolutePath());
    Integer expectedValue = Objects.hashCode(expectedTemplateReader);
    assertEquals(expectedValue, testTemplateReader.hashCode());
  }

  @Test
  void testToString(){
    String expectedString = "TemplateReader{"
        + "template=["
        + testTemplateReader.getTemplate().get(0) + ", "
        + testTemplateReader.getTemplate().get(1) + "], "
        + "templateName='"
        + testTemplateReader.getTemplateName()
        + "'}";
    assertEquals(expectedString, testTemplateReader.toString());

  }
}