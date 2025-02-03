package src.model4.EarthquakeSortStarterProgram;
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){
        if(null==quakes || from<0){
            throw new IllegalArgumentException();
        }
        int maxIndex = from;
        double max = Double.NEGATIVE_INFINITY;
        for(int i = from; i < quakes.size(); i++){
            double depth = quakes.get(i).getDepth();
            System.out.println("--depth:"+depth);
            if(depth>max){
                max = depth;
                maxIndex = i;
            }
        }
        System.out.println("max "+max);
        return maxIndex;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> quakes){
        for(int i = 0; i < quakes.size(); i++){
            int index = getLargestDepth(quakes,i);
            System.out.println("-----"+quakes.get(index).toString());
            QuakeEntry oldQe = quakes.get(i);
            QuakeEntry newQe = quakes.get(index);
            quakes.set(i,newQe);
            quakes.set(index,oldQe);
        }

    }


    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "./src/model4/EarthquakeSortStarterProgram/data/nov20quakedatasmall.atom";
        String source = "./src/model4/EarthquakeSortStarterProgram/data/earthquakeDataSampleSix2.atom";
        //String source = "./src/model4/EarthquakeSortStarterProgram/data/earthquakeDataSampleSix1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
//        for (QuakeEntry qe: list) {
//            System.out.println(qe);
//        }
//        sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        sortByMagnitudeWithCheck(list);
        System.out.println("EarthQuakes in sorted order:");
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }


    public void swap(ArrayList<QuakeEntry> quakeData, int x, int y){
        QuakeEntry temp = quakeData.get(x);
        quakeData.set(x, quakeData.get(y));
        quakeData.set(y, temp);
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        for(int i = 0; i < quakeData.size()-1-numSorted; i++){
            double magnitude = quakeData.get(i).getMagnitude();
            double nextMagnitude = quakeData.get(i+1).getMagnitude();
            if(nextMagnitude<magnitude){
                swap(quakeData,i, i+1);
            }
        }
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        for(int i = 0; i < in.size()-1; i++){
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            if(checkInSortedOrder(in)){
                System.out.println("how many passes were needed to sort :" + (i+1));
                break;
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        System.out.println("read data for "+in.size()+" quakes");
        for (QuakeEntry qe: in) {
            System.out.println(qe);
        }
        for (int i = 0; i < in.size()-1; i++){
            onePassBubbleSort(in, i);
            System.out.println("Printing Quakes after pass "+i);
            for (QuakeEntry qe: in) {
                System.out.println(qe);
            }

        }
    }
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        for(int i = 0; i < quakes.size()-1; i++){
            double magnitude = quakes.get(i).getMagnitude();
            double nextMagnitude = quakes.get(i+1).getMagnitude();
            if(nextMagnitude<magnitude){
                return false;
            }
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        for (int i = 0; i < in.size()-1; i++){
            onePassBubbleSort(in,i);
            if(checkInSortedOrder(in)){
                System.out.println("how many passes were needed to sort :" + (i+1));
                break;
            }
        }
    }


    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
}
