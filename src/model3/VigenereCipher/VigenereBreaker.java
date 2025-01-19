package src.model3.VigenereCipher;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.Arrays;
import java.util.HashSet;

public class VigenereBreaker {

    public String sliceString(String message, int whichSlice, int totalSlices){
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i+=totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon){
        CaesarCracker cck = new CaesarCracker(mostCommon);
        int key;
        int[] keys = new int[klength];
        for(int i = 0; i < klength; i++){
            key = cck.getKey(sliceString(encrypted,i,klength));
            keys[i] = key;
        }
        return keys;
    }

    public String breakVigenere(){
        FileResource fr = new FileResource();
        return fr.asString();
    }

    public void readDictionary(FileResource fr){
        HashSet<String> set = new HashSet<>();
        for(String str : fr.lines()){
            set.add(str.toLowerCase());
        }
    }

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
