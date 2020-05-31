import edu.duke.*;

public class TestCaesarCipherTwo {
    
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    
    private int[] countLetters(String s){
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
    
    private int maxIndex(int[] count){
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
    
    private String halfOfString(String message, int start){
     StringBuilder sb = new StringBuilder();   
        for (int i = start; i < message.length(); i += 2){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }
    
    public String breakCaesarCipher(String input){
        String input1 = halfOfString(input, 0);
        String input2 = halfOfString(input, 1);
        int count1[] = countLetters(input1);
        int maxIdx1 = maxIndex(count1);
        int decryptKey1 = maxIdx1 - 4;
        
        if (decryptKey1 < 0){
            decryptKey1 = decryptKey1 + 26;
        }
        
        int count2[] = countLetters(input2);
        int maxIdx2 = maxIndex(count2);
        int decryptKey2 = maxIdx2 - 4;
        
        if (decryptKey2 < 0){
            decryptKey2 = decryptKey2 + 26;
        }
        
        CaesarCipherTwo cc = new CaesarCipherTwo(decryptKey1, decryptKey2);
        System.out.println("Detected keys :" +decryptKey1 + " " + decryptKey2);
        String result = cc.decrypt(input);
        return result;
        
    }
    
    public void testEncrypt(String input, int key1, int key2){
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        String result = cc.encrypt(input);
        System.out.println("Encrypted Text : " +result);
    }
    
    public void testDecrypt(String input){

        String result = breakCaesarCipher(input);
        System.out.println("Decrypted Text : " +result);
    }
    
    public void testDecryptFile(){
        FileResource fr = new FileResource();
        
        String decrypted = breakCaesarCipher(fr.asString());
        System.out.println("Decrypted Text is :" + decrypted);

    }
}
