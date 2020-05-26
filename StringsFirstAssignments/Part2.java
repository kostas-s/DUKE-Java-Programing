
public class Part2 {
    
 public String findSimpleGene(String dna, String startCodon, String stopCodon){
     String dnaUpper = dna.toUpperCase();
     int startIndex = dnaUpper.indexOf(startCodon);
        if (startIndex < 0){
            return "No start codon found";
        }
        int stopIndex = dnaUpper.indexOf(stopCodon, startIndex+3);
        if (stopIndex < 0) {
            return "No stop codon found";
        }
        if ((stopIndex - startIndex)%3 ==0){
            return dna.substring(startIndex, stopIndex+3);
        }else{
            return "Not a valid gene (codon sizes incorrect)";
        }
        
    
    }
        
    public void testSimpleGene(){
    String gene=null;

    String dna1 = "AAATGCCCTAACTAGATTAAGAAACC";        
    System.out.println("Dna is: " + dna1);
    gene = findSimpleGene(dna1, "ATG", "TAA");
    System.out.println("Gene is: " +gene);
        
    String dna2 = "ATGTAACCCTAT";        
    System.out.println("Dna is: " + dna2);
    gene = findSimpleGene(dna2, "ATG", "TAA");
    System.out.println("Gene is: " +gene);
    
    String dna3 = "ATTTAAATGATCGTAATTT";        
    System.out.println("Dna is: " + dna3);
    gene = findSimpleGene(dna3, "ATG", "TAA");
    System.out.println("Gene is: " +gene);
    
    String dna4 = "TCGTAATCGGGATTAA";        
    System.out.println("Dna is: " + dna4);
    gene = findSimpleGene(dna4, "ATG", "TAA");
    System.out.println("Gene is: " +gene);
    
    String dna5 = "TAATGCCCCTATTTAT";        
    System.out.println("Dna is: " + dna5);
    gene = findSimpleGene(dna5, "ATG", "TAA");
    System.out.println("Gene is: " +gene);
    
    String dna6 = "attatgtctcccattgcgtaa"; 
    System.out.println("Dna is: " + dna6);
    gene = findSimpleGene(dna6, "ATG", "TAA");
    System.out.println("Gene is: " +gene);
    }
    
    
    
}
