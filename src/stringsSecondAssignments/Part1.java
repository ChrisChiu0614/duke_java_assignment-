package stringsSecondAssignments;

public class Part1 {
    final static String TAA = "TAA";
    final static String TAG = "TAG";
    final static String TGA = "TGA";
    final static String ATG = "ATG";

    public static int findStopCodon(String dna, int startIndex, String stopCodon){
        String lowerDNA = dna.toLowerCase();
        String lowerCodon = stopCodon.toLowerCase();
        int currIndex = lowerDNA.indexOf(lowerCodon,startIndex+3);
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

    public static void testFindStopCodon(){
        // 測試用例
        System.out.println("Test 1: " + findStopCodon("ATGAAATAA", 0, "TAA")); // 應返回 6
        System.out.println("Test 2: " + findStopCodon("ATGAAAAAA", 0, "TAA")); // 應返回 9 (無 stopCodon)
        System.out.println("Test 3: " + findStopCodon("ATGAAATAAATGTGA", 0, "TGA")); // 應返回 12
        System.out.println("Test 4: " + findStopCodon("CCCATGAAAGGGTTTTAG", 3, "TAG")); // 應返回 15
        System.out.println("Test 5: " + findStopCodon("ATGAAATAA", 0, "TAG")); // 應返回 9 (無 stopCodon)
        System.out.println("Test 6: " + findStopCodon("ATGCCCTAATAG", 0, "TAA")); // 應返回 6
        System.out.println("Test 7: " + findStopCodon("ATGCCCTAATAG", 0, "TAG")); // 應返回 9
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

    public static void testFindGene() {
        // 測試用例
        String dna1 = "CCCGGGTTT"; // 無 "ATG"
        String dna2 = "ATGCCCTAA"; // 有 "ATG"，且有一個有效終止密碼子
        String dna3 = "ATGCCCCTAGTAA"; // 有 "ATG"，且有多個有效終止密碼子
        String dna4 = "ATGCCC"; // 有 "ATG"，但無有效終止密碼子
        String dna5 = "ATGCCCCCCCTAA"; // 有 "ATG"，只有一個有效終止密碼子在非 3 的倍數位置

        // 測試案例執行
        System.out.println("DNA: " + dna1 + ", Gene: " + findGene(dna1));
        System.out.println("DNA: " + dna2 + ", Gene: " + findGene(dna2));
        System.out.println("DNA: " + dna3 + ", Gene: " + findGene(dna3));
        System.out.println("DNA: " + dna4 + ", Gene: " + findGene(dna4));
        System.out.println("DNA: " + dna5 + ", Gene: " + findGene(dna5));
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

    public static void testPrintAllGenes(){
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
        printAllGenes(dna1);

        System.out.println("Testing DNA: " + dna2);
        printAllGenes(dna2);

        System.out.println("Testing DNA: " + dna3);
        printAllGenes(dna3);

        System.out.println("Testing DNA: " + dna4);
        printAllGenes(dna4);

        System.out.println("Testing DNA: " + dna5);
        printAllGenes(dna5);

        System.out.println("Testing DNA6: " + dna6);
        printAllGenes(dna6);
    }


    public static void main(String[] args) {
        //testFindStopCodon();
        //testFindGene();
        //testPrintAllGenes();
        System.out.println(findGene("ATGCTAACTAGCTGACTAAT"));

    }
}
