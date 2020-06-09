import java.util.*;
import java.io.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices){
            char currChar = message.charAt(i);
            sb.append(currChar);
        }
        return sb.toString();
    }
    
    public HashSet<String> readDictionary(FileResource fr){
     HashSet<String> hSet = new HashSet<String>();
     for (String line : fr.lines()){
         hSet.add(line.toLowerCase());
     }
     return hSet;   
    }
        
    public int countWords(String message, HashSet<String> dictionary){
     String[] wordsArray = message.split("\\W+");
     int count = 0;
     for (int i = 0; i < wordsArray.length; i++){
         if (dictionary.contains(wordsArray[i].toLowerCase())){
             count++;
         }
     }
     return count;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        //Work on each idividual slice to find it's key
        for (int i = 0; i < klength; i++){
         String slice = sliceString(encrypted, i, klength);
         key[i] = cc.getKey(slice);
        }
        return key;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        int maxCount = 0;
        String bestDecrypted = null;
        String bestLang = null;
        
        for (String lang : languages.keySet()){
        String decrypted = breakForLanguage(encrypted, languages.get(lang));
        int currCount = countWords(decrypted, languages.get(lang));
        if (currCount > maxCount){
            maxCount = currCount;
            bestDecrypted = decrypted;
            bestLang = lang;
        }   
       }
       System.out.println("Correct words for best match: " + maxCount);
       System.out.println("Best language match: " + bestLang);
       System.out.println("DECRYPTED MESSAGE FOLLOWS: \n\n" + bestDecrypted);
        
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int maxKeyLength = 100;
        int bestKeyLength = 0;
        char mostCommonChar = mostCommonCharIn(dictionary);
        int maxCount = 0;
        String bestDecrypted = null;
        
        for (int i = 1 ; i < 100; i++){
            int [] key = tryKeyLength(encrypted, i, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int currCount = countWords(decrypted, dictionary);
            if (currCount > maxCount){
                maxCount = currCount;
                bestDecrypted = decrypted;
                bestKeyLength = i;
            }
        }
        System.out.println("Max number of valid words : " +maxCount);
        System.out.println("Key length with most valid words : " + bestKeyLength);
        return bestDecrypted;
    }

    public void breakVigenere () {
        HashMap<String, HashSet<String>> langMap = new HashMap<String, HashSet<String>>();
        FileResource fr = new FileResource();
        String message = fr.asString();
        DirectoryResource drDict = new DirectoryResource();
        
       for (File f : drDict.selectedFiles()){
        System.out.println("Reading dictionary: " + f.getName());
        FileResource frDict = new FileResource(f);
        HashSet<String> dictionary = readDictionary(frDict);
        langMap.put(f.getName(), dictionary);
        System.out.println("-SUCCESSFULLY IMPORTED-");
       }
        //int[] key = tryKeyLength(message, keyLength, 'e');
        //VigenereCipher vc  = new VigenereCipher(key);
        //String decrypted = breakForLanguage(message, dictionary);
        
        breakForAllLangs(message, langMap);
        //System.out.println(decrypted);
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
    HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
      for (String currWord : dictionary){
       for (int i = 0; i < currWord.length(); i++){
           char currChar = Character.toLowerCase(currWord.charAt(i));
           if (!charMap.containsKey(currChar)){
               charMap.put(currChar, 1);
           } else {
               charMap.put(currChar, charMap.get(currChar) + 1);
           }     
      }
    }
    // now find the most occurring character and return it
    int maxCount = 0;
    char maxChar = 0;
      for (Character currChar : charMap.keySet()){
          if (charMap.get(currChar) > maxCount){
              maxCount = charMap.get(currChar);
              maxChar = currChar;
          }
      }
    return maxChar;
   }
}
