package problem1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 The abstract class Generator is the parent class for the EmailGenerator and LetterGenerator classes.
 It contains common methods and attributes used by both subclasses.
 */
public abstract class Generator {
  private String fileName;
  private ArrayList<String> template;
  private List<String[]> csvData;
  private String[] pointers;
  private String folderName;
  private String filePath;
  protected String filePrefix;

  /**
   Constructor for the Generator class.
   @param fileName the name of the template file.
   @param folderName the name of the folder where the generated files will be saved.
   @param filePath the name of the csv file
   */
  public Generator(String fileName, String folderName, String filePath) {
    this.fileName = fileName;
    this.folderName = folderName;
    this.filePath = filePath;
    this.template = new TemplateReader(fileName).getTemplate();
    this.csvData = new CSVReader(filePath).readCSV();
    this.pointers = this.csvData.get(0);

  }


  /**
   Method for generating letters or emails.
   */
  public void writeLetters(){
    this.createFolder(this.folderName);
    for(int i = 1; i < csvData.size(); i++){
      String[] info = csvData.get(i);
      if(this.pointers.length == info.length){
        this.writeOneLetter(this.filePrefix, i, info);
      }
    }
  };

  /**
   Helper method of method writeLetters, create the folder where the generated files will be saved.
   @param folderName the name of the folder to be created.
   */
  private void createFolder(String folderName){
    File folder = new File(folderName);
    if(!folder.exists()){
      boolean success = folder.mkdir();
      if(!success){
        System.err.println("Failed to create directory '"+ folderName +"'");
      }
    }
  }

  /**
   Helper method of method writeLetters, generate a single letter or email.
   @param prefix the prefix to be added to the file name.
   @param numLetter the number of the letter or email being generated.
   @param info the data to be used to fill in the template.
   */
  private void writeOneLetter(String prefix, Integer numLetter, String[] info) {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(this.folderName + "/" + prefix + Integer.toString(numLetter) + ".txt"));
      for (int i = 0; i < template.size(); i++) {
        String line = template.get(i);
        String result = this.generateResult(line, info) + "\n";
        writer.write(result);
      }
      writer.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  /**
   Helper method of method writeLetters, fill in the template with data from the CSV file for one line.
   @param line the line of the template to be filled in.
   @param info the data to be used to fill in the template.
   @return the filled-in line of the template.
   */
  private String generateResult(String line, String[] info) {
    Pattern pattern = Pattern.compile("\\[\\[((\\p{Alpha}+)|((\\p{Alpha}+)\\_(\\p{Alpha}+)))\\]\\]");
    Matcher matcher = pattern.matcher(line);
    StringBuffer stringBuffer = new StringBuffer();
    while (matcher.find()) {
      String replacement = "";
      String match = matcher.group(1);
      for(int i = 0; i < info.length; i++) {
        if (match.equals(this.pointers[i])) {
          replacement = info[i];
        }
      }
      matcher.appendReplacement(stringBuffer, replacement);
    }
    matcher.appendTail(stringBuffer);
    return stringBuffer.toString();
  }

  /**
   * Getter for fileName
   * @return - fileName, as String
   */
  public String getFileName() {
    return fileName;
  }
  /**
   * Getter for template
   * @return - template, as ArrayList<String>
   */
  public ArrayList<String> getTemplate() {
    return template;
  }
  /**
   * Getter for csvData
   * @return - csvData, as List<String[]>
   */
  public List<String[]> getCsvData() {
    return csvData;
  }
  /**
   * Getter for pointers
   * @return - pointers, as String[]
   */
  public String[] getPointers() {
    return pointers;
  }
  /**
   * Getter for folderName
   * @return - folderName, as String
   */
  public String getFolderName() {
    return folderName;
  }
  /**
   * Getter for filePath
   * @return - filePath, as String
   */
  public String getFilePath() {
    return filePath;
  }
  /**
   * Getter for filePrefix
   * @return - filePrefix, as String
   */
  public String getFilePrefix() {
    return filePrefix;
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
    Generator generator = (Generator) o;
    // Check if the csvData are equals first
    if(csvData.size() != generator.csvData.size()){
      return false;
    }
    for(int i = 0; i < csvData.size(); i++){
      String[] line1 = csvData.get(i);
      String[] line2 = generator.csvData.get(i);
      // if they have different length:
      if(line1.length != line2.length){
        return false;
      }
      if(!Arrays.equals(line1, line2)){
        return false;
      }
    }
    return Objects.equals(fileName, generator.fileName) &&
        Objects.equals(template, generator.template) &&
        Arrays.equals(pointers, generator.pointers) &&
        Objects.equals(folderName, generator.folderName) &&
        Objects.equals(filePath, generator.filePath) &&
        Objects.equals(filePrefix, generator.filePrefix);
  }

  /**
   *
   * @return the hashcode of the object, as an integer
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(fileName, template, csvData, folderName, filePath, filePrefix);
    result = 31 * result + Arrays.hashCode(pointers);
    return result;
  }
  /**
   * @return the information of the Generator, as a String
   */
  @Override
  public String toString() {
    return "fileName='" + fileName + '\'' +
        ", template=" + template +
        ", csvData=" + csvData +
        ", pointers=" + Arrays.toString(pointers) +
        ", folderName='" + folderName + '\'' +
        ", filePath='" + filePath + '\'' +
        ", filePrefix='" + filePrefix + '\'' +
        '}';
  }
}
