package src.model4.RandomTextStarterProgram;

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne {
    private String myText;
    private Random myRandom;

    public MarkovOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index + 1);
        sb.append(key);

        for(int k=0; k < numChars-1; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size()==0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }

        return sb.toString();
    }

    public ArrayList<String> getFollows(String key){
        int index = 0;
        ArrayList<String> follows = new ArrayList<>();
        while(index < myText.length()){
            index =  myText.indexOf(key,index,myText.length()-1);
            if(index==-1){
                break;
            }

            int pos = index+key.length();
            if(pos < myText.length()){
                String keyword = myText.substring(pos, pos+1);
                follows.add(keyword);
            }

            index+=key.length();

        }
        return follows;
    }
}
