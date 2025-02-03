package src.model4.EfficientSortStarterProgram;

import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int result = q1.getInfo().compareTo(q2.getInfo());
        return result == 0 ? Double.compare(q1.getDepth(), q2.getDepth()) : result;
    }
}
