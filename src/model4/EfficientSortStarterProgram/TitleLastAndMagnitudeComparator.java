package src.model4.EfficientSortStarterProgram;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int q1LastIndex = q1.getInfo().lastIndexOf(" ") + 1;
        int q2LastIndex = q2.getInfo().lastIndexOf(" ") + 1;

        int result = q1.getInfo().substring(q1LastIndex).compareTo(q2.getInfo().substring(q2LastIndex));
        return result == 0 ? Double.compare(q1.getMagnitude(), q2.getMagnitude()) : result;
    }
}
