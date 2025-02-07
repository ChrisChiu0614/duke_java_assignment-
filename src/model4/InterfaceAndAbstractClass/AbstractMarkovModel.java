package src.model4.InterfaceAndAbstractClass;
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key){
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

    @Override
    public String toString() {
        return "MarkovModel of order " +myRandom;
    }

}
