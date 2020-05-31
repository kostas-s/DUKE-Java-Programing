import edu.duke.*;
import java.io.*;


public class TestCaesarCipher {
    
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    
    public int[] countLetters(String s){
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++){
            char currChar = s.charAt(i);
            
            if (Character.isLetter(currChar)){
                int idx = alphabet.indexOf(Character.toLowerCase(currChar));
                count[idx]++;
            }
        
        }
        return count;
    }
    
    public int maxIndex(int[] count){
     int currMax = 0;
     int maxIdx = 0;
     
     for (int i = 0; i < count.length; i++){
         if (count[i] > currMax){
             currMax = count[i];
             maxIdx = i;
         }
     }
     return maxIdx;
    }
    
    public String breakCaesarCipher(String input){
        int count[] = countLetters(input);
        int maxIdx = maxIndex(count);
        int decryptKey = maxIdx - 4;
        
        if (decryptKey < 0){
            decryptKey = decryptKey + 26;
        }
        
        CaesarCipher cc = new CaesarCipher(decryptKey);
        String result = cc.decrypt(input);
        return result;
    }
    
    public void testEncrypt(String input, int key){
        CaesarCipher cc = new CaesarCipher(key);
        String result = cc.encrypt(input);
        System.out.println("Encrypted Text : " +result);
    }
    
    public void testDecrypt(String input){

        String result = breakCaesarCipher(input);
        System.out.println("Decrypted Text : " +result);
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        CaesarCipher cc = new CaesarCipher(1);
        String encryptedStr = cc.encrypt(fr.asString());
        //String decryptedStr = cc.decrypt(encryptedStr);
        String decryptedStr = breakCaesarCipher(encryptedStr);

        System.out.println("encrypted text is :" + encryptedStr);
        System.out.println("decrypted text is :" + decryptedStr);

    }
    
}
