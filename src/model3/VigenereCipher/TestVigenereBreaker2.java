package src.model3.VigenereCipher;

import edu.duke.FileResource;

import java.util.HashSet;


public class TestVigenereBreaker2 {
    public static FileResource getFile(String name){
        String path = "./src/model3/VigenereCipher/messages/";
        return new FileResource(path+"/"+name);
    }
    public static void main(String[] args) {
        String name = "secretmessage2.txt";
        FileResource fr = getFile(name);
        String data = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        FileResource file = vb.getDictionaryFile("English");
        HashSet<String> dictionary = vb.readDictionary(file);
        String output = vb.breakForLanguage(data,dictionary);
        System.out.println(output);


    }
}
