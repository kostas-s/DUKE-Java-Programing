import edu.duke.*;
import java.util.*;
import java.io.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedList;
    private ArrayList<String> usedCategoryList;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color", "country", "name",
                            "animal", "timeframe", "verb", "fruit"};
                            
        for (int i = 0; i < categories.length; i++){
            ArrayList<String> temp = readIt(source+"/" + categories[i] + ".txt");
            myMap.put(categories[i], temp);
        }
        usedList = new ArrayList<String>();
        usedCategoryList = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
                    
         return ""+myRandom.nextInt(50)+5;
        }
        
        // add to list of used categories to use in totalWordsConsidered method
        if (!usedCategoryList.contains(label)){
            usedCategoryList.add(label);
        }
     
        if (myMap.containsKey(label)){
         return randomFrom(myMap.get(label));
        }
     
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while (usedList.indexOf(sub)!=-1){
        sub = getSubstitute(w.substring(first+1,last));
        }
        usedList.add(sub);   
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap(){
     int count = 0;
     for (String key : myMap.keySet()){
         ArrayList<String> list = myMap.get(key);
         count += list.size();         
     }     
     return count;
    }
    
    private int totalWordsConsidered(){
        int count = 0;
        
        for (int i = 0; i < usedCategoryList.size(); i++){
            String category = usedCategoryList.get(i);
            ArrayList<String> list = myMap.get(category);
            count += list.size();
        }
        return count;
    }
    
    public void makeStory(){
        usedList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 60);
        System.out.println("\n\n" + usedList.size() + " words were replaced to make this story");
        int wordOptions = totalWordsInMap();
        System.out.println(wordOptions + " total words available for subtitution");
        
        int wordUsedOptions = totalWordsConsidered();
        System.out.println(wordUsedOptions + " total words considered (from used categories)");
    }
    


}