package src.model4.SearchingEarthquakeDataStarterProgram;

import edu.duke.FileResource;

import java.util.*;
import java.util.stream.Collectors;

public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "./src/model4/SearchingEarthquakeDataStarterProgram/data/nov20quakedatasmall.atom";
        String source = "./src/model4/SearchingEarthquakeDataStarterProgram/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        for (QuakeEntry qe : list) {
            System.out.println(qe.toString());

        }

        //System.out.println(indexOfLargest(list));
        ArrayList<QuakeEntry> largestList = getLargest(list,5);
        System.out.println("--------");
        for (QuakeEntry qe : largestList) {
            System.out.println(qe.toString());

        }

    }

    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        return data.stream()
                .max((quakeEntry1, quakeEntry2) -> Double.compare(quakeEntry1.getMagnitude(), quakeEntry2.getMagnitude()))
                .map(quakeEntry -> {
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).equals(quakeEntry)) {
                            return i;
                        }
                    }
                    return -1;
                }).orElse(-1);
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        if (null == quakeData || howMany == 0) {
            throw new IllegalArgumentException();
        }
        return quakeData.stream()
                .sorted((quakeEntry1,quakeEntry2)->-Double.compare(quakeEntry1.getMagnitude(),quakeEntry2.getMagnitude())) //using -Double.compare DESC
                .limit(howMany)
                .collect(Collectors.toCollection(()->new ArrayList<>()));
    }

    public static void main(String[] args) {
        LargestQuakes lq = new LargestQuakes();
        lq.findLargestQuakes();
    }
}
