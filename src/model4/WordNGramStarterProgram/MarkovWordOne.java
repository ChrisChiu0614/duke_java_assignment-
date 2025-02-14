package src.model4.WordNGramStarterProgram;
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
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
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<String>();
		try {
			int i = 0;
			while ( i < myText.length) {
				int index = indexOf(myText, key, i);
				if (index == -1 || index + 1 >= myText.length) {
					break;
				}

				follows.add(myText[index + 1]);
				i = index +1;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	    return follows;
    }

	private int indexOf(String[] words, String target, int start){
		try {
			for(int i = start; i< words.length; i++){
				if(target.equals(words[i])){
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
		System.out.println(Arrays.toString(words));
		//“this” starting at 0, “this” starting at 3, “frog” starting at 0, “frog” starting at 5, “simple” starting at 2 and “test” starting at 5.
		System.out.println(indexOf(words,"starting",0));
		System.out.println(indexOf(words,"this",3));
		System.out.println(indexOf(words,"frog",0));
		System.out.println(indexOf(words,"frog",5));
		System.out.println(indexOf(words,"simple",2));
		System.out.println(indexOf(words,"test",5));
	}



}
