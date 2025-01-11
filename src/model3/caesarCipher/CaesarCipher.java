package src.model3.caesarCipher;

public class CaesarCipher {
    private String alphabet;
    private String shiftAlphabet;

    public CaesarCipher(int key){
        alphabet ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }

    public String tempShiftAlphabet(int key){
        return alphabet.substring(key) + alphabet.substring(0, key);
    }

    //Encryption method
    public String encrypt(String data) {
        return processText(data, this.alphabet, this.shiftAlphabet);
    }

    // Decryption method
    public String decrypt(String data) {
        return processText(data, this.shiftAlphabet, this.alphabet);
    }

    // Core processing logic
    private static String processText(String data, String sourceAlphabet, String targetAlphabet) {
        boolean isUpperCase = data.equals(data.toUpperCase());
        StringBuilder ans = new StringBuilder();
        for (char c : data.toCharArray()) {
            char upperChar = Character.toUpperCase(c);
            int index = sourceAlphabet.indexOf(upperChar);
            if (index == -1) {
                ans.append(c);
                continue;
            }

            char targetChar = targetAlphabet.charAt(index);
            ans.append(isUpperCase ? targetChar : Character.toLowerCase(targetChar));

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
        return max;
    }




    // Test the encryption and decryption process
    public static void testEncryptDecrypt() {
        CaesarCipher caesarCipher = new CaesarCipher(17);
        String enstr = caesarCipher.encrypt("You used arrays to crack a Caesar cipher\n" +
                "• Counting frequencies, using indexing\n" +
                "• Both encrypt and decrypt used indexing");
        System.out.println("Encrypted: " + enstr);

        // Find the most frequent letter's index in the encrypted text
        int freq = caesarCipher.freqIndex(enstr);

        // Calculate the offset (key) using freq - 4
        int key = (freq - 4 + 26) % 26; // Ensure key is always non-negative
        System.out.println("Detected Key: " + key);

        // Decrypt the text using the calculated key
        String deStr = caesarCipher.decrypt(enstr);
        System.out.println("Decrypted: " + deStr);
    }

    public static void main(String[] args) {
        testEncryptDecrypt();

    }
}
