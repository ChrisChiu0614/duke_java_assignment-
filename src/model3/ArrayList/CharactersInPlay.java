package src.model3.ArrayList;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    public CharactersInPlay(){
        names = new ArrayList();
        counts = new ArrayList();
    }

    public void update(String person){

        int index = names.indexOf(person);
        if(index==-1){
            names.add(person);
            counts.add(1);
        }else{
            counts.set(index,counts.get(index)+1);
        }
    }

    public void findAllCharacters(){
        FileResource fr = new FileResource();
        for(String str : fr.lines()){
            int personIndex = str.indexOf(".");
            if(personIndex==-1){
                continue;
            }
            String person = str.substring(0,personIndex);
            update(person);
        }
    }
    public void charactersWithNumParts(int num1, int num2){
        for(int i = 0; i < names.size(); i++){
            int count = counts.get(i);
            if(count>=num1 && count<=num2){
                System.out.println(names.get(i)+" "+counts.get(i));
            }

        }
    }

    public void tester(){
        findAllCharacters();;
        charactersWithNumParts(10,15);

        int max = 0;
        for(int i = 0; i < names.size(); i++){
            int count = counts.get(i);

            if(count>counts.get(max)){
                max = i;

            }

        }
        //System.out.println(names.get(max)+" "+counts.get(max));
    }

    public static void main(String[] args) {
        CharactersInPlay cip = new CharactersInPlay();
        cip.tester();
    }
}
