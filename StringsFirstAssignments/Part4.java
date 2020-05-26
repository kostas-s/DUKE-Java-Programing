import edu.duke.*;

public class Part4 {
    
    public void testing(){
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        int ytIndex = 0;
        int startIndex = 0;
        int endIndex = 0;
        
        for(String word : ur.words()){
            String wordLower = word.toLowerCase();
            ytIndex = wordLower.indexOf("youtube.com");
            
            if (ytIndex==-1){
                continue;
            }else{
                startIndex = wordLower.indexOf("\"");
                endIndex = wordLower.lastIndexOf("\"");
                System.out.println(word.substring(startIndex+1, endIndex));
            }
        }
        
            
            
        }

        
        
        
    }


