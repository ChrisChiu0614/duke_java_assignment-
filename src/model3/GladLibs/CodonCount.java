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
        int dnaLen = dna.length();
        int end = start+3;

        while(end<dnaLen){
            String codon = dna.substring(start,end);
            boolean mach = true;
            for(char c:codon.toCharArray()) {
                if(c!='C' && c!='G' && c!='T' && c!='A'){
                    mach = false;
                    break;
                }
            }
            if(mach){
                if(!dnaMap.containsKey(codon)){
                    dnaMap.put(codon,1);
                }else{
                    dnaMap.put(codon,dnaMap.get(codon)+1);
                }
            }

            start+=3;
            end+=3;
        }
    }

    public String getMostCommonCodon(){
        String maxKey = "";
        for(String codon: dnaMap.keySet()){

            if(maxKey.isEmpty()){
                maxKey = codon;
                continue;
            }
            if(dnaMap.get(codon)> dnaMap.get(maxKey)){
                maxKey = codon;
            }
        }

        return dnaMap.get(maxKey).toString();
    }

    public void printCodonCounts(int start, int end){
        for(String key : dnaMap.keySet()){
            int count = dnaMap.get(key);
            if(count>=start && count<=end){
                System.out.println(key+" : "+count);
            }
        }
    }

    public void tester(){
        FileResource fr = new FileResource();
        String input = fr.asString().toUpperCase();

        for(int i = 0; i < input.length(); i++){
            buildCodonMap(i,input);
        }

        String commonCodon = getMostCommonCodon();
        System.out.println(commonCodon);

        printCodonCounts(1,5);
    }

    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
        cc.tester();
    }
}
