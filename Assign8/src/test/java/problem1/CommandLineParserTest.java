package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandLineParserTest {

  private String[] testArgs1;
  private String[] testArgs2;
  private String[] testArgs3;
  private CommandLineParser testCommandLineParser1;
  private CommandLineParser testCommandLineParser2;
  private CommandLineParser testCommandLineParser3;
  static final String DEFAULT_MESSAGE =
      """


          Usage:
          --email Generate email messages. If this option is provided, then -- email-template must also be provided.
          --email-template <path/to/file> A filename for the email template. --letter Generate letters. If this option is provided, then --letter- template must also be provided.
          --letter-template <path/to/file> A filename for the letter template. --output-dir <path/to/folder> The folder to store all generated files. This option is required.
          --csv-file <path/to/folder> The CSV file to process. This option is required.

          Examples:
          --email --email-template email-template.txt --output-dir emails -- csv-file customer.csv
          --letter --letter-template letter-template.txt --output-dir letters - -csv-file customer.csv""";
  @BeforeEach
  void setUp() throws InvalidArgumentsException {
    String arg1 = "--email --email-template email-template.txt --letter --letter-template letter-template.txt --csv-file insurance-company-members.csv --output-dir emails_and_letters";
    testArgs1 = arg1.split(" ");
    testCommandLineParser1 = new CommandLineParser(testArgs1);
    String arg2 = "--email --email-template email-template.txt  --csv-file insurance-company-members.csv --output-dir emails_and_letters";
    testArgs2 = arg2.split(" ");
    testCommandLineParser2 = new CommandLineParser(testArgs2);
    String arg3 = "--email --csv-file insurance-company-members.csv --output-dir emails_and_letters";
    testArgs3 = arg3.split(" ");
    Exception InvalidArgumentsException = assertThrows(Exception.class, () -> testCommandLineParser3 = new CommandLineParser(testArgs3));
  }

  @Test
  void processArgs() {
  }

  @Test
  void checkRequiredArgs() throws InvalidArgumentsException {
    String[] testArgs4;
    String arg4 = "--email --email-template email-template.txt --letter --letter-template letter-template.txt --csv-file insurance-company-members.csv";
    testArgs4 = arg4.split(" ");
    Exception InvalidArgumentsException = assertThrows(Exception.class, () -> testCommandLineParser3.checkRequiredArgs());
    Exception InvalidArgumentsException2 = assertThrows(Exception.class, () -> testCommandLineParser3 = new CommandLineParser(testArgs4));
    String[] testArgs5;
    String arg5 = "--email --output-dir emails_and_letters";
    testArgs5 = arg5.split(" ");
    Exception InvalidArgumentsException3 = assertThrows(Exception.class, () -> testCommandLineParser3 = new CommandLineParser(testArgs5));
    String[] testArgs6;
    String arg6 = "--email --email-template email-template.txt --letter --csv-file insurance-company-members.csv --output-dir emails_and_letters";
    testArgs6 = arg6.split(" ");
    Exception InvalidArgumentsException4 = assertThrows(Exception.class, () -> testCommandLineParser3 = new CommandLineParser(testArgs6));
  }



  @Test
  void getLetterRequired() {
    assertFalse(testCommandLineParser2.getLetterRequired());
  }

  @Test
  void getEmailRequired() {
    assertTrue(testCommandLineParser1.getEmailRequired());
  }

  @Test
  void getEmailTemplate() {
    assertEquals("email-template.txt", testCommandLineParser1.getEmailTemplate());
  }

  @Test
  void getLetterTemplate() {
    assertEquals("letter-template.txt", testCommandLineParser1.getLetterTemplate());
  }

  @Test
  void getOutputDir() {
    assertEquals("emails_and_letters", testCommandLineParser1.getOutputDir());
  }

  @Test
  void getCsvFile() {
    assertEquals("insurance-company-members.csv", testCommandLineParser1.getCsvFile());
  }

  @Test
  void testEquals() {
    assertEquals(testCommandLineParser1,testCommandLineParser1);
    assertNotEquals(testCommandLineParser1,testCommandLineParser2);
  }

  @Test
  void testHashCode() {
    assertEquals(testCommandLineParser1.hashCode(), testCommandLineParser1.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "CommandLineParser{letter=true, email=true,"
        + " emailTemplate='email-template.txt', letterTemplate='letter-template.txt',"
        + " outputDir='emails_and_letters', csvFile='insurance-company-members.csv'}";
    assertEquals(expectedString, testCommandLineParser1.toString());
  }
}