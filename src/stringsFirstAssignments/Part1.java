package src.stringsFirstAssignments;

public class Part1 {

    public static String findSimpleGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex==-1){
            return "";
        }
        int endIndex = dna.indexOf("TAA",startIndex+3);
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
        // DNA with no “ATG”
        String dna1 = "CCCGGGTTTAA";
        System.out.println("Test 1 (No ATG): " + findSimpleGene(dna1));

        // DNA with no “TAA”
        String dna2 = "ATGCCCGGG";
        System.out.println("Test 2 (No TAA): " + findSimpleGene(dna2));

        // DNA with no “ATG” or “TAA”
        String dna3 = "CCCGGGTTT";
        System.out.println("Test 3 (No ATG or TAA): " + findSimpleGene(dna3));

        // DNA with ATG, TAA and the substring between them is a multiple of 3
        String dna4 = "ATGCCCGGGTAA";
        System.out.println("Test 4 (Valid gene, multiple of 3): " + findSimpleGene(dna4));

        // DNA with ATG, TAA and the substring between them is not a multiple of 3
        String dna5 = "ATGCGTAA";
        System.out.println("Test 5 (Invalid gene, not multiple of 3): " + findSimpleGene(dna5));


    }


    public static void main(String[] args) {
        testSimpleGene();
    }
}