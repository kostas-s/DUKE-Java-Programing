import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
String source;
    
    public EarthQuakeClient(String sourceFile) {
        source = sourceFile;
        // TODO Auto-generated constructor stub
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // where can be "start" "end" or "any"
        for (QuakeEntry qe : quakeData){
         String currInfo = qe.getInfo();
         switch (where){
            case "any": {
                if (currInfo.indexOf(phrase)!=-1){
                answer.add(qe);
                continue;
                }
            }
            case "end": {
                int phraseLen = phrase.length();
                if (currInfo.endsWith(phrase)){
                answer.add(qe);
                continue;
                }    
            }
            case "start": {
                if (currInfo.indexOf(phrase)==0){
                answer.add(qe);
                continue;
                }
            }
            }
            
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
                                               double minDepth, double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData){
         double currDepth = qe.getDepth();
         if (currDepth > minDepth && currDepth < maxDepth){
             answer.add(qe);
            }
            
        }
                                                   
        return answer;                                      
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData){
            if (qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData){
         Location loc = qe.getLocation();
         System.out.println(loc.distanceTo(from));
         if (from.distanceTo(loc) < distMax){
             answer.add(qe);
         }   
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        double magMin = 5.0;
        ArrayList<QuakeEntry> filteredList = filterByMagnitude(list, magMin);
        
        for (QuakeEntry qe : filteredList){
            System.out.println(qe);
        }
        System.out.println("Found " + filteredList.size() + " entries with magnitude higher than " + magMin);

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        //This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        ArrayList<QuakeEntry> filteredList = filterByDistanceFrom(list, 1000000.0, city);
        
        for (QuakeEntry qe : filteredList){
            Location loc = qe.getLocation();
            double distance = loc.distanceTo(city)/1000;
            System.out.println(distance + " " + qe.getInfo());
        }
        System.out.println("found " + filteredList.size() + " entries that matched the criteria");
        // This location is Bridgeport, CA
        // Location city =  new Location(38.17, -118.82);
    }
    
    public void quakesOfDepth(double min, double max){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> filteredList = filterByDepth(list, min, max);
        for (QuakeEntry qe : filteredList) {
            System.out.println(qe);
        }
        System.out.println("Found " + filteredList.size() + " quakes that match the criteria");
    }
    
    public void quakesByPhrase(String where, String phrase){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        //String phrase = "Explosion";
        //String where = "start";
        
        ArrayList<QuakeEntry> filteredList = filterByPhrase(list, where, phrase);
        for (QuakeEntry qe : filteredList) {
            System.out.println(qe);
        }
        System.out.println("Found " + filteredList.size() + " quakes that match " + phrase + " at " + where);
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
