import java.util.*;
import edu.duke.*;

public class WordFrequencies {

    ArrayList<String> myWords;
    ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
     myWords = new ArrayList<String>();
     myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
    myWords.clear();
    myFreqs.clear();
    
    FileResource fr = new FileResource();
    
    for (String word : fr.words()){
     if (myWords.indexOf(word.toLowerCase()) == -1){
         myWords.add(word.toLowerCase());
         myFreqs.add(1);
        } else {
         int idx = myWords.indexOf(word.toLowerCase());
         int currFreq = myFreqs.get(idx);
         myFreqs.set(idx, currFreq+1);
        }
    }
    }
    
    public void tester(){
     findUnique();   
     System.out.println("Number of unique words is: " + myWords.size());
     
     for (int i=0; i < myWords.size(); i++){
         System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
     }
     
     int idxMax = findIndexOfMax();
     
     System.out.println("Most common word is :" + myWords.get(idxMax) + "\t it occurs : " + myFreqs.get(idxMax) + " times.");
     
    }
    
    private int findIndexOfMax(){
     int currMax = 0;
     int idxMax = 0;
     for (int i = 0; i < myFreqs.size(); i++) {
        if (myFreqs.get(i) > currMax) {
            currMax = myFreqs.get(i);
            idxMax = i;
        }
     }
     return idxMax;
    }
     
    }

    

