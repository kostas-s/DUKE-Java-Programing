import java.io.*;
import edu.duke.*;

public class CaesarCipherTwo {

    private String alpha;
    private String shiftedAlpha1;
    private String shiftedAlpha2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1,int key2){
     alpha = "abcdefghijklmnopqrstuvwxyz";
     shiftedAlpha1 = alpha.substring(key1) + alpha.substring(0, key1);
     shiftedAlpha2 = alpha.substring(key2) + alpha.substring(0, key2);
     mainKey1=key1;
     mainKey2=key2;
    }
    
    public String encrypt(String input){
     StringBuilder sb = new StringBuilder(input);   
     
        for (int i = 0; i < input.length(); i+=2){
         char currChar = input.charAt(i);
         int idx = alpha.indexOf(Character.toLowerCase(currChar));
         if (idx != -1){
             if (Character.isLowerCase(currChar)){
             sb.setCharAt(i, shiftedAlpha1.charAt(idx));
             } else {
             sb.setCharAt(i, Character.toUpperCase(shiftedAlpha1.charAt(idx)));
             }
         }
        }
        for (int i = 1; i < input.length(); i+=2){
         char currChar = input.charAt(i);
         int idx = alpha.indexOf(Character.toLowerCase(currChar));
         if (idx != -1){
             if (Character.isLowerCase(currChar)){
             sb.setCharAt(i, shiftedAlpha2.charAt(idx));
             } else {
             sb.setCharAt(i, Character.toUpperCase(shiftedAlpha2.charAt(idx)));
             }
         }
        }
        return sb.toString();
        
    }
    
    public String decrypt(String input){
        int dKey1 = 26 - mainKey1;
        int dKey2 = 26 - mainKey2;
        CaesarCipherTwo cc = new CaesarCipherTwo(dKey1, dKey2);
        String decryptedString = cc.encrypt(input);
        return decryptedString;
    }
    
    
}
