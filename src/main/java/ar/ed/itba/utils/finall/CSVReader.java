package ar.ed.itba.utils.finall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVReader {
  
  public enum Column {
    FILENAME, X, Y, W, H, LICENSE;
  }
  
  public static Map<String,String> read(String csvFile, String separator){
    Map<String,String> map = new HashMap<>();
    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
      String line = br.readLine();
      String[] strings = line.split(separator);
      for (Column c: Column.values()) {
        map.put(c.name(),strings[c.ordinal()]);
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    }
    return map;
  }
  
}
