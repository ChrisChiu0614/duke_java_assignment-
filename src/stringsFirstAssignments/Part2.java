package src.stringsFirstAssignments;

public class Part2 {
    public static String findSimpleGene(String dna,String startCodon, String stopCodon){
        boolean isLowerCase = dna.toLowerCase().equals(dna);
        if(isLowerCase){
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        int startIndex = dna.indexOf(startCodon);
        if(startIndex==-1){
            return "";
        }
        int endIndex = dna.indexOf(stopCodon,startIndex+3);
        if(endIndex==-1){
            return "";
        }
        String gene = dna.substring(startIndex,endIndex+3);
        return gene.length()%3==0?gene:"";
    }

    public static void testSimpleGene(){
        /*
        such as: DNA with no “ATG”, DNA with no “TAA”, DNA with no “ATG” or “TAA”,
        NA with ATG, TAA and the substring between them is a multiple of 3 (a gene),
        and DNA with ATG, TAA and the substring between them is not a multiple of 3.
         */

        // 測試大寫 DNA
        String dna1 = "ATGGGTTAAGTC";
        System.out.println("Test 1 (Uppercase DNA): " + findSimpleGene(dna1, "ATG", "TAA"));

        // 測試小寫 DNA
        String dna2 = "gatgctataat";
        System.out.println("Test 2 (Lowercase DNA): " + findSimpleGene(dna2, "ATG", "TAA"));

        // 測試無 ATG
        String dna3 = "CCGGTTTAA";
        System.out.println("Test 3 (No ATG): " + findSimpleGene(dna3, "ATG", "TAA"));

        // 測試無 TAA
        String dna4 = "ATGCCCGGG";
        System.out.println("Test 4 (No TAA): " + findSimpleGene(dna4, "ATG", "TAA"));

        // 測試有效基因（3 的倍數）
        String dna5 = "ATGCCCGGGTAA";
        System.out.println("Test 5 (Valid gene): " + findSimpleGene(dna5, "ATG", "TAA"));

        // 測試無效基因（非 3 的倍數）
        String dna6 = "ATGCCTAA";
        System.out.println("Test 6 (Invalid gene): " + findSimpleGene(dna6, "ATG", "TAA"));



    }

    public static void main(String[] args) {
        testSimpleGene();
    }

}
