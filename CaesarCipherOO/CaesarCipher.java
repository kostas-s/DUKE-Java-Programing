public class CaesarCipher {
    private String alpha;
    private String shiftedAlpha;
    private int mainKey;
    
    public CaesarCipher(int key){
        mainKey = key;
        alpha = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlpha = alpha.substring(key) + alpha.substring(0, key);
        
    }
    
    public String encrypt(String input){
     
        StringBuilder encryptedSb = new StringBuilder(input);
        
        for (int i = 0; i < input.length(); i++){
         char currChar = input.charAt(i);
         int idx = alpha.indexOf(Character.toLowerCase(currChar));
         if (idx != -1){
             if (Character.isLowerCase(currChar)){
             encryptedSb.setCharAt(i, shiftedAlpha.charAt(idx));
             } else {
             encryptedSb.setCharAt(i, Character.toUpperCase(shiftedAlpha.charAt(idx)));
             }
         }
        }
        return encryptedSb.toString();
    }
    
    public String decrypt(String input){
        int decryptKey = 26 - mainKey;
        CaesarCipher cc = new CaesarCipher(decryptKey);
        String decryptedString = cc.encrypt(input);
        return decryptedString;
    }
    
}
