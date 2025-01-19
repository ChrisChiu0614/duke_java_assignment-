package src.model3.VigenereCipher;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> set = new HashSet<>();
        for(String str : fr.lines()){
            set.add(str.toLowerCase());
        }
        return set;
    }

    public int countWords(String message, HashSet<String> dictionary){
        if (message == null || message.isEmpty()) return 0;

        String[] words = message.split("\\W+");
        int count = 0;
        for (String word : words) {
            if (!word.isEmpty() && dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }
    public FileResource getDictionaryFile(String language){
        String path = "./src/model3/VigenereCipher/dictionaries/";
        return new FileResource(path+"/"+language);
    }

    public String breakForLanguage(String encrypted,HashSet<String> dictionary){
        String ans = "";
        int max = 0;
        int[] finalKeys = new int[0];
        /**
         * Note that there is nothing special about 100;
         * we will just give you messages with key lengths in the range 1–100.
         * If you did not have this information, you could iterate all the way to encrypted.length().
         * Your program would just take a bit longer to run.
         */
        for(int i = 1; i <=100; i++){
            int[] keys = tryKeyLength(encrypted,i,'e');
            VigenereCipher vc = new VigenereCipher(keys);
            String output =  vc.decrypt(encrypted);
            int count = countWords(output, dictionary);
            if(count>max){
                max = count;
                ans = output;
                finalKeys = keys;
            }
//            if(i==38){
//                System.out.println("---38---:"+count);
//            }
        }
//        System.out.println("max:"+max);
//        System.out.println("finalKeys"+Arrays.toString(finalKeys));
//        System.out.println(finalKeys.length);
        return ans;
    }

    public char mostCommonCharIn(HashSet<String> dictionary){
       return dictionary.stream()
               .flatMapToInt(str->str.chars())
               .mapToObj(c->(char)c)
               .collect(Collectors.groupingBy(c->c,Collectors.counting()))
               .entrySet()
               .stream()
               .max(Collectors.toMap(Map.Entry.comparingByValue()))
               .orElseThrow()->new IllegalStateException("No characters found"))
               .getKey();

    }




}
