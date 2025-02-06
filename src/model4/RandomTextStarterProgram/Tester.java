package src.model4.RandomTextStarterProgram;

import edu.duke.FileResource;

import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {
        MarkovRunner runner = new MarkovRunner();
        //runner.runMarkovZero();
        //runner.runMarkovOne();
        runner.runMarkovFour();
        //runner.runMarkovModel();
        Tester tester = new Tester();
        //tester.testGetFollows();
        //tester.testGetFollowsWithFile();

    }

    public void testGetFollows(){
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        ArrayList<String> follows =  markov.getFollows("th");
        for(String str : follows){
            System.out.println(str);
        }
        System.out.println("--size-->"+follows.size());
    }

    public void testGetFollowsWithFile(){
        String pathWithFile = "./src/model4/RandomTextStarterProgram/data/melville.txt";
        FileResource fr = new FileResource(pathWithFile);
        String myTest = fr.asString();

        MarkovOne markov = new MarkovOne();
        markov.setTraining(myTest);
        ArrayList<String> follows =  markov.getFollows("th");

        for(String str : follows){
            System.out.println(str);
        }

        System.out.println("--size-->"+follows.size());

    }
}
