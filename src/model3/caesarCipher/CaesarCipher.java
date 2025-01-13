package src.model3.caesarCipher;

import java.util.Arrays;

public class CaesarCipher {
    int mainKey;
    private String alphabet;
    private String shiftAlphabet;

    public CaesarCipher(){
        alphabet ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }
    public CaesarCipher(int key){
        alphabet ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    public int getMainKey(){
        return this.mainKey;
    }

    public String tempShiftAlphabet(int key){
        return alphabet.substring(key) + alphabet.substring(0, key);
    }

    //Encryption method
    public String encrypt(String data) {
        return processText(data, this.alphabet, this.shiftAlphabet);
    }

    // Decryption method
    public String decrypt(String data, int key) {
        CaesarCipher cc = new CaesarCipher(26-mainKey);

        //String shiftAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        //return processText(data, cc.shiftAlphabet, cc.alphabet);
        return cc.encrypt(data);
    }

    // Core processing logic
    private static String processText(String data, String sourceAlphabet, String targetAlphabet) {
        StringBuilder ans = new StringBuilder();
        for (char c : data.toCharArray()) {
            char upperChar = Character.toUpperCase(c);
            int index = sourceAlphabet.indexOf(upperChar);
            if (index == -1) {
                ans.append(c);
                continue;
            }

            char targetChar = targetAlphabet.charAt(index);
            ans.append(Character.isUpperCase(c)?targetChar:Character.toLowerCase(targetChar));

        }
        return ans.toString();
    }

    // Find the index of the most frequent letter in the encrypted text
    public int freqIndex(String data){
        int max = 0;
        int[] letters = new int[26];
        for(int i = 0; i < data.length();i++){
            char upperChar = Character.toUpperCase(data.charAt(i));
            int index = this.alphabet.indexOf(upperChar);
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




    // Test the encryption and decryption process
    public static void testEncryptDecrypt() {
//        CaesarCipher caesarCipher = new CaesarCipher(23);
        String data = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
//        String ans = encryptTwoKeys(data,8,21);
//        System.out.println("Encrypted: " + ans);

        CaesarCipher caesarCipher = new CaesarCipher(8);
        String ans2 = caesarCipher.encrypt(data);
        System.out.println(ans2);
        // Find the most frequent letter's index in the encrypted text
        int freq = caesarCipher.freqIndex(ans2);
        System.out.println(freq);
        // Calculate the offset (key) using freq - 4
        int dkey = freq-4;
        if(dkey<4){
            dkey=26-(4-freq);
        }
        System.out.println("Detected Key: " + dkey);

        // Decrypt the text using the calculated key
        String deStr = caesarCipher.decrypt(ans2,26-dkey);
        System.out.println("Decrypted: " + deStr);
    }

    public static void main(String[] args) {
        String data = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        CaesarCipher cc = new CaesarCipher(8);
        String encr = cc.encrypt(data);
        System.out.println(encr);
        String decr = cc.decrypt(encr, cc.mainKey);
        System.out.println(decr);


    }
}
