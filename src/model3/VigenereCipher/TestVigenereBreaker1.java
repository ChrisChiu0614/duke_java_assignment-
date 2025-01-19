package src.model3.VigenereCipher;

import edu.duke.URLResource;

import java.util.Arrays;

public class TestVigenereBreaker1 {

    public static void main(String[] args) {
        VigenereBreaker vb = new VigenereBreaker();
        //String path = "./src/model3/VigenereCipher/VigenereTestData/";
        String file = "secretmessage1.txt";
        //FileResource fr = new FileResource(path+"/"+file);
        URLResource urlResource = new URLResource("https://www.dukelearntoprogram.com/java/secretmessage1.txt");
        String data = urlResource.asString();
        //String data = fr.asString();

        int[] keys = vb.tryKeyLength(data,4,'e');
        System.out.println(Arrays.toString(keys));
        VigenereCipher vc = new VigenereCipher(keys);
        String output =  vc.decrypt(data);
        System.out.println("output="+output);
    }
}
