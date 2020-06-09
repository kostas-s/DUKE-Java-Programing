import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows(String key){
    MarkovOne markov = new MarkovOne();
    String myText = "this is a test yes this is a test.";
    markov.setTraining(myText);
    ArrayList<String> followsList = markov.getFollows(key);
    System.out.println(followsList);
    System.out.println("ArrayList Size: " + followsList.size());
    
    }
    
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        //markov.setRandom(42);
        ArrayList<String> list = markov.getFollows("th");
        System.out.println(list);
        System.out.println("ArrayList Size: " + list.size());
        //String text = markov.getRandomText(500);
        //printOut(text);
    }
        private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
}
