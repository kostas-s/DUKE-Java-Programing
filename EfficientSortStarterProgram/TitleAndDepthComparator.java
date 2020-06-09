import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry q1, QuakeEntry q2){
        String title1 = q1.getInfo();
        String title2 = q2.getInfo();
        int result = title1.compareTo(title2);
        if (result == 0){
            // if titles are the same, compare by depth
            double depth1 = q1.getDepth();
            double depth2 = q2.getDepth();
            if (depth1 < depth2){
                return -1;
            }
            if (depth1 > depth2){
                return 1;
            }
            return 0;
        } else {
            return result;
        }
    }
}
