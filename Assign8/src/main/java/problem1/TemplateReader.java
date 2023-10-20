package problem1;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class TemplateReader reads the template with given templateName
 * and save each line of the template in ArrayList<String>
 */
public class TemplateReader {

  private ArrayList<String> template;
  private String templateName;

  /**
   * Constructor of TemplateReader
   * @param templateName, the given templateName, as a String
   */
  public TemplateReader(String templateName) {
    this.template = new ArrayList<>();
    this.templateName = templateName;
    this.readFile();
  }

  /**
   * Method to read the given file, and return the template as an ArrayList<String>
   * @return - the template, as an ArrayList<String>
   */
  public ArrayList<String> getTemplate() {
    return template;
  }

  /**
   * Helper method of getTemplate
   */
  private void readFile(){
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(templateName));
      String line;
      while ((line = reader.readLine()) != null) {
        template.add(line);
      }
    } catch (NoSuchFileException | FileNotFoundException nsf) {
      nsf.printStackTrace();
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Getters for templateName
   * @return templateName, as a String
   */
  public String getTemplateName() {
    return templateName;
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
    TemplateReader that = (TemplateReader) o;
    return Objects.equals(template, that.template) && Objects.equals(templateName,
        that.templateName);
  }
  /**
   *
   * @return the hashcode of the object, as an integer
   */
  @Override
  public int hashCode() {
    return Objects.hash(template, templateName);
  }

  /**
   * @return the information of the TemplateReader, as a String
   */
  @Override
  public String toString() {
    return "TemplateReader{" +
        "template=" + template +
        ", templateName='" + templateName + '\'' +
        '}';
  }
}