import edu.duke.*;
import org.apache.commons.csv.*;


public class ParseCSV {

    public String countryInfo(CSVParser parser , String country){
        for (CSVRecord record : parser){
         String parsedCountry = record.get("Country");
            
         if (parsedCountry.contains(country)){
             String exports = record.get("Exports");
             String value = record.get("Value (dollars)");
             String result = (country + ": " + exports + ": " + ": " + value);
             return result;
            }
            
        }
        
        return "NOT FOUND";
    }
    
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record : parser){
            String parsedExports = record.get("Exports");
            
            if (parsedExports.contains(exportItem1) && parsedExports.contains(exportItem2)){
                String country = record.get("Country");
                System.out.println(country);
            }
            
        }
        
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        
        for (CSVRecord record : parser){
         String parsedExports = record.get("Exports");
         
         if (parsedExports.contains(exportItem)){
             count++;
            }
            
        }

        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
     for (CSVRecord record : parser){
         String parsedValue = record.get("Value (dollars)");
         if (parsedValue.length() > amount.length()){
             String parsedCountry = record.get("Country");
             System.out.println(parsedCountry + " " + parsedValue);
            }
        }

    }
    
    
    
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        // String test1 = countryInfo(parser, "Nauru");
        // System.out.println(test1);
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //int count = numberOfExporters(parser, "cocoa");
        //System.out.println(count + " countries export cocoa");
        bigExporters(parser, "$999,999,999,999");
    }
    
    
}
