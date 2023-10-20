package problem1;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Processes and validates command line arguments. This class doesn't *do* anything with the values provided by the user
 * beyond initial validation. It is another class' responsibility to determine what to do with the user input.
 */
public class CommandLineParser {
  public static final String EMAIL = "--email";
  public static final String EMAIL_TEMPLATE = "--email-template";
  public static final String LETTER = "--letter";
  public static final String LETTER_TEMPLATE = "--letter-template";
  public static final String OUTPUT_DIR = "--output-dir";
  public static final String CSV_FILE = "--csv-file";
  public static final String DEFAULT_MESSAGE =
      """
          
          
          Usage:
          --email Generate email messages. If this option is provided, then -- email-template must also be provided.
          --email-template <path/to/file> A filename for the email template. --letter Generate letters. If this option is provided, then --letter- template must also be provided.
          --letter-template <path/to/file> A filename for the letter template. --output-dir <path/to/folder> The folder to store all generated files. This option is required.
          --csv-file <path/to/folder> The CSV file to process. This option is required.

          Examples:
          --email --email-template email-template.txt --output-dir emails -- csv-file customer.csv
          
          --letter --letter-template letter-template.txt --output-dir letters - -csv-file customer.csv""";

  private Boolean letterRequired = false;
  private Boolean emailRequired = false;
  private String emailTemplate = null;
  private String letterTemplate = null;
  private String outputDir = null;
  private String csvFile = null;

  /**
   * Constructor for the CommandLineParser class
   * @param args The arguments provided by the user.
   * @throws InvalidArgumentsException If the arguments supplied are invalid.
   */
  public CommandLineParser(String[] args) throws InvalidArgumentsException {
    this.processArgs(args);
    this.checkRequiredArgs();
  }

  /**
   * Helper method to populate the instance variables.
   * @param args The args supplied by the user.
   * @throws InvalidArgumentsException If an invalid argument is provided at the command line.
   */
  public void processArgs(String[] args) throws  InvalidArgumentsException{
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {  //enhanced switch
        case EMAIL -> this.emailRequired = true;
        case EMAIL_TEMPLATE -> this.emailTemplate = ((i + 1) < args.length) ? args[i + 1] : null;
        case LETTER -> this.letterRequired = true;
        case LETTER_TEMPLATE -> this.letterTemplate = ((i + 1) < args.length) ? args[i + 1] : null;
        case OUTPUT_DIR -> this.outputDir = ((i + 1) < args.length) ? args[i + 1] : null;
        case CSV_FILE -> this.csvFile = ((i + 1) < args.length) ? args[i + 1] : null;
      }
    }
  }

  /**
   * check that some options require other options to also be present.
   * @throws InvalidArgumentsException if required options are not provided
   */
  public void checkRequiredArgs() throws InvalidArgumentsException{
    if (this.csvFile == null) {
      throw new InvalidArgumentsException("Error: --csv-file is required.");
    }
    if (this.outputDir == null) {
      throw new InvalidArgumentsException("Error: --output-dir is required.");
    }
    if (this.emailRequired && this.emailTemplate == null) {
      throw new InvalidArgumentsException("Error: --email provided but no --email-template was given." + DEFAULT_MESSAGE);
    }
    if (this.letterRequired && this.letterTemplate == null) {
      throw new InvalidArgumentsException("Error: --letter provided but no --letter-template was given." + DEFAULT_MESSAGE);
    }

  }

  /**
   * Gets the value of letter.
   *
   * @return the value of letter
   */
  public Boolean getLetterRequired() {
    return this.letterRequired;
  }

  /**
   * Gets the value of email.
   *
   * @return the value of email
   */
  public Boolean getEmailRequired() {
    return this.emailRequired;
  }

  /**
   * Gets the value of emailTemplate.
   *
   * @return the value of emailTemplate
   */
  public String getEmailTemplate() {
    return this.emailTemplate;
  }

  /**
   * Gets the value of letterTemplate.
   *
   * @return the value of letterTemplate
   */
  public String getLetterTemplate() {
    return this.letterTemplate;
  }

  /**
   * Gets the value of outputDir.
   *
   * @return the value of outputDir
   */
  public String getOutputDir() {
    return this.outputDir;
  }

  /**
   * Gets the value of csvFile.
   *
   * @return the value of csvFile
   */
  public String getCsvFile() {
    return this.csvFile;
  }

  /**
   * equals method for the class
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommandLineParser that = (CommandLineParser) o;
    return Objects.equals(letterRequired, that.letterRequired) && Objects.equals(emailRequired,
        that.emailRequired) && Objects.equals(emailTemplate, that.emailTemplate)
        && Objects.equals(letterTemplate, that.letterTemplate) && Objects.equals(
        outputDir, that.outputDir) && Objects.equals(csvFile, that.csvFile);
  }

  /**
   * hashCode method for the class
   */
  @Override
  public int hashCode() {
    return Objects.hash(letterRequired, emailRequired, emailTemplate, letterTemplate, outputDir, csvFile);
  }

  /**
   * toString method for the class
   */
  @Override
  public String toString() {
    return "CommandLineParser{" +
        "letter=" + letterRequired +
        ", email=" + emailRequired +
        ", emailTemplate='" + emailTemplate + '\'' +
        ", letterTemplate='" + letterTemplate + '\'' +
        ", outputDir='" + outputDir + '\'' +
        ", csvFile='" + csvFile + '\'' +
        '}';
  }
}
