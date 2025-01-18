package src.model3.VigenereCipher;

import src.model3.caesarCipher.CaesarCipherTwo;

public class CaesarCracker {

    private char mostCommon;
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public CaesarCracker(){
        mostCommon = 'e';
    }

    public CaesarCracker(char common){
        mostCommon = common;
    }

    public String getAlphabet(){
        return alphabet;
    }

    private int[] countLetters(String s) {
        int[] counts = new int[26];
        String alphabet = getAlphabet();
        for (char c : s.toCharArray()) {
            char upperCase = Character.toUpperCase(c);
            int index = alphabet.indexOf(upperCase);
            if (index == -1) {
                continue;
            }
            counts[index]++;
        }
        return counts;
    }

    private int getKey(String s) {
        int[] counts = countLetters(s);
        int maxIndex = maxIndex(counts);
        int mostCommonPos = mostCommon - 'a';
        return (maxIndex-mostCommonPos+26)%26;
    }

    private int maxIndex(int[] counts) {
        int maxIndex = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > counts[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public String decrypt(String input){
        int key = getKey(input);
        CaesarCipher cc = new CaesarCipher(key);
        return cc.encrypt(input);

    }



}
