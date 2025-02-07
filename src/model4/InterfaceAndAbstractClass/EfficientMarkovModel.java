package src.model4.InterfaceAndAbstractClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel{

    private int n;
    private HashMap<String, ArrayList<String>> markovMap;

    public EfficientMarkovModel(int number) {
        myRandom = new Random();
        n = number;
        printHashMapInfo();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
        markovMap = buildMap();
    }

    public HashMap<String, ArrayList<String>> buildMap(){
        HashMap<String, ArrayList<String>> cach = new HashMap<>();
        int index = 0;
        while(index <= myText.length()-n){
            String key = myText.substring(index, index+n);
            if(cach.containsKey(key)){
                ArrayList<String> list = cach.get(key);
                String word = myText.substring(index+key.length(),index+key.length()+1);
                list.add(word);
            }else{
                ArrayList<String> list = new ArrayList<>();
                String word = myText.substring(index+key.length(),index+key.length()+1);
                list.add(word);
                cach.put(key,list);
            }
            index++;
        }
        return cach;
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
            ArrayList<String> follows = markovMap.get(key);

            if (follows == null || follows.isEmpty()) {
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

    public void printHashMapInfo(){
        int largestKey = Integer.MIN_VALUE;
        int smallestKey = Integer.MAX_VALUE;

        for(String key : markovMap.keySet()){
            System.out.println("key + [" + key + "] : value [" + markovMap.get(key) + "]");
            int size = markovMap.get(key).size();
            if(size>largestKey){
                largestKey=size;
            }
            if(size<smallestKey){
                smallestKey=size;
            }
        }
        System.out.println("1. markovMap Size: "+ markovMap.size());
        System.out.println("2. largestKey: "+largestKey);
        System.out.println("3. smallestKey: "+smallestKey);
    }



    @Override
    public String toString() {
        return "MarkovModel of order "+n;
    }
}
