package src.model4.EfficientSortStarterProgram;

public class Test {
    public static void main(String[] args) {
        DifferentSorters ds = new DifferentSorters();
//        ds.sortWithCompareTo();
//        ds.sortByTitleAndDepth();
        ds.sortByLastWordInTitleThenByMagnitude();
    }
}
