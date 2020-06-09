import java.util.*;
import edu.duke.*;

public class LargestQuakes {
String source;
    
    public LargestQuakes(String sourceFile){
     source = sourceFile;   
    }
    public void findLargestQuakes(int howMany){
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        // CODE TO PRINT OUT THE WHOLE LIST YOU JUST READ
        // for (QuakeEntry qe : list){
            // System.out.println(qe);
        // }
        System.out.println(list.size() + " total quakes read from file");   
        
        int maxIdx = indexOfLargest(list);
        
        // CODE TO PRINT OUT THE LARGEST QUAKE AND ITS INDEX
        // System.out.println("Largest quake found in index : " + maxIdx);
        // System.out.println(list.get(maxIdx));
        
        ArrayList<QuakeEntry> filteredList = getLargest(list, howMany);
        System.out.println(howMany + " Earthquakes of the largest magnitude below:");
        for (QuakeEntry qe : filteredList){
         System.out.println(qe);
        }
        
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int maxIndex = 0;
        double maxSize = 0;
        
        for (int i = 0; i < data.size(); i++){
            double currSize = data.get(i).getMagnitude();
            if (currSize > maxSize){
                maxSize = currSize;
                maxIndex = i;
            }
        }
        return maxIndex;       
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        if (quakeData.size() < howMany){
            howMany = quakeData.size();
        }
        
        for (int i = 0; i < howMany; i++){
         int maxIndex = indexOfLargest(copy);
         answer.add(copy.get(maxIndex));
         copy.remove(maxIndex);
        }
        return answer;
    }
    
}
