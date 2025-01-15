package src.model3.GladLibs;

import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.HashMap;

public class CodonCount {
    private HashMap<String,Integer> dnaMap;

    public CodonCount(){
        dnaMap = new HashMap<>();
    }

    private void buildCodonMap(int start, String dna){
        dnaMap.clear();
        int num = 0;
        int k = (dna.length()-start)/3;
        while (num <k){
            int head = (num*3)+start;
            int tail =  head+3;
            String codon = dna.substring(head,tail);
            if(!dnaMap.containsKey(codon)){
                dnaMap.put(codon,1);
            }else{
                dnaMap.put(codon,dnaMap.get(codon)+1);
            }
            num++;
        }

    }

    public String getMostCommonCodon(){
        int largest = 0;
        String maxKey = null;
        for(String codon: dnaMap.keySet()){

            int current = dnaMap.get(codon);
            if(current > largest){
                largest = current;
                maxKey = codon;
            }
        }

        return maxKey;
    }

    public void printCodonCounts(int start, int end){
        for(String key : dnaMap.keySet()){
            int count = dnaMap.get(key);
            if(count>=start && count<=end){
                System.out.println(key+" : "+count+"\t");
            }
        }
    }

    public void tester(){
        FileResource fr = new FileResource("src/model3/GladLibs/PracticeGladLibsData/dnaMystery1");
        String input = fr.asString().toUpperCase().trim();
        int max = 6;
        for(int i = 0; i < max; i++){
            buildCodonMap(i,input);
            System.out.println("--"+i+"--"+dnaMap);
            String commonCodon = getMostCommonCodon();
            System.out.println("comon:"+commonCodon);
            printCodonCounts(4,4);
        }




    }

    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
        cc.tester();

    }
}
