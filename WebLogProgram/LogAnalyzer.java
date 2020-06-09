import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()){
          LogEntry le = WebLogParser.parseEntry(line);    
          records.add(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIpList = new ArrayList<String>();
         
         for (LogEntry le : records){
             String ipAddr = le.getIpAddress();
             if (!uniqueIpList.contains(ipAddr)){
                 uniqueIpList.add(ipAddr);
             }
         }
         return uniqueIpList.size();
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public void printAllHigherThanNum(int num){
         for (LogEntry le : records) {
             int status = le.getStatusCode();
             if (status > num){
                 System.out.println(le);
             }
         }
     }
     
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
     // day formatted MMM DD  example: Dec 05    
     ArrayList<String> uniqueIpList = new ArrayList<String>();   
     for (LogEntry le : records) {
            String date = le.getAccessTime().toString();
            String ip = le.getIpAddress();
            
            if (date.indexOf(someday)!=-1 && !uniqueIpList.contains(ip)){
             uniqueIpList.add(ip);
            }
                
     }
     return uniqueIpList;
    }
    
    public HashMap<String, Integer> countVisitsPerIP(){
     HashMap<String, Integer> map = new HashMap<String, Integer>();
     for (LogEntry le : records){
         String ip = le.getIpAddress();
         if (!map.containsKey(ip)){
             map.put(ip, 1);
         } else {
             map.put(ip, map.get(ip)+1);
         }
        }
        return map;
    }
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> map){
        int maxValue = 0;
            for (int currValue : map.values()){
                if (currValue > maxValue){
                    maxValue = currValue;
                }
            }
        return maxValue;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map){
        ArrayList<String> ipList = new ArrayList<String>();
        int maxValue = mostNumberVisitsByIP(map);
            for (String key : map.keySet()){
                int currValue = map.get(key);
                
                if (maxValue == currValue) {
                    ipList.add(key);
                }
                
            }
                
        return ipList;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
            for (LogEntry le : records){
             String currDate = le.getAccessTime().toString();
             currDate = currDate.substring(4,10);
             String currIp = le.getIpAddress();
                if (!map.containsKey(currDate)){
                    ArrayList<String> currList = new ArrayList<String>();
                    currList.add(currIp);
                    map.put(currDate, currList);
                } else {
                    ArrayList<String> currList = map.get(currDate);
                    currList.add(currIp);
                    map.put(currDate, currList);
                }   
            }
        return map;
    }
    
    public int countUniqueIPsInRange(int low, int high){
     // returns the number of unique IPs in records that have a status code
     // in range from low to high inclusive
     ArrayList<String> uniqueIpList = new ArrayList<String>();
     for (LogEntry le : records) {
         String ip = le.getIpAddress();
         int status = le.getStatusCode();
         if (!uniqueIpList.contains(ip) && status>=low && status <= high){
             uniqueIpList.add(ip);
         }
     
     }
    return uniqueIpList.size();
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map){
     int maxSize = 0;
     String maxDay = null;
        for (String key : map.keySet()){
            int currSize = map.get(key).size();
                if (currSize > maxSize){
                    maxSize = currSize;
                    maxDay = key;
                }
        }
     return maxDay;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day){
        //public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map){
        // need to make map of IP -> visit count for specific date and then call ipsmostvisits on that.
        HashMap<String, Integer> currMap = new HashMap<String, Integer>();
            for (String key : map.keySet()){
             if (key.equals(day)){
                 ArrayList<String> currIpList = map.get(key);
                 for (String currIp : currIpList){
                  if (!currMap.containsKey(currIp)){
                      currMap.put(currIp, 1);
                  } else {
                      currMap.put(currIp, currMap.get(currIp)+1);
                  }   
                 }
                 break;
             }
            }
            ArrayList<String> result = iPsMostVisits(currMap);
            return result;
    }
    
     
}
