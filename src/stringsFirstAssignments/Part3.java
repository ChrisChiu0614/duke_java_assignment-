package stringsFirstAssignments;

public class Part3 {

    public static boolean twoOccurrences(String stringa, String stringb){
        if(stringa.isEmpty()||stringb.isEmpty()){
            return false;
        }

        int count = 0;
        int index = stringb.indexOf(stringa);
        while(index !=-1){
            count++;
            if(count>1){
                return true;
            }
            index = stringb.indexOf(stringa,index+1);
        }

        return false;
    }
    public static String lastPart(String stringa, String stringb){
        int index = stringb.indexOf(stringa);
        if(index==-1){
            return stringb;
        }
        return stringb.substring(index+stringa.length());

    }


    public static void testing(){
        System.out.println(twoOccurrences("an", "banana")); // true
        System.out.println(twoOccurrences("na", "banana")); // true
        System.out.println(twoOccurrences("z", "banana"));  // false
        System.out.println(twoOccurrences("ban", "banana")); // false
        System.out.println(twoOccurrences("", "banana"));    // false
        System.out.println(twoOccurrences("an", ""));        // false
    }



    public static void main(String[] args) {
        //boolean bool = twoOccurrences("atg", "ctgtatgta");
        //System.out.println(bool);
        testing();
        System.out.println(lastPart("zoo","forest"));
        System.out.println(lastPart("an","banana"));
    }

}
