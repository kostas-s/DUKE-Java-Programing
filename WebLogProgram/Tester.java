
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    LogAnalyzer la;
    
    public Tester(String fileName){
    la = new LogAnalyzer();
    la.readFile(fileName);
    }
    
    
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        la.printAll();
        int count = la.countUniqueIPsInRange(300,399);
        System.out.println(count + " unique IPs in range 300-399");
    }
    
    public void testCountUniqueIPsInRange(int num1, int num2){
        int count = la.countUniqueIPsInRange(num1,num2);
        System.out.println(count + " unique IPs in range " +num1 + "-" + num2);
    }
    
    public void testPrintAllHigherThanNum(int num){
        System.out.println("printing all higher than specific status number " + num);
        la.printAllHigherThanNum(num);
    }
    
    public void testUniqueIPVisitsOnDay(String day){
        ArrayList<String> uniqueIP = la.uniqueIPVisitsOnDay(day);
        System.out.println("printing unique ip visits on day " + day + ", Count : " + uniqueIP.size());
        // for (int i = 0; i < uniqueIP.size(); i++){
        // System.out.println(uniqueIP.get(i));
        // }
    }
    
    public void testUniqueIPs() {
        int count = la.countUniqueIPs();
        System.out.println("Unique IPs logged : " + count);
    }
    
    public void testCountVisitsPerIP() {
        HashMap<String, Integer> map = la.countVisitsPerIP();
        System.out.println(map);
    }
    
    public void testMostNumberVisitsByIP() {
       HashMap<String, Integer> map = la.countVisitsPerIP();
       int maxVisits = la.mostNumberVisitsByIP(map);
       System.out.println("Max visits by single ip: " + maxVisits); 
    }
    
    public void testIPsMostVisits(){
       HashMap<String, Integer> map = la.countVisitsPerIP();
       ArrayList<String> ipList = la.iPsMostVisits(map);
       System.out.println(ipList); 
    }
    
    public void testIPsForDays(){
       HashMap<String, ArrayList<String>> map = la.iPsForDays();
       System.out.println(map);
    }
    
    public void testDayWithMostIPVisits(){
       HashMap<String, ArrayList<String>> map = la.iPsForDays();
       String day = la.dayWithMostIPVisits(map);
       System.out.println(day);
    }
    
    public void testIPsWithMostVisitsOnDay(String day){
       HashMap<String, ArrayList<String>> map = la.iPsForDays();
       ArrayList<String> ipList = la.iPsWithMostVisitsOnDay(map, day);
       System.out.println(ipList);
    }
        
}
