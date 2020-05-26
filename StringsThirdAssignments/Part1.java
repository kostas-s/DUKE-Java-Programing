import edu.duke.*;

public class Part1 {
    
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
            System.out.println(gene);
            if (gene.isEmpty()){
                System.out.println("Empty Gene");
                break;
            }
            
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
            if (startIndex == -1){
                System.out.println("startIndex -1");
                break;
            }
            

        }
        
        
    }
    
    public StorageResource getAllGenes(String dna){
        //System.out.println("In getAllGenes");
        StorageResource geneList = new StorageResource();
                
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return geneList;
        }
        
        while (true){

            String gene = findGene(dna.substring(startIndex, dna.length()));
            
            if (gene.isEmpty()){
                //System.out.println("Empty Gene");
                break;
            }
                        
            geneList.add(gene);
            
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
            if (startIndex == -1){
                //System.out.println("startIndex -1");
                break;
            }
            

        }
        
        return geneList;
    }
    
    public void testGetAllGenes(){
      String dna = "attcacaacgtagccggagtcggtgttccgagcgccaacaccttcaaccactacaataatgtcatatgtagtcgcacgcctgtgagcgacatcgtcttcgctacgtgcaatctttccagcggcagcccagcaccgtggcggcatagaggcgttcttgagcattgccctatttcgacgccttcaggacactggaagaatgacgtgctcgtcaggagttccagatagaaaaagtttcgagggtctgcgataggtccacccctgagttgtgaagggatttgcttgtcacggaatattcgaacggatcggtgctgcttcgaggtcctcgacgccaggagtagactttgactgtgttaggatatcacaaaaagatacagatgcggaaacgccactatgtatgtcgacccgacgttctaagttcccatattacccagccttagcccggcatggtatatatgcgatgccaaataaacaactggagcggaccaaccacattaccctaaatccaggggtacacgaccgtaccggcctagatgctaagttccctgccagatgacctaaccctaacaccggtaaatttacaggagtgtcttatgtacggggctatcgcgtgtcaccccggtggcatggttctgtcggattatgtagttaccatgtacactgtaccgctggcttcattagtgcagaccgctcgggccaggggaacgaccaaacggtctaaacgcgtagcatggccgcatattactttcggctgtgacatgtccggccaatctcgggaataggcaccttggttttgacataaggggaactggagagctttcagcgtaccaggaagccctcttggaaacgcggctcccacatcggaaaccgttacgagaccatttagaactggcgctcaccgacaacctaagatgtcaggctcagcgatgacatgcgacggcctttgtgatctcacgcgaattagattgacccctgtagacgtaaacctggttcttatacctcc";
      StorageResource sr = getAllGenes(dna.toUpperCase());
      
      for (String s: sr.data()){
          System.out.println("Gene: " + s);
        }
        
    }
    
    public void testFindGene(){
     String dna = "attcacaacgtagccggagtcggtgttccgagcgccaacaccttcaaccactacaataatgtcatatgtagtcgcacgcctgtgagcgacatcgtcttcgctacgtgcaatctttccagcggcagcccagcaccgtggcggcatagaggcgttcttgagcattgccctatttcgacgccttcaggacactggaagaatgacgtgctcgtcaggagttccagatagaaaaagtttcgagggtctgcgataggtccacccctgagttgtgaagggatttgcttgtcacggaatattcgaacggatcggtgctgcttcgaggtcctcgacgccaggagtagactttgactgtgttaggatatcacaaaaagatacagatgcggaaacgccactatgtatgtcgacccgacgttctaagttcccatattacccagccttagcccggcatggtatatatgcgatgccaaataaacaactggagcggaccaaccacattaccctaaatccaggggtacacgaccgtaccggcctagatgctaagttccctgccagatgacctaaccctaacaccggtaaatttacaggagtgtcttatgtacggggctatcgcgtgtcaccccggtggcatggttctgtcggattatgtagttaccatgtacactgtaccgctggcttcattagtgcagaccgctcgggccaggggaacgaccaaacggtctaaacgcgtagcatggccgcatattactttcggctgtgacatgtccggccaatctcgggaataggcaccttggttttgacataaggggaactggagagctttcagcgtaccaggaagccctcttggaaacgcggctcccacatcggaaaccgttacgagaccatttagaactggcgctcaccgacaacctaagatgtcaggctcagcgatgacatgcgacggcctttgtgatctcacgcgaattagattgacccctgtagacgtaaacctggttcttatacctcc";
     printAllGenes(dna.toUpperCase());
        
    }
    
    
    public void testFindStopCodon(){
    String dna = "zzzATGsdsdfsdfsdfTAAdTAAdsds";
    int startIndex = dna.indexOf("ATG");
    int stopIndex = findStopCodon(dna, startIndex, "TAA");
    System.out.println(dna.substring(startIndex, stopIndex + 3));
    
    dna = "zzzATGfftTAAttlllkkkjjjTAAllooosss";
    startIndex = dna.indexOf("ATG");
    stopIndex = findStopCodon(dna, startIndex, "TAA");
    if (stopIndex == dna.length() || startIndex== -1){    
        System.out.println("gene not found");
    } else {
    System.out.println(dna.substring(startIndex, stopIndex + 3));
}
    }
    
}
