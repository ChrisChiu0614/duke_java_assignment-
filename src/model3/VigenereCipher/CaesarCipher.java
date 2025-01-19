package src.model3.VigenereCipher;

import java.util.Arrays;

public class CaesarCipher {
    int mainKey;
    private String alphabet;
    private String shiftAlphabet;

    public CaesarCipher(int key){
        alphabet ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    public int getMainKey(){
        return this.mainKey;
    }

    public char encryptLetter(char c) {
        return transformLetter(c, alphabet, shiftAlphabet);
    }

    public char decryptLetter(char c) {
        return transformLetter(c, shiftAlphabet , alphabet);
    }

    private char transformLetter(char c, String from, String to) {
        char upperCase = Character.toUpperCase(c);
        int idx = from.indexOf(upperCase);
        if (idx != -1) {
            char res = Character.isUpperCase(c)?to.charAt(idx):Character.toLowerCase(to.charAt(idx));
            return res;
        }
        return c;
    }

    //Encryption method
    public String encrypt(String data) {
        return processText(data, alphabet, shiftAlphabet);
    }

    // Decryption method
    public String decrypt(String data) {
        return processText(data, shiftAlphabet, alphabet);
    }

    // Core processing logic
    private String processText(String data, String sourceAlphabet, String targetAlphabet) {
        StringBuilder sb = new StringBuilder();
        for (char c : data.toCharArray()) {
            char upperChar = Character.toUpperCase(c);
            char targetChar = transformLetter(upperChar,sourceAlphabet,targetAlphabet);
            sb.append(Character.isUpperCase(c)?targetChar:Character.toLowerCase(targetChar));

        }
        return sb.toString();
    }



    // Find the index of the most frequent letter in the encrypted text
    public int freqIndex(String data){
        int max = 0;
        int[] letters = new int[26];
        for(int i = 0; i < data.length();i++){
            char upperChar = Character.toUpperCase(data.charAt(i));
            int index = alphabet.indexOf(upperChar);
            if(index==-1){
                continue;
            }
            letters[index]++;
            if(letters[index]>letters[max]){
                max=index;
            }

        }
        System.out.println(Arrays.toString(letters));
        return max;
    }

    public static String encryptTwoKeys(String input, int key1, int key2){
        CaesarCipher caesarCipher = new CaesarCipher(key1);
        String key1Enc = caesarCipher.encrypt(input);

        caesarCipher = new CaesarCipher(key2);
        String key2Enc = caesarCipher.encrypt(input);
        StringBuilder sb = new StringBuilder(key1Enc);
        for(int i = 0; i < key2Enc.length(); i++){
            if((i&1)==1){
                sb.setCharAt(i, key2Enc.charAt(i));
            }
        }
        return sb.toString();
    }

    public String toString() {
        return "" + mainKey;
    }

}
