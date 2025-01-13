package src.model3.caesarCipher;

import edu.duke.FileResource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    public Set<String> getDictionary() {
        FileResource fr = new FileResource("./src/model3/caesarCipher/20k.txt");
        Set<String> dictionary = new HashSet<>();
        for (String str : fr.lines()) {
            dictionary.add(str);
        }
        return dictionary;
    }

    public int countWordsInDictionary(String text, Set<String> dictionary) {
        int count = 0;
        String[] words = text.split("\\W+");// is not letter

        for (String str : words) {
            if (dictionary.contains(str.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    public String breakTwoKeysWithDictionary(String encrypted, Set<String> dictionary) {
        CaesarBreaker cb = new CaesarBreaker();
        String enc1 = cb.halfOfString(encrypted, 0);
        String enc2 = cb.halfOfString(encrypted, 1);
        String bestDecrypt = "";
        String keys = "";
        int bestCount = 0;
        for (int k1 = 0; k1 < 26; k1++) {
            for (int k2 = 0; k2 < 26; k2++) {

                CaesarCipher cc = new CaesarCipher();

                String decrypt1 = cc.decrypt(enc1, k1);
                String decrypt2 = cc.decrypt(enc2, k2);
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
                int count = countWordsInDictionary(sb.toString(), dictionary);
                if (count > bestCount) {
                    bestCount = count;
                    bestDecrypt = sb.toString();
                    keys = k1 + "," + k2;
                }


            }

        }
        System.out.println(bestDecrypt);
        return keys;
    }

    public void test() {
        CaesarBreaker cb = new CaesarBreaker();
        Set<String> dictionary = cb.getDictionary();
        FileResource fr = new FileResource();
        String encStr = fr.asString();
        String keys = breakTwoKeysWithDictionary(encStr, dictionary);

        System.out.println(keys);

    }


    public void testDecrypt() {
        CaesarBreaker cb = new CaesarBreaker();

        //FileResource fr = new FileResource();

        //String encStr = encryptTwoKeys(fr.asString(), 23, 2);
        String data = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        String encStr = encryptTwoKeys(data, 14, 24);
        System.out.println("encSTR:"+encStr);
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
        System.out.println(key1 + "," + key2);
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        CaesarBreaker cb = new CaesarBreaker();
        cb.testDecrypt();
        //cb.test();

    }
}
