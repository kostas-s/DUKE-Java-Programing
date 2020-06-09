
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    private int indexOf(String[] words, String target1, String target2, int start){
     for (int i = start; i < words.length-1; i++){
         if (words[i].equals(target1)&&words[i+1].equals(target2)){
             return i;
         }
     }
     return -1;
    }
    
    public void testIndexOf(){
     String text = "this is just a test yes this is a simple test";
     String[] words = text.split("\\s");
     // System.out.println("this starting at 0 :" + indexOf(words, "this", 0));
     // System.out.println("this starting at 3 :" + indexOf(words, "this", 3));
     // System.out.println("frog starting at 0 :" + indexOf(words, "frog", 0));
     // System.out.println("frog starting at 5 :" + indexOf(words, "frog", 5));
     // System.out.println("simple starting at 2 :" + indexOf(words, "simple", 2));
     // System.out.println("test starting at 11 :" + indexOf(words, "test", 11));
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        sb.append(key1);
        sb.append(" ");
        String key2 = myText[index+1];
        sb.append(key2);
        sb.append(" ");
        
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            //System.out.println(key1 + " " + key2 + " " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos != -1){
         int next = indexOf(myText, key1, key2, pos);
         if (next == -1 || next + 2 > myText.length -1){
             break;
         }
         next+=2;
         follows.add(myText[next]);
         pos = next;
        }
         return follows;
        }
        
        public String toString(){
         return "MarkovWord of order 2";   
        }
    }
