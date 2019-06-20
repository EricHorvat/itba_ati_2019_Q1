package ar.ed.itba.utils.finall;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.Rectangle;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OCREngine {
  
  private static OCREngine INSTANCE;
  
  public static OCREngine getInstance(){
    if (INSTANCE == null){
      INSTANCE = new OCREngine();
    }
    return INSTANCE;
  }
  
  private ITesseract tesseract;
  
  private OCREngine() {
    tesseract = new Tesseract();
    tesseract.setOcrEngineMode(3);
    tesseract.setDatapath(System.getenv("TESSDATA_PREFIX"));
  }
  
  public boolean match(File file, Rectangle rect, String text){
    try {
      String ocrResult = tesseract.doOCR(file, rect).replace("-","");
      List<String> stringList = possibleStrings(ocrResult);
      return stringList.stream().anyMatch(s -> s.toUpperCase().contains(text.toUpperCase()));
    }catch (TesseractException e){
      System.err.println(e.toString());
      return false;
    }
  }
  
  private static Map<Character, List<Character>> replacements;
  
  static {
    replacements = new HashMap<>();
    replacements.put('0',List.of('O'));
    replacements.put('1',List.of('I'));
    replacements.put('2',List.of('Z'));
    replacements.put('4',List.of('A'));
    replacements.put('5',List.of('S'));
    replacements.put('6',List.of('G'));
    replacements.put('7',List.of('T'));
    replacements.put('8',List.of('B'));
    replacements.put('A',List.of('4'));
    replacements.put('B',List.of('8'));
    replacements.put('G',List.of('6'));
    replacements.put('I',List.of('1'));
    replacements.put('O',List.of('0'));
    replacements.put('S',List.of('5'));
    replacements.put('T',List.of('7'));
    replacements.put('Z',List.of('2'));
  }
  
  private List<String> possibleStrings(String original){
    List<String> stringList = new ArrayList<>();
    stringList.add(original);
    for (int i = 0; i < original.length(); i++) {
      Character character = original.charAt(i);
      if(replacements.keySet().contains(character)){
        int finalI = i;
        List<String> strings = (replacements
                                .get(character)
                                .stream()
                                .map(replaceCharacter -> stringList.stream().map(s -> {
                                  char[] arr = s.toCharArray();
                                  arr[finalI] = replaceCharacter;
                                  return new String(arr);
                                })))
                                .flatMap(stringStream -> stringStream)
                                .collect(Collectors.toList());
        stringList.addAll(strings);
      }
    }
    
    return stringList;
  }
  
  
}
