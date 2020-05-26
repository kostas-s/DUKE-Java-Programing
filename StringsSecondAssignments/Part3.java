
public class Part3 {
 
 public int countGenes(String dna){
     
     int count = 0;
     
     int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return 0;
        }
        
        while (true){

            String gene = findGene(dna.substring(startIndex, dna.length()));
     
            if (gene.isEmpty()){
                //System.out.println("Empty Gene");
                break;
            }
            count++;
            
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
            if (startIndex == -1){
                //System.out.println("startIndex -1");
                break;
            }
            

        }
     
     
     
     return count;
    }
    
    public void testCountGenes(){
     int count=0;
     String dna = "AATGCTAACTAGCTGACTAAT";
     count = countGenes(dna.toUpperCase());
     System.out.println("Total number of Genes in DNA sample: " + count);
     printAllGenes(dna.toUpperCase());
        
    }
    
 
    
 public String findGene(String dna){
         
     int startIndex = dna.indexOf("ATG");
     if (startIndex == -1){
         return "";
        }
        
     int indexTAA = findStopCodon(dna, startIndex, "TAA");    
     int indexTAG = findStopCodon(dna, startIndex, "TAG");
     int indexTGA = findStopCodon(dna, startIndex, "TGA");
     
     int minIndex = Math.min(indexTAA, Math.min(indexTAG, indexTGA));
     if (minIndex == dna.length()){
         return "";
     }
     
     return dna.substring(startIndex, minIndex+3);
     }
    
    
 public int findStopCodon(String dna, int startIndex, String stopCodon){
    
        int currIndex = dna.indexOf(stopCodon, startIndex);
        
        while (currIndex != -1){
            
         if ((currIndex - startIndex) % 3 == 0){
             return currIndex;
            }
            else{
            currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }

     return dna.length();
    }
    
 public void printAllGenes(String dna){
        
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return;
        }
        
        while (true){

            String gene = findGene(dna.substring(startIndex, dna.length()));
            
            if (gene.isEmpty()){
                //System.out.println("Empty Gene");
                break;
            }
            
            System.out.println(gene);
            
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
            if (startIndex == -1){
                //System.out.println("startIndex -1");
                break;
            }
            

        }
        
        
    }
    
    
    
    
    
    
}
