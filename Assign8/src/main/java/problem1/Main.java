package problem1;

import java.io.IOException;
import java.util.List;

/**
 * Main function: Receives input, Triggers parsing of input
 * Triggers processing of parsed input, Prints results
 */
public class Main {
  public static void main(String[] args) {
    // args:
    // --email --email-template email-template.txt --letter --letter-template letter-template.txt
    // --output-dir emails_and_letters --csv-file insurance-company-members.csv

    try {
      CommandLineParser parser = new CommandLineParser(args);
      String folder = parser.getOutputDir();
      String csvPath = parser.getCsvFile();

      // if there is an --email option
      if (parser.getEmailRequired()) {
        String emailFileName = parser.getEmailTemplate();
        EmailGenerator email = new EmailGenerator(emailFileName, folder, csvPath);
        email.writeLetters();
      }
      // if there is a --letter option
      if (parser.getLetterRequired()) {
        String letterFileName = parser.getLetterTemplate();
        LetterGenerator letter = new LetterGenerator(letterFileName, folder, csvPath);
        letter.writeLetters();
      }
    } catch (InvalidArgumentsException e) {
      e.printStackTrace();
    }
  }
}