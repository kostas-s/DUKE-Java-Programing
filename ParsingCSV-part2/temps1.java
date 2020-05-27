import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class temps1 {

    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestRow = null;
        
        for (CSVRecord currentRow : parser){
         if (coldestRow == null){
             coldestRow = currentRow;
            } else {
                double coldestTemp = Double.parseDouble(coldestRow.get("TemperatureF"));
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                if (currentTemp==-9999){
                    continue;
                }
                if (currentTemp < coldestTemp){
                    coldestRow = currentRow;
                }
            }
        }
     return coldestRow;
    }
    
    
    public File fileWithColdestTemperature(){

        DirectoryResource dr = new DirectoryResource();
        File coldestFile = null;
        CSVRecord coldestRow = null; 
        
        for (File f : dr.selectedFiles()){
            if (coldestFile == null){
                coldestFile = f;
            }
            
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            
            if (coldestRow == null){
                coldestRow = currentRow;
            } else {
                double coldestTemp = Double.parseDouble(coldestRow.get("TemperatureF"));
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                
                // check for invalid temp
                
                if (currentTemp==-9999){
                    continue;
                }
                if (currentTemp < coldestTemp){
                    coldestRow = currentRow;
                    coldestFile = f;
                }
            }
            
            
        }

        return coldestFile;
    }
    
    public CSVRecord getLowestRow(CSVRecord lowestRow, CSVRecord currentRow){        
        
        double lowestHumidity = Double.parseDouble(lowestRow.get("Humidity"));
        double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
        if (currentHumidity < lowestHumidity){
            return currentRow;
        } else {
            return lowestRow;
        }
         
    }
    
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestRow = null;
        
        for (CSVRecord currentRow : parser){
            
            // Check for invalid Humidity
            
            if (currentRow.get("Humidity").equals("N/A")){
                continue;
            }
            
            if (lowestRow == null){
                lowestRow = currentRow;
            } else {
                
                
                lowestRow = getLowestRow(lowestRow, currentRow);
            }
        
        }
        return lowestRow;
    }
    
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestRow = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());            
            if (lowestRow == null){
                lowestRow = currentRow;
            } else {
                
                
                lowestRow = getLowestRow(lowestRow, currentRow);
            }
                
        
        }
        return lowestRow;
    }
    
    
    public double averageTemperatureInFile (CSVParser parser){
     int count = 0;
     double totalTemp = 0;
     
     for (CSVRecord currentRow : parser){
         double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
         
         // Check for invalid temp
         
         if (currentTemp==-9999){
            continue;
         }
         totalTemp = totalTemp + currentTemp;
         count++;
     }

     return totalTemp/count;
    }
    
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        int count = 0;
        double totalTemp = 0;
        
        for (CSVRecord currentRow : parser){
            
            // check for invalid humidity
            
            if (currentRow.get("Humidity").equals("N/A")){
                continue;
            }
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            if (currentTemp==-9999){
                    continue;
                }
            
            if (currentHumidity >= value){
                totalTemp = totalTemp+currentTemp;
                count++;
            }
        }
        
     if (count == 0){
         return 0;
     } else {
         
         return totalTemp/count;
        }
    }
    
    
    public void testAverageTemperatureWithHighHumidityInFile(){
     // names are gonna need two lines if they keep growing like that...   
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureWithHighHumidityInFile(parser, 80); 
        
        if (avgTemp == 0){
            System.out.println("No temperatures with that humidity");
        }else{
            System.out.println("Average Temp when high Humidity is " + avgTemp);
        }
     
        
    }
    
    
    public void testAverageTemperatureInFile(){
     FileResource fr = new FileResource();
     double avgTemp = averageTemperatureInFile(fr.getCSVParser());
     
     System.out.println("Average temperature in file is " + avgTemp);
        
    }
    
    
    public void testLowestHumidityInManyFiles(){
     CSVRecord csv = lowestHumidityInManyFiles();
     System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
        
    }
    
    
    public void testLowestHumidityInFile(){
     FileResource fr = new FileResource();
     CSVParser parser = fr.getCSVParser();
     CSVRecord csv = lowestHumidityInFile(parser);
     
     System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
     
     
    }
    
    
    public void testFileWithColdestTemperature(){
        File coldestFile = fileWithColdestTemperature();
        FileResource fr = new FileResource(coldestFile);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRow = coldestHourInFile(parser);
        parser = fr.getCSVParser();
        
        System.out.println("Coldest day was in file " + coldestFile.getName());
        System.out.println("Coldest temperature on that day was " + coldestRow.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were:");
        for (CSVRecord record : parser){
        System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
        
        
    }
    
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord coldestRow = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + coldestRow.get("TemperatureF") + " at " + (coldestRow.get("TimeEST")));
        
        
    }
    
}