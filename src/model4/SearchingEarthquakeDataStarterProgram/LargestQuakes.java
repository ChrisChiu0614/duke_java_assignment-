package src.model4.SearchingEarthquakeDataStarterProgram;

import edu.duke.FileResource;

import java.util.*;
public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "./src/model4/SearchingEarthquakeDataStarterProgram/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        for(QuakeEntry qe : list){
            System.out.println(qe.toString());

        }

        System.out.println(indexOfLargest(list));


    }

    public int indexOfLargest(ArrayList<QuakeEntry> data){
        if(data==null){
            throw new IllegalArgumentException();
        }
        return data.stream()
                .max((quakeEntry1, quakeEntry2)->Double.compare(quakeEntry1.getMagnitude(),quakeEntry2.getMagnitude()))
                .map(quakeEntry->{
                    for(int i = 0; i < data.size(); i++){
                        if(data.get(i).equals(quakeEntry)){
                            return i;
                        }
                    }
                    return -1;
                }).orElse(-1);
    }

    public static void main(String[] args) {
        LargestQuakes lq = new LargestQuakes();
        lq.findLargestQuakes();
    }
}
