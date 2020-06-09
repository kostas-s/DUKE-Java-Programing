import java.util.Random;
import java.util.*;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int num;
    
    public MarkovModel(int n) {
        myRandom = new Random();
        num = n;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String> followList = new ArrayList<String>();
        int idx = myText.indexOf(key);
        while (idx != -1){
            if (idx + key.length() + 1 > myText.length()){
                break;
            }
            String addString = myText.substring(idx+key.length() , idx+key.length()+1);
            followList.add(addString);
            idx = myText.indexOf(key, idx+1);
        }
        return followList;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - num);
        String key = myText.substring(index, index+num);
        sb.append(key);
        
        for(int k=0; k < numChars-num; k++){
         ArrayList<String> follows = getFollows(key);
         //System.out.println("key " + key + " " + follow);
         if (follows.size() == 0){
             break;
         }
         index = myRandom.nextInt(follows.size());
         String next = follows.get(index);
         System.out.println("index : " + index + ", Key to append: " + key);
         sb.append(next);
         key = key.substring(1) + next;
        }

        return sb.toString();
    }
}
