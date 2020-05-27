import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class BabyNames {
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalBoyNames = 0;
        int totalGirlNames = 0;
        
     for (CSVRecord record : fr.getCSVParser(false)){
        totalBirths += Integer.parseInt(record.get(2));
        if (record.get(1).equals("M")){
            totalBoys += Integer.parseInt(record.get(2));
            totalBoyNames++;
        }else{
            totalGirls += Integer.parseInt(record.get(2));
            totalGirlNames++;
        }
     }
        
     System.out.println("Total number of boys = " + totalBoys);
     System.out.println("Total number of girls = " + totalGirls);
     System.out.println("Total number of babies = " + totalBirths);
     System.out.println("Total number of boy names = " + totalBoyNames);
     System.out.println("Total number of girl names = " + totalGirlNames);
    }
    
    public int getRank(int year, String name, String gender){
        // return -1 if name not found
    FileResource fr = new FileResource("yob"+Integer.toString(year)+".csv");
    int nameRank = 0;
     
     for (CSVRecord record : fr.getCSVParser(false)){
         if (record.get(1).equals(gender)){
             nameRank++;
             if (record.get(0).equals(name)){
             return nameRank;
            }
        }
    }
    return -1;   
    }
    
    public String getName(int year, int rank, String gender){
    
    FileResource fr = new FileResource("yob" + Integer.toString(year) + ".csv");
    String nameResult = "NO NAME";
    int currentRank = 0;
    
    for (CSVRecord record : fr.getCSVParser(false)){
        if (record.get(1).equals(gender)){
            currentRank++;
            if (currentRank==rank){
                nameResult = record.get(0);
                return nameResult;
            }
        }
    }
        
    return nameResult;
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
     int nameRank = getRank(year, name, gender);
     String newName = getName(newYear, nameRank, gender);
     
     System.out.println(name + " born in " + year + " would be " + newName + " if born in " + newYear); 
        
    }
    
    public int yearOfHighestRank(String name, String gender){
        int highestRank = -1;
        int highestYear = -1;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            
            int year = getYearFromFile(f);
            int currentRank = getRank(year, name, gender);
            if (currentRank==-1){
                continue;
            }
         
         if (highestRank == -1){
             highestRank = currentRank;
             highestYear = year;
         } else if (currentRank < highestRank){
             highestRank = currentRank;
             highestYear = year;
            }
         }
     return highestYear;   
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int totalAppeared = 0;
        int totalRank = 0;
        
        for (File f : dr.selectedFiles()){
            //int getRank(year name gender)
            int year = getYearFromFile(f);
            int currentRank = getRank(year, name, gender);
            
            if (currentRank==-1){
                continue;
            } else {
                totalAppeared++;
                totalRank += currentRank;
            }
        }
        
        if (totalAppeared > 0){
        return (totalRank / (double)totalAppeared);
        } else {
        return -1.0;
        }
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        FileResource fr = new FileResource("yob" + Integer.toString(year) + ".csv");
        int stopRank = getRank(year, name, gender);
        int currentRank = 0;
        int totalBirths = 0;
        
       for (CSVRecord record : fr.getCSVParser(false)){
        if (!(record.get(1).equals(gender))){
            continue;
        }

        currentRank++;
         if (currentRank >= stopRank){
             break;
         } else {
         totalBirths += Integer.parseInt(record.get(2));
        }
       }
         return totalBirths;
    }
    
    public int getYearFromFile(File f){
        String yearString = f.getName();
        yearString = yearString.substring(3,7);
        int yearInt = Integer.parseInt(yearString);
        return yearInt;
    }
    
    public void testGetTotalBirthsRankedHigher(){
     int result = getTotalBirthsRankedHigher(2012, "Ethan", "M");
     System.out.println(result);
    }
    
    public void testGetAverageRank(){
     double rank = getAverageRank("Jacob", "M");   
     System.out.println(rank);   
    }
    
    public void testYearOfHighestRank(){
     int result = yearOfHighestRank("Emma", "M");
     System.out.println(result);
        
    }
    
    public void testGetName(){
     String name = getName(2012, 6, "M");
     System.out.println(name);
    }

    public void testGetRank(){
     int rank = getRank(2012, "Emma", "F");
     System.out.println("Specific name's rank is = " + rank);
    }

    public void testTotalBirths(){
     FileResource fr = new FileResource();
     totalBirths(fr);
    }
    
    

}
