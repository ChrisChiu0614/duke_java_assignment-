package src.stringsSecondAssignments;

public class Part2 {

    public static int howMany(String stringa, String stringb){
        int count = 0;
        int stringaLen = stringa.length();
        int index = stringb.indexOf(stringa);
        while (index!=-1){
            count++;
            if(index+stringaLen>stringb.length()){
                break;
            }
            index = stringb.indexOf(stringa,index+stringaLen);
        }
        return count;
    }

    public static void testHowMany(){
        // 情境：stringa 在 stringb 中多次出現且不重疊
        // stringa = "GAA", stringb = "ATGAACGAATTGAATC"
        // 預期：GAA 出現在索引 2, 7, 13，共 3 次
        System.out.println("Test 1: " + howMany("GAA", "ATGAACGAATTGAATC")); // 預期：3

        // 情境：stringa 部分重疊，但只計算不重疊次數
        // stringa = "AA", stringb = "ATAAAA"
        // 預期：AA 出現在索引 2 和 4，計算為 2 次
        System.out.println("Test 2: " + howMany("AA", "ATAAAA")); // 預期：2

        // 情境：stringa 在 stringb 中只出現一次
        // stringa = "CC", stringb = "ATCCGATTG"
        // 預期：CC 在索引 2 出現一次
        System.out.println("Test 3: " + howMany("CC", "ATCCGATTG")); // 預期：1

        // 情境：stringa 在 stringb 中不存在
        // stringa = "GGG", stringb = "ATCCGATTG"
        // 預期：GGG 在 stringb 中未出現，計算為 0 次
        System.out.println("Test 4: " + howMany("GGG", "ATCCGATTG")); // 預期：0

        // 情境：stringa 和 stringb 完全相等
        // stringa = "AATG", stringb = "AATG"
        // 預期：AATG 與 stringb 完全相等，計算為 1 次
        System.out.println("Test 5: " + howMany("AATG", "AATG")); // 預期：1
    }

    public static void main(String[] args) {
        testHowMany();
    }
}
