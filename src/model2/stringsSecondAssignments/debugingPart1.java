package src.model2.stringsSecondAssignments;

public class debugingPart1 {
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            //System.out.println("index:"+index);
            if (index == -1 || index >= input.length() - 3){
                break;
            }
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+3);
            //System.out.println("index after updating " + index);
        }
    }
    public void test() {
        //findAbc("abcd");
        //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");

        findAbc("abcabcabcabca");
    }

    public static void main(String[] args) {
        debugingPart1 part1 = new debugingPart1();
        part1.test();
    }
}
