import java.util.*;
import edu.duke.*;
import java.io.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        //Location tokyo = new Location(35.42, 139.43);
        //Location denver = new Location(39.7392, -104.9903);
        
        //Filter f1 = new DistanceFilter(denver, 1000000);
        //Filter f2 = new PhraseFilter("end", "a");
        Filter f1 = new MagnitudeFilter(3.5, 4.5);
        Filter f2 = new DepthFilter(-55000.0, -20000.0);
        ArrayList<QuakeEntry> filteredList  = filter(list, f1);
        ArrayList<QuakeEntry> filteredList2 = filter(filteredList, f2);
        for (QuakeEntry qe: filteredList2) { 
            System.out.println(qe);
        } 
        System.out.println(filteredList2.size() + " quake records match the filter criteria");
    }
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);   
        // for (QuakeEntry qe : list){
            // System.out.println(qe);
        // }
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(1.0, 4.0);
        Filter f2 = new DepthFilter(-180000.0, -30000.0);
        Filter f3 = new PhraseFilter("any", "o");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> filteredList = filter(list, maf);
        
        for (QuakeEntry qe: filteredList){
            System.out.println(qe);
        }
        System.out.println("Filters used are : \n" + maf.getName());
        System.out.println(filteredList.size() + " quake records match the filter criteria");
    }
    
    public void findClosestToGreece(double magMin, double magMax){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        Location athens = new Location(37.983810, 23.727539);
        Filter f1 = new DistanceFilter(athens, 1000*1000);
        Filter f2 = new MagnitudeFilter(magMin, magMax);
        maf.addFilter(f1);
        maf.addFilter(f2);
        ArrayList<QuakeEntry> filteredList = filter(list, maf);
        for (QuakeEntry qe: filteredList){
            System.out.println(qe);
        }
        System.out.println("Filters used are : \n" + maf.getName());
        System.out.println(filteredList.size() + " quake records match the filter criteria");
        try (PrintWriter writer = new PrintWriter(new File("test.csv"))){
            StringBuilder sb = new StringBuilder();
            for (QuakeEntry qe : filteredList){
                sb.append(qe.getLocation().getLatitude());
                sb.append(",");
                sb.append(qe.getLocation().getLongitude());
                sb.append(",");
                sb.append(qe.getMagnitude());
                sb.append(",");
                sb.append(qe.getDepth());
                sb.append("\n");
            }
            writer.write(sb.toString());
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);   
        // for (QuakeEntry qe : list){
            // System.out.println(qe);
        // }
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        
        //Location tulsa = new Location(36.1314, -95.9372);
        Location billund = new Location(55.7308, 9.1153);
        Filter f1 = new MagnitudeFilter(0.0, 5.0);
        Filter f2 = new DistanceFilter(billund, 3000000);
        Filter f3 = new PhraseFilter("any", "e");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> filteredList = filter(list, maf);
        
        for (QuakeEntry qe: filteredList){
            System.out.println(qe);
        }
        System.out.println("Filters used are : \n" + maf.getName());
        System.out.println(filteredList.size() + " quake records match the filter criteria");
    }
    
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
