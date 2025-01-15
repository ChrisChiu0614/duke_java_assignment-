package src.model3.GladLibs;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap {
	private HashMap<String, ArrayList<String>> myMap;
	private Random myRandom;
	private ArrayList<String> usedCategories;
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "src/model3/GladLibs/data";

	public GladLibMap(){
		myMap = new HashMap<>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
		usedCategories = new ArrayList<>();
	}

	public GladLibMap(String source){
		myMap = new HashMap<>();
		initializeFromSource(source);
		myRandom = new Random();
		usedCategories = new ArrayList<>();
	}
	
	private void initializeFromSource(String source) {
		String[] label = {"adjective","animal","color","country","fruit","name","noun","timeframe","verb"};
		for(int i = 0; i < label.length; i++){
			String name = label[i];
			ArrayList<String> adjectiveList= readIt(source+"/"+name+".txt");
			myMap.put(name,adjectiveList);
		}
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("number")) {
			return "" + myRandom.nextInt(50) + 5;
		}
		ArrayList<String> list = myMap.get(label);
		if(list!=null){

			return randomFrom(list);
		}

		return "**UNKNOWN**";
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
		String categorie = w.substring(first+1,last);
		if(myMap.get(categorie)!=null && !usedCategories.contains(categorie)){
			usedCategories.add(categorie);
		}

		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public void makeStory(){
	    System.out.println("\n");
		String data = dataSourceDirectory+"/madtemplate.txt";
		String story = fromTemplate(data);
		printOut(story, 60);
		System.out.println(totalWordsInMap());
		System.out.println(totalWordsConsidered());
	}

	private int totalWordsInMap(){
		int sum = 0;
		for(String key : myMap.keySet()){
			sum +=myMap.get(key).size();
		}
		return sum;
	}

	private int totalWordsConsidered(){
		int sum = 0;
		for(String category : usedCategories){
			sum+=myMap.get(category).size();

		}
		return sum;
	}

	public static void main(String[] args) {
		GladLibMap glm = new GladLibMap();
		glm.makeStory();

	}
	


}
