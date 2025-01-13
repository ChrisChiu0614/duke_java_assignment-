package src.model3.caesarCipher;

import edu.duke.FileResource;

public class TestCaesarCipher {

    private String alphabet;

    public void simpleTests(){
        //FileResource fr = new FileResource();
        //String data = fr.asString();
        String data = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipher cc = new CaesarCipher(15);
        String encrData = cc.encrypt(data);
        System.out.println(encrData);
        breakCaesarCipher(encrData);




    }

    public void breakCaesarCipher(String input){
        int[] counts = countLetters(input);
        int maxIndex = maxIndex(counts);
        int dkey = (maxIndex-4+26)%26;
        CaesarCipher cc = new CaesarCipher(dkey);
        String decrData = cc.decrypt(input, dkey);
        System.out.println(decrData);


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
        String alphabet ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
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

    public static void main(String[] args) {
        TestCaesarCipher tcc = new TestCaesarCipher();
        tcc.simpleTests();
    }
}
