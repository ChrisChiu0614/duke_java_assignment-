package src.model3.ArrayList;

import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies(){
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }

    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String str : fr.words()) {
            String lowerCase = str.toLowerCase();
            int index = myWords.indexOf(lowerCase);
            if (index == -1) {
                myWords.add(lowerCase);
                myFreqs.add(1);
            } else {
                myFreqs.set(index, myFreqs.get(index) + 1);
            }
        }
    }

    public static void main(String[] args) {

    }




}
