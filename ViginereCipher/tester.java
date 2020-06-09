import edu.duke.*;
import java.util.*;

public class tester {

    public void testCaesarCipher(){
        FileResource fr = new FileResource();
        CaesarCipher cc = new CaesarCipher(6);
        String encrypted = cc.encrypt(fr.asString());
        String decrypted = cc.decrypt(encrypted);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }
        public void testCaesarCracker(char c){
        FileResource fr = new FileResource();
        CaesarCracker cc = new CaesarCracker(c);
        String decrypted = cc.decrypt(fr.asString());
        System.out.println(decrypted);
    }
    public void testViginereCipher(){
     int[] key = {17, 14, 12, 4};
     FileResource fr = new FileResource();
     VigenereCipher vc = new VigenereCipher(key);
     String encrypted = vc.encrypt(fr.asString());
     String decrypted = vc.decrypt(encrypted);
     String keyRet = vc.toString();
     System.out.println(encrypted);
     System.out.println(decrypted);
     System.out.println(keyRet);
        
    }
    public void testVigenereBreaker(){
    VigenereBreaker vb = new VigenereBreaker();
    System.out.println(vb.sliceString("abcdefghijklm", 0, 3));
    System.out.println(vb.sliceString("abcdefghijklm", 1, 3));
    System.out.println(vb.sliceString("abcdefghijklm", 2, 3));
    System.out.println(vb.sliceString("abcdefghijklm", 0, 4));
    System.out.println(vb.sliceString("abcdefghijklm", 1, 4));
    System.out.println(vb.sliceString("abcdefghijklm", 2, 4));
    System.out.println(vb.sliceString("abcdefghijklm", 3, 4));
    System.out.println(vb.sliceString("abcdefghijklm", 0, 5));
    System.out.println(vb.sliceString("abcdefghijklm", 1, 5));
    System.out.println(vb.sliceString("abcdefghijklm", 2, 5));
    System.out.println(vb.sliceString("abcdefghijklm", 3, 5));
    System.out.println(vb.sliceString("abcdefghijklm", 4, 5));    
    }
    
    public void testTryKeyLength(int keyLength){
     VigenereBreaker vb = new VigenereBreaker();
     FileResource fr = new FileResource();
     int[] key = vb.tryKeyLength(fr.asString(), keyLength, 'e');
     for (int i = 0; i < key.length; i++){
     System.out.println(key[i]);
    }
    }
    
    public void testDictionaryAndCount(){
     VigenereBreaker vb = new VigenereBreaker();
     FileResource fr = new FileResource();
     HashSet<String> dict = vb.readDictionary(fr);
     int count = vb.countWords("i am Cute liTTle PuPPy lolol", dict);
     System.out.println(count);
     
    }
    
    public void testCount(){
     VigenereBreaker vb = new VigenereBreaker();
     FileResource fr = new FileResource();
     HashSet<String> dict = vb.readDictionary(fr);
     char commonChar = vb.mostCommonCharIn(dict);
     System.out.println("most common char in selected language is : " + commonChar);
     
    }
    
}
