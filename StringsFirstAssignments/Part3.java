
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        int count=0;
        int index=0;
        
        while (stringb.indexOf(stringa, index)!=-1){
            count++;
            index = stringb.indexOf(stringa, index)+3;
        }
        
        if (count > 1){        
        return true;
    }else{
        return false;
    }
    }

    
    public String lastPart(String stringa, String stringb){
        int startIndex = stringb.indexOf(stringa);
        int strLength = stringa.length();
        
        if (startIndex != -1){
            return stringb.substring(startIndex+strLength);
        }else{
            return stringb;
    
        }
    }
    
    
    public void testing(){
        boolean state=false;
        
        state = twoOccurrences("by", "A story by Abby Long");
        System.out.println(state);
        
        state = twoOccurrences("a", "banana");
        System.out.println(state);
        
        state = twoOccurrences("atg", "ctgtatgta");
        System.out.println(state);
        
        String resultString = lastPart("an" , "banana");
        System.out.println("The part of the string after an in banana is "+resultString);
        
        resultString = lastPart ("zoo", "forest");
        System.out.println("The part of the string after zoo in forest is "+resultString);
        
        resultString = lastPart ("anaN", "This is a string anaN divided in two");
        System.out.println("The part of the string requested is "+resultString);
    }
    
    
}
