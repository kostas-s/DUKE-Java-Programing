import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    
    public WordsInFiles(){
        map = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for (String word : fr.words()){
            if (!map.containsKey(word)){
             ArrayList<String> fileList = new ArrayList<String>();
             fileList.add(f.getName());
             map.put(word, fileList);
            } else {
             int idx = map.get(word).indexOf(f.getName());   
             if (idx == -1){
                 ArrayList<String> fileList = map.get(word);
                 fileList.add(f.getName());
                 map.put(word, fileList);
             }
            }
        }
    }
    
    private void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber(){
     int maxSize = 0;
     for (ArrayList list : map.values()){
        if (list.size() > maxSize){
            maxSize = list.size();
        }
     }
     return maxSize;
    }
    
    private ArrayList<String> wordsInNumFiles(int number){
     ArrayList<String> list = new ArrayList<String>();
        for (String key : map.keySet()){
         if ((map.get(key)).size() == number){
             list.add(key);
         }
        }
     return list;
    }
    
    private void printFilesIn(String word){
     ArrayList<String> list = map.get(word);
     System.out.println(word + " appears in the following files: ");
     for (int i=0; i<list.size(); i++){
         System.out.println(list.get(i));
     }
    }
    
    public void tester(){
        buildWordFileMap();
        int max = maxNumber();
        //int max =4;
        for (String key : map.keySet()){
          System.out.println(key + " " + map.get(key));
        }
        System.out.println("The max number of files a word appears in is :" + max);
        ArrayList<String> list = wordsInNumFiles(max);
        System.out.println("words that appear in " + max + " files are: " + list.size());
        for (int i=0; i<list.size(); i++){
            printFilesIn(list.get(i));
        }
    }
}
