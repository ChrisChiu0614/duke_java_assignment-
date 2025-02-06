package src.model4.RandomTextStarterProgram;

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int n;
    public MarkovModel(int number) {
        myRandom = new Random();
        n = number;
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
        int index = myRandom.nextInt(myText.length()-n);
        String key = myText.substring(index, index + n);
        sb.append(key);

        for(int k=0; k < numChars-n; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size()==0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            // delete ket[0] + next and keep char of myNumber count
            key = key.substring(1)+next;
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
