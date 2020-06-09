
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        for (int i = 0; i < quakes.size() - 1; i++){
         if (quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()){
             return false;
         }
        }
        return true;
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
     
     int maxIdx = from; 
     for (int i = from+1; i < quakeData.size(); i++){
         if (quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()){
          maxIdx = i;   
         }
     }
     return maxIdx;
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
     for (int i = 0; i < quakeData.size() - 1 - numSorted; i++){
         if (quakeData.get(i).getMagnitude() > quakeData.get(i+1).getMagnitude()){
             QuakeEntry qMax = quakeData.get(i);
             QuakeEntry qMin = quakeData.get(i+1);
             quakeData.set(i, qMin);
             quakeData.set(i+1, qMax);
            }
         }
     }   
    
     public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
         int passes = 0;
         for (int i = 0; i < in.size()-1; i++){
             onePassBubbleSort(in, i);
             passes = i + 1;
         }
         System.out.println("Number of passes needed: " + passes);
     }
    
     public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
         int passes = 0;
         for (int i = 0; i < in.size()-1; i++){
             if (checkInSortedOrder(in)) {
                 passes = i;
                 break;
             }
             onePassBubbleSort(in, i);
             passes = i + 1;
         }
         System.out.println("Number of passes needed: " + passes);
     }
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
     //This is called selection sort (in place swapping)
        //for (int i = 0 ; i < in.size(); i++){
        for (int i = 0; i < 50; i++){
            int maxIdx = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i, qmax);
            in.set(maxIdx, qi);
        }
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
       int passes = 0;
       for (int i=0; i< in.size(); i++) {
           if (checkInSortedOrder(in)) {
                 passes = i;
                 break;
             }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            passes = i + 1;
        }
       System.out.println("Number of passes needed: " + passes);
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithCheck(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
