import java.util.*;
import edu.duke.*;

public class CodonCount {
    private HashMap<String, Integer> map;
    
    public CodonCount(){
     map = new HashMap<String, Integer>();   
    }
    
    private void buildCodonMap(int start, String dna){
        for (int i = start; i < dna.length()-3; i+=3){
         String codon = dna.substring(i, i+3);
         if (!map.containsKey(codon)){
            map.put(codon, 1);
        } else {
            map.put(codon, map.get(codon)+1);
        }
            
       }
    }
    
    private String getMostCommonCodon(){
     String result = null;
     int maxCount = 0;
        for (String codon : map.keySet()){
            if (map.get(codon) > maxCount){
                result = codon;
                maxCount = map.get(codon);
            }
        }
     return result;
    }
    
    private void printCodonCounts(int start, int end){
        for (String codon : map.keySet()){
            if (map.get(codon)>=start && map.get(codon)<=end){
                System.out.println(codon + "\t" + map.get(codon));
            }
        }
    
        
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        String dna = (fr.asString()).toUpperCase();
        
        for (int i=0; i<3; i++){
            map.clear();
            buildCodonMap(i, dna);
            System.out.println("========================");
            System.out.println("Number of unique codons : \n" + map.size() + "\n");
            System.out.println("Most common codon : " + getMostCommonCodon() + "\ncount : " + map.get(getMostCommonCodon()) + "");
            printCodonCounts(1, 7);
        }
        
        
    
    }
}
    
 
