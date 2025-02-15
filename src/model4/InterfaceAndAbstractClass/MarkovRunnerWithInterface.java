package src.model4.InterfaceAndAbstractClass;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunnerWithInterface {

	public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
		markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
		String pathWithFile = "./src/model4/RandomTextStarterProgram/data/melville.txt";
        FileResource fr = new FileResource(pathWithFile);
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 4;
//		MarkovZero mz = new MarkovZero();
//        runModel(mz, st, size, seed);
//
//
//		MarkovOne mOne = new MarkovOne();
//        runModel(mOne, st, size, seed);
//
//
//		MarkovModel mThree = new MarkovModel(3);
//        runModel(mThree, st, size, seed);
//
//
//		MarkovFour mFour = new MarkovFour();
//        runModel(mFour, st, size, seed);
		EfficientMarkovModel mFour = new EfficientMarkovModel(4);
		runModel(mFour, st, size, seed);

    }

	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

	public void testHashMap(){
		EfficientMarkovModel mtwo = new EfficientMarkovModel(2);
		String st = "yes-this-is-a-thin-pretty-pink-thistle";
		int size = 50;
		int seed = 42;
		runModel(mtwo, st, size, seed);

	}

	public void compareMethods(){
		EfficientMarkovModel m1 = new EfficientMarkovModel(2);
		EfficientMarkovModel m2 = new EfficientMarkovModel(2);
		int size = 1000;
		int seed = 42;
		String pathWithFile = "./src/model4/RandomTextStarterProgram/data/hawthorne.txt";
		FileResource fr = new FileResource(pathWithFile);
		String st = fr.asString();
		System.out.println("start:"+System.nanoTime());
		runModel(m1, st, size, seed);
		System.out.println("end:"+System.nanoTime());
		runModel(m2, st, size, seed);
		System.out.println("end1:"+System.nanoTime());
	}

}
