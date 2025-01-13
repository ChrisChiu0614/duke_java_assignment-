package src.model3.caesarCipher;

public class CaesarCipherTwo {
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;

    public CaesarCipherTwo(int key1, int key2){
        shiftedAlphabet1 = alphabet.substring(key1)+alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2)+alphabet.substring(0,key2);
    }

    public String getAlphabet(){
        return this.alphabet;
    }

    public String encrypt(String input){
        String alphabet = getAlphabet();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            char upperCase = Character.toUpperCase(c);
            int index = alphabet.indexOf(upperCase);
            if(index==-1){
                sb.append(c);
                continue;
            }
            boolean isUpper = Character.isUpperCase(c);
            char newChar;
            // even
            if((i&1)==0){
                newChar = isUpper?shiftedAlphabet1.charAt(index):Character.toLowerCase(shiftedAlphabet1.charAt(index));
            }
            // odd
            else{
                newChar = isUpper?shiftedAlphabet2.charAt(index):Character.toLowerCase(shiftedAlphabet2.charAt(index));

            }
            sb.append(newChar);
        }


        return sb.toString();
    }


    public String decrypt(String input){
        String evenStr = halfOfString(input, 0);
        String oddStr = halfOfString(input, 1);
        int key1 = getKey(evenStr);
        int key2 = getKey(oddStr);
        CaesarCipherTwo cct = new CaesarCipherTwo(26-key1,26-key2);
        return cct.encrypt(input);

    }

    private String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    private int getKey(String s) {
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
}
