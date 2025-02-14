package src.model4.WordNGramStarterProgram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MarkovWordTwo implements IMarkovModel{

    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }

        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        try {
            int i = 0;
            while ( i < myText.length) {
                int index = indexOf(myText, key1, key2, i);
                if (index == -1 || index + 2 >= myText.length) {
                    break;
                }

                follows.add(myText[index + 2]);
                i = index +1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return follows;
    }

    private int indexOf(String[] words, String target1, String target2, int start){
        try {
            for(int i = start; i< words.length-1; i++){
                if(target1.equals(words[i]) && target2.equals(words[i+1])){
                    return i;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public void testIndexOf(){
        String[] words = "this is just a test yes this is a simple test".split(" ");

    }
}
