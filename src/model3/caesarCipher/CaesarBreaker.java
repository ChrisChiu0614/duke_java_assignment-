package src.model3.caesarCipher;

import edu.duke.FileResource;

import java.util.Arrays;

import static src.model3.caesarCipher.CaesarCipher.encryptTwoKeys;

public class CaesarBreaker {
    private String alphabet;

    public CaesarBreaker() {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }

    public String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int getKey(String s) {
        int[] counts = countLetters(s);
        int maxIndex = maxIndex(counts);
        int dkey = (maxIndex - 4 + 26) % 26; //

        return dkey;
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

    private int[] countLetters(String s) {
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            char upperCase = Character.toUpperCase(c);
            int index = this.alphabet.indexOf(upperCase);
            if (index == -1) {
                continue;
            }
            counts[index]++;
        }
        return counts;
    }

    public void testDecrypt() {
        CaesarBreaker cb = new CaesarBreaker();

        FileResource fr = new FileResource();
        String encStr = encryptTwoKeys(fr.asString(), 23, 2);
        String enc1 = cb.halfOfString(encStr, 0);
        String enc2 = cb.halfOfString(encStr, 1);
        int key1 = getKey(enc1);
        int key2 = getKey(enc2);

        CaesarCipher cc = new CaesarCipher();

        String decrypt1 = cc.decrypt(enc1, key1);
        String decrypt2 = cc.decrypt(enc2, key2);
        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < decrypt1.length() || i < decrypt2.length()) {
            if (i < decrypt1.length()) {
                sb.append(decrypt1.charAt(i));
            }
            if (i < decrypt2.length()) {
                sb.append(decrypt2.charAt(i));
            }
            i++;
        }


        System.out.println(fr.asString());
        System.out.println(encStr);
        System.out.println(enc1);
        System.out.println(enc2);
        System.out.println(key1);
        System.out.println(key2);
        System.out.println(decrypt1);
        System.out.println(decrypt2);
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        CaesarBreaker cb = new CaesarBreaker();
        cb.testDecrypt();

    }
}
