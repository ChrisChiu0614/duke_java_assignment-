package stringsSecondAssignments;

public class Part3 {
    final static String TAA = "TAA";
    final static String TAG = "TAG";
    final static String TGA = "TGA";
    final static String ATG = "ATG";

    public static int findStopCodon(String dna, int startIndex, String stopCodon){
        String lowerDNA = dna.toLowerCase();
        String lowerCodon = stopCodon.toLowerCase();
        int currIndex = lowerDNA.indexOf(lowerCodon,startIndex);
        while(currIndex!=-1){
            if((currIndex-startIndex)%3==0){
                return currIndex;
            }else{
                startIndex = currIndex;
            }
            currIndex = lowerDNA.indexOf(lowerCodon,startIndex+3);
        }

        return dna.length();
    }

    public static String findGene(String dna){
        String lowerDNA = dna.toLowerCase();
        String lowerStartCodon = ATG.toLowerCase();

        int startIndex = lowerDNA.indexOf(lowerStartCodon);
        if(startIndex==-1){
            System.out.println("no startIndex");
            return "";
        }
        int indexOfTAA = findStopCodon(lowerDNA,startIndex,TAA.toLowerCase());
        int indexOfTAG = findStopCodon(lowerDNA,startIndex,TAG.toLowerCase());
        int indexOfTGA = findStopCodon(lowerDNA,startIndex,TGA.toLowerCase());
        int minStopIndex = Math.min(indexOfTAA,Math.min(indexOfTAG,indexOfTGA));

        if(minStopIndex==dna.length()){
            System.out.println("no stopIndex");
            return "";
        }
        return dna.substring(startIndex,minStopIndex+3);
    }

    public static void printAllGenes(String dna){
        String lowerDNA = dna.toLowerCase();
        String lowerStartCodon = ATG.toLowerCase();
        int startIndex = lowerDNA.indexOf(lowerStartCodon);
        while (startIndex!=-1) {

            int indexOfTAA = findStopCodon(lowerDNA, startIndex, TAA.toLowerCase());
            int indexOfTAG = findStopCodon(lowerDNA, startIndex, TAG.toLowerCase());
            int indexOfTGA = findStopCodon(lowerDNA, startIndex, TGA.toLowerCase());
            int minStopIndex = Math.min(indexOfTAA, Math.min(indexOfTAG, indexOfTGA));


            if (minStopIndex == dna.length()) {
                startIndex = lowerDNA.indexOf(lowerStartCodon, startIndex + 3);
                continue;
            }

            System.out.println(dna.substring(startIndex, minStopIndex + 3));

            startIndex = lowerDNA.indexOf(lowerStartCodon, minStopIndex + 3);

        }

    }
    public static int countGenes(String dna){
        String lowerDNA = dna.toLowerCase();
        String lowerStartCodon = ATG.toLowerCase();
        int count = 0;
        int startIndex = lowerDNA.indexOf(lowerStartCodon);
        while (startIndex!=-1) {

            int indexOfTAA = findStopCodon(lowerDNA, startIndex, TAA.toLowerCase());
            int indexOfTAG = findStopCodon(lowerDNA, startIndex, TAG.toLowerCase());
            int indexOfTGA = findStopCodon(lowerDNA, startIndex, TGA.toLowerCase());
            int minStopIndex = Math.min(indexOfTAA, Math.min(indexOfTAG, indexOfTGA));


            if (minStopIndex == dna.length()) {
                startIndex = lowerDNA.indexOf(lowerStartCodon, startIndex + 3);
                continue;
            }
            count++;
            System.out.println(dna.substring(startIndex, minStopIndex + 3));

            startIndex = lowerDNA.indexOf(lowerStartCodon, minStopIndex + 3);

        }
        return count;
    }

    public static void testCountGenes(){
        // 測試用例
        String dna1 = "CCCGGGTTT"; // 無 "ATG"
        String dna2 = "ATGCCCTAAATGCCCTAG"; // 有多個有效基因
        String dna3 = "ATGCCCGGGATGTAG"; // 有兩個有效基因
        String dna4 = "ATGCCCTAAGGGTAG"; // 有一個基因，另一段無效
        String dna5 = "ATGCCCCCCTAAATGTTTTGA"; // 有多個基因，並且有無效段
        String dna6 = "CCATGCCCCGGGTTTATGCCCTAATAGATGCCCCCC";
        // 第1個 "ATG" 沒有有效終止密碼子
        // 第2個 "ATG" (從索引9開始) 有終止密碼子 "TAA"
        // 第3個 "ATG" (從索引22開始) 有終止密碼子 "TAG"

        // 測試執行
        System.out.println("Testing DNA: " + dna1);
        System.out.println(countGenes(dna1));

        System.out.println("Testing DNA: " + dna2);
        System.out.println(countGenes(dna2));

        System.out.println("Testing DNA: " + dna3);
        System.out.println(countGenes(dna3));

        System.out.println("Testing DNA: " + dna4);
        System.out.println(countGenes(dna4));

        System.out.println("Testing DNA: " + dna5);
        System.out.println(countGenes(dna5));

        System.out.println("Testing DNA6: " + dna6);
        System.out.println(countGenes(dna6));
    }

    public static void main(String[] args) {
        testCountGenes();
    }


}
