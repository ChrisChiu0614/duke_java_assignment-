package src.model3.ArrayList;

import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }

    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String str : fr.words()) {
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

    public int findIndexOfMax() {
        int maxIndex = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > myFreqs.get(maxIndex)) {
                maxIndex = i;
            }

        }

        return maxIndex;
    }

    public void tester() {
        findUnique();
        int maxIndex = findIndexOfMax();
        System.out.println("Number of unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
        System.out.println("The word that occurs most often and its count are:" + myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }


}
