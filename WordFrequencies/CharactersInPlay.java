import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> myNames;
    private ArrayList<Integer> myCount;
    
    public CharactersInPlay(){
     myNames = new ArrayList<String>();
     myCount = new ArrayList<Integer>();
    }
    
    private void update(String person){
     int idx = myNames.indexOf(person.toLowerCase());
     
     if (idx == -1){
         myNames.add(person.toLowerCase());
         myCount.add(1);
        } else {
         int currCount = myCount.get(idx);
         myCount.set(idx, currCount+1);
        }
        
    }
    
    private void findAllCharacters(){
     FileResource fr = new FileResource();   
      
     for (String line : fr.lines()){
         int idxDot = line.indexOf('.');
         if (idxDot != -1){
             String name = line.substring(0,idxDot);
             update(name);
         }
     }
    }
    
    private void charactersWithNumParts(int num1, int num2){
     // assume num1 <= num2
     System.out.println("Printing out names with counts between (including) : " + num1 + " and " +num2);
     for (int i = 0; i < myNames.size(); i++){
        if (myCount.get(i) >= num1 && myCount.get(i) <= num2){
          System.out.println(myNames.get(i) + "\t count: " + myCount.get(i));
        } 
     } 
    }

    public void tester(){
     findAllCharacters();
     for (int i = 0; i < myNames.size(); i++){
        if (myCount.get(i) > 30){
         System.out.println(myNames.get(i) + "\t count: " + myCount.get(i));
        }
     }
     charactersWithNumParts(10, 15);
    }
    
}
