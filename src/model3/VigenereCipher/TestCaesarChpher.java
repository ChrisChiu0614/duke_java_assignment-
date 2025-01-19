package src.model3.VigenereCipher;

import edu.duke.FileResource;

public class TestCaesarChpher {

    public static void main(String[] args) {
        String path = "./src/model3/VigenereCipher/VigenereTestData/";
        String file = "titus-small.txt";
        FileResource fr = new FileResource(path+"/"+file);
        String data = fr.asString();

        CaesarCipher cc = new CaesarCipher(8);
        String erc = cc.encrypt(data);
        System.out.println(erc);

        String dec = cc.decrypt(erc);
        System.out.println(dec);

        boolean t = data.equals(dec);
        System.out.println(t);


    }
}
