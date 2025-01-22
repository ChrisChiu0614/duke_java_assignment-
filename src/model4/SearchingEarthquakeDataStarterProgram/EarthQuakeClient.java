package src.model4.SearchingEarthquakeDataStarterProgram;

import java.util.*;
import java.util.stream.Collectors;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
                                                   double magMin) {

        return quakeData.stream()
                .filter(quakeEntry -> quakeEntry.getMagnitude() > magMin)
                .collect(Collectors.toCollection(() -> new ArrayList<QuakeEntry>()));
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
                                                      double distMax,
                                                      Location from) {

        return quakeData.stream()
                .filter(quakeEntry -> ((double) from.distanceTo(quakeEntry.getLocation()) / 1000) < distMax)
                .collect(Collectors.toCollection(() -> new ArrayList<QuakeEntry>()));
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
                                               double minDepth, double maxDepth) {
        return quakeData.stream()
                .filter(quakeEntry -> quakeEntry.getDepth() > minDepth && quakeEntry.getDepth() < maxDepth)
                .collect(Collectors.toCollection(() -> new ArrayList<QuakeEntry>()));
    }

    public void filterByPhrase(ArrayList<QuakeEntry> quakeData,
                               String where,
                               String phrase) {
        ArrayList<QuakeEntry> answer;

        switch (where.toLowerCase()){
            case "start":
                quakeData.stream().filter(quakeEntry -> quakeEntry.getInfo().substring())
                break;
            case "end":

                break;
            case "any":

                break;
        }


    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list) {
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "./src/model4/SearchingEarthquakeDataStarterProgram/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        ArrayList<QuakeEntry> bigQuakesList = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : bigQuakesList) {
            System.out.println(qe.toString());
        }
        System.out.println("Found " + bigQuakesList.size() + " quakes that match that criteria");

    }

    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "./src/model4/SearchingEarthquakeDataStarterProgram/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city = new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> distanceList = filterByDistanceFrom(list, 1000, city);
        for (QuakeEntry qe : distanceList) {
            System.out.print((double) city.distanceTo(qe.getLocation()) / 1000);
            System.out.println(" " + qe.getInfo());

        }
        System.out.println("Found " + distanceList.size() + " quakes that match that criteria");
    }

    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "./src/model4/SearchingEarthquakeDataStarterProgram/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        double minDepth = -10000.0;
        double maxDepth = -5000.0;

        ArrayList<QuakeEntry> depthList = filterByDepth(list, minDepth, maxDepth);
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        for (QuakeEntry qe : depthList) {

            System.out.println(qe.toString());

        }
        System.out.println("Found " + depthList.size() + " quakes that match that criteria");
    }


    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "./src/model4/SearchingEarthquakeDataStarterProgram/data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public static void main(String[] args) {
        EarthQuakeClient eqc = new EarthQuakeClient();
        //eqc.bigQuakes();
        //eqc.closeToMe();
        eqc.quakesOfDepth();
    }

}
