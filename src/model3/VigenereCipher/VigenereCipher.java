package src.model3.VigenereCipher;

import edu.duke.FileResource;

import java.util.Arrays;

public class VigenereCipher {
    private CaesarCipher[] ciphers;

    public VigenereCipher(int[] key){
        ciphers = new CaesarCipher[key.length];
        for(int i = 0; i < key.length; i++){
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }

    public String encrypt(String input){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int len = ciphers.length;
        for(char c : input.toCharArray()){
            int key = i % len;
            char enc = ciphers[key].encryptLetter(c);
            sb.append(enc);
            i++;
        }
        return sb.toString();
    }

    public String decrypt(String input){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int len = ciphers.length;
        for(char c : input.toCharArray()){
            int key = i % len;
            char enc = ciphers[key].decryptLetter(c);
            sb.append(enc);
            i++;
        }
        return sb.toString();
    }

    public String toString(){
        return Arrays.toString(ciphers);
    }


    public static void main(String[] args) {
        int[] key = new int[]{17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(key);
        vc.toString();

        String path = "./src/model3/VigenereCipher/VigenereTestData/";
        String file = "titus-small.txt";
        FileResource fr = new FileResource(path+"/"+file);
        String data = fr.asString();

        String vcEnc = vc.encrypt(data);
        System.out.println(vcEnc);

        String vcDec = vc.decrypt(vcEnc);
        System.out.println(vcDec);

        boolean b = data.equals(vcDec);
        System.out.println(b);
    }


}
