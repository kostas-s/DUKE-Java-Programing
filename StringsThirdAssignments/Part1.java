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
        int count = 0;
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return;
        }
        
        while (true){

            String gene = findGene(dna.substring(startIndex, dna.length()));
            System.out.println(gene);
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
        System.out.println("Total number of Genes in file : " + count);
        
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
    
    public float cgRatio(String dna){
     int indexC = dna.indexOf("C");
     int countC = 0;
     int indexG = dna.indexOf("G");
     int countG = 0;
     
     while (indexC != -1){
         countC++;
         indexC = dna.indexOf("C", indexC + 1);
        }
        
     while (indexG != -1){
         countG++;
         indexG = dna.indexOf("G", indexG + 1);
     }
     
     float result = (countC + countG) / (float)dna.length();
     
     return result;   
    }
    
    public int countCTG(String dna){
     int indexCTG = dna.indexOf("CTG");
     int count = 0;
        
        while (indexCTG != -1){
        count++;    
        indexCTG = dna.indexOf("CTG", indexCTG + 1);
        }
     return count;
    }
    
    public void processGenes(StorageResource sr){
    
    int countLongLines = 0;
       
    for (String line : sr.data()){
     if (line.length()>60){
         System.out.println(line);
         countLongLines++;
        }
    }
    
    System.out.println("There are : " + countLongLines + " lines with more than 60 characters");
    
    int countCGratio = 0;
    for (String line : sr.data()){
     if (cgRatio(line) > 0.35) {
         System.out.println(line);
         countCGratio++;
        }
    }
    
    System.out.println("There are : " + countCGratio + " lines with C-G ratio higher than 0.35");
    
    String longestString = null;
    int maxLength = 0;
    
    for (String line : sr.data()){
        if (line.length() > maxLength){
            maxLength = line.length();
            longestString = line;
        }
    }
    
    System.out.println("Longest Line is : " + longestString);
    System.out.println("Longest Line's length is : " + longestString.length());
    
    }
    
    public void testProcessGenes(){
        StorageResource sr = new StorageResource();
     // String dna1 = "CTGlflfCTGlfCTlgCTG";
     // String dna2 = "CCGCCoeoheoC";
     // String dna3 = "attcacaacgtagccggagtcggtgttccgagcgccaacaccttcaaccactacaataatgtcatatgtagtcgcacgcctgtgagcgacatcgtcttcgctacgtgcaatctttccagcggcagcccagcaccgtggcggcatagaggcgttcttgagcattgccctatttcgacgccttcaggacactggaagaatgacgtgctcgtcaggagttccagatagaaaaagtttcgagggtctgcgataggtccacccctgagttgtgaagggatttgcttgtcacggaatattcgaacggatcggtgctgcttcgaggtcctcgacgccaggagtagactttgactgtgttaggatatcacaaaaagatacagatgcggaaacgccactatgtatgtcgacccgacgttctaagttcccatattacccagccttagcccggcatggtatatatgcgatgccaaataaacaactggagcggaccaaccacattaccctaaatccaggggtacacgaccgtaccggcctagatgctaagttccctgccagatgacctaaccctaacaccggtaaatttacaggagtgtcttatgtacggggctatcgcgtgtcaccccggtggcatggttctgtcggattatgtagttaccatgtacactgtaccgctggcttcattagtgcagaccgctcgggccaggggaacgaccaaacggtctaaacgcgtagcatggccgcatattactttcggctgtgacatgtccggccaatctcgggaataggcaccttggttttgacataaggggaactggagagctttcagcgtaccaggaagccctcttggaaacgcggctcccacatcggaaaccgttacgagaccatttagaactggcgctcaccgacaacctaagatgtcaggctcagcgatgacatgcgacggcctttgtgatctcacgcgaattagattgacccctgtagacgtaaacctggttcttatacctcc";
     // String dna4 = "CTGATTCATTATTATCGCGTAACTA";
     
    // sr.add(dna1);
    // sr.add(dna2);    
    // sr.add(dna3);
    // sr.add(dna4);
    
        FileResource fr = new FileResource("GRch38dnapart.fa");
        if (fr==null){
            System.out.println("File not found");
        }
        String dna = fr.asString();
        sr = getAllGenes(dna.toUpperCase());
        printAllGenes(dna.toUpperCase());
        int countCTGs = countCTG(dna.toUpperCase());
        processGenes(sr);
        System.out.println("CTG appears : " +countCTGs + " times in the file");
    
    }
    
    
    // public void testCountCTG(){
    // String dna = "CTGlflfCTGlfCTlgCTG";
    // int count = countCTG(dna);
    // System.out.println(count);
        
    // }
    
    
    // public void testCgRatio(){
        
    // float result = cgRatio("CCGCCoeoheoC");    
    // System.out.println(result);
        
    // }
    
    // public void testGetAllGenes(){
      // String dna = "attcacaacgtagccggagtcggtgttccgagcgccaacaccttcaaccactacaataatgtcatatgtagtcgcacgcctgtgagcgacatcgtcttcgctacgtgcaatctttccagcggcagcccagcaccgtggcggcatagaggcgttcttgagcattgccctatttcgacgccttcaggacactggaagaatgacgtgctcgtcaggagttccagatagaaaaagtttcgagggtctgcgataggtccacccctgagttgtgaagggatttgcttgtcacggaatattcgaacggatcggtgctgcttcgaggtcctcgacgccaggagtagactttgactgtgttaggatatcacaaaaagatacagatgcggaaacgccactatgtatgtcgacccgacgttctaagttcccatattacccagccttagcccggcatggtatatatgcgatgccaaataaacaactggagcggaccaaccacattaccctaaatccaggggtacacgaccgtaccggcctagatgctaagttccctgccagatgacctaaccctaacaccggtaaatttacaggagtgtcttatgtacggggctatcgcgtgtcaccccggtggcatggttctgtcggattatgtagttaccatgtacactgtaccgctggcttcattagtgcagaccgctcgggccaggggaacgaccaaacggtctaaacgcgtagcatggccgcatattactttcggctgtgacatgtccggccaatctcgggaataggcaccttggttttgacataaggggaactggagagctttcagcgtaccaggaagccctcttggaaacgcggctcccacatcggaaaccgttacgagaccatttagaactggcgctcaccgacaacctaagatgtcaggctcagcgatgacatgcgacggcctttgtgatctcacgcgaattagattgacccctgtagacgtaaacctggttcttatacctcc";
      // StorageResource sr = getAllGenes(dna.toUpperCase());
      
      // for (String s: sr.data()){
          // System.out.println("Gene: " + s);
        // }
        
    // }
    
    // public void testFindGene(){
     // String dna = "attcacaacgtagccggagtcggtgttccgagcgccaacaccttcaaccactacaataatgtcatatgtagtcgcacgcctgtgagcgacatcgtcttcgctacgtgcaatctttccagcggcagcccagcaccgtggcggcatagaggcgttcttgagcattgccctatttcgacgccttcaggacactggaagaatgacgtgctcgtcaggagttccagatagaaaaagtttcgagggtctgcgataggtccacccctgagttgtgaagggatttgcttgtcacggaatattcgaacggatcggtgctgcttcgaggtcctcgacgccaggagtagactttgactgtgttaggatatcacaaaaagatacagatgcggaaacgccactatgtatgtcgacccgacgttctaagttcccatattacccagccttagcccggcatggtatatatgcgatgccaaataaacaactggagcggaccaaccacattaccctaaatccaggggtacacgaccgtaccggcctagatgctaagttccctgccagatgacctaaccctaacaccggtaaatttacaggagtgtcttatgtacggggctatcgcgtgtcaccccggtggcatggttctgtcggattatgtagttaccatgtacactgtaccgctggcttcattagtgcagaccgctcgggccaggggaacgaccaaacggtctaaacgcgtagcatggccgcatattactttcggctgtgacatgtccggccaatctcgggaataggcaccttggttttgacataaggggaactggagagctttcagcgtaccaggaagccctcttggaaacgcggctcccacatcggaaaccgttacgagaccatttagaactggcgctcaccgacaacctaagatgtcaggctcagcgatgacatgcgacggcctttgtgatctcacgcgaattagattgacccctgtagacgtaaacctggttcttatacctcc";
     // printAllGenes(dna.toUpperCase());
        
    // }
    
    
    // public void testFindStopCodon(){
    // String dna = "zzzATGsdsdfsdfsdfTAAdTAAdsds";
    // int startIndex = dna.indexOf("ATG");
    // int stopIndex = findStopCodon(dna, startIndex, "TAA");
    // System.out.println(dna.substring(startIndex, stopIndex + 3));
    
    // dna = "zzzATGfftTAAttlllkkkjjjTAAllooosss";
    // startIndex = dna.indexOf("ATG");
    // stopIndex = findStopCodon(dna, startIndex, "TAA");
    // if (stopIndex == dna.length() || startIndex== -1){    
        // System.out.println("gene not found");
    // } else {
    // System.out.println(dna.substring(startIndex, stopIndex + 3));
// }
    // }
    
}
