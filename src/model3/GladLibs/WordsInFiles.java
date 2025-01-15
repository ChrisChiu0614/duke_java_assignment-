package src.model3.GladLibs;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> words;

    public WordsInFiles(){
        words = new HashMap<>();
    }

    private void addWordsFromFile(File f){
        String fName = f.getName();
        FileResource fr = new FileResource(f);
        for(String word :fr.words()){
            word = word.trim();
            if(!words.containsKey(word)){
                ArrayList<String> files = new ArrayList<>();
                files.add(fName);
                words.put(word,files);
            }else{
                ArrayList<String> files = words.get(word);
                if (!files.contains(fName)){
                    files.add(fName);
                }
            }

        }
    }

    public void buildWordFileMap(){
        words.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }

    private int maxNumber(){
        int maxNum = 0;
        for(String key : words.keySet()){
            maxNum = Math.max(maxNum,words.get(key).size());
        }
        return maxNum;
    }

    private ArrayList<String> wordsInNumFiles(Integer number){
        ArrayList<String> res = new ArrayList<>();
        for(String key : words.keySet()){
            if(words.get(key).size()==number){
                res.add(key);
            };
        }
        return res;
    }

    private void printFilesIn(String word){
        ArrayList<String> res = words.get(word);
        if(res!=null){
            for(String fName : res){
                System.out.println(word+" appears in the files: "+fName);
            }
        }
    }

    public void tester(){
        buildWordFileMap();
       int sum = 0;
        for(String key:words.keySet()){
           sum+=words.get(key).size();
       }
        System.out.println(sum);
        /*
       int maxNum = maxNumber();
        System.out.println("maxNum:"+maxNum);
        ArrayList<String> wordInList = wordsInNumFiles(3);
        System.out.println("wordInList: "+wordInList);
        for(String word : wordInList){
            printFilesIn(word);
        }
        */

    }

    public static void main(String[] args) {
        WordsInFiles wif = new WordsInFiles();
        wif.tester();
    }
}
