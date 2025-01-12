package src.model3.caesarCipher;

import edu.duke.FileResource;

import java.lang.reflect.Array;
import java.util.Arrays;

public class WordLengths {

    public static void countWordLengths(FileResource resource, int[] counts){

        for(String str:resource.words()){
            int notLetter = 0;
            if(!Character.isLetter(str.charAt(0))){
                notLetter++;
            }
            if(!Character.isLetter(str.charAt(str.length()-1))){
                notLetter++;
            }
            System.out.println("str:"+str+"index="+(str.length()-notLetter));
            counts[str.length()-notLetter]++;
        }
    }

    public static int indexOfMax(int[] values){
        int maxIndex = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i]>values[maxIndex]){
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    public static void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr,counts);
        int maxIndex = indexOfMax(counts);

        System.out.println(Arrays.toString(counts));
        System.out.println(maxIndex);

    }



    public static void main(String[] args) {
        testCountWordLengths();

    }

}
