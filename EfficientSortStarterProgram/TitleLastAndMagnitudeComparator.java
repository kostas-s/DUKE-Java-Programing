import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2){
           String title1 = q1.getInfo().trim();
           String title2 = q2.getInfo().trim();
           String lWord1 = title1.substring(title1.lastIndexOf(" ") + 1);
           String lWord2 = title2.substring(title2.lastIndexOf(" ") + 1);
           int result = lWord1.compareTo(lWord2);
         if (result == 0) {
           // if words are the same, compare by magnitude
           double mag1 = q1.getMagnitude();
           double mag2 = q2.getMagnitude();
           if (mag1 < mag2){
               return -1;
           }
           if (mag1 > mag2){
               return 1;
           }
           
           return 0;
           
         } else {
            return result;
         }
    }
}
