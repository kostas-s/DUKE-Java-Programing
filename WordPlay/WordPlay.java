import java.io.*;

public class WordPlay {

    public boolean isVowel(char ch){
     String vowels = "AEIOU";
     int idx = vowels.indexOf(Character.toUpperCase(ch));
     if (idx != -1){
     return true;   
    }else {
     return false;
    }
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < sb.length(); i++){
         if (isVowel(sb.charAt(i))){
             sb.setCharAt(i, ch);
         }            
        }
        return sb.toString();       
    }
    
    public String emphasize(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        char chUpper = Character.toUpperCase(ch);
        
        for (int i = 0; i < sb.length(); i++){
         char currChar = Character.toUpperCase(sb.charAt(i));
         if (currChar == chUpper){
             if (i % 2 == 0) {
                 sb.setCharAt(i, '*');
                } else {
                 sb.setCharAt(i, '+');
             }
         }    
        }
        return sb.toString();
    }
    
    public void testIsVowel(){
        
     System.out.println(isVowel('a'));
     System.out.println(isVowel('A'));
     System.out.println(isVowel('f'));
    }
    
    
}
