
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
     
        if (stringa == null){
         return 0;
        }
     
        if (stringb == null){
         return 0;
        }
        
     int count = 0;
     int index = stringb.indexOf(stringa);
     
     while (index!=-1){
         count++;
         index = stringb.indexOf(stringa, index + stringa.length());
        }
        
     return count;
    }
    
    
    public void testHowMany(){
    int count=0;
    
    count = howMany("AA", "ATAAAA");
    System.out.println(count);
    
    count = howMany("GAA", "ATG AA CGAATTGAATC");
    System.out.println(count);
    
    
    }
}
