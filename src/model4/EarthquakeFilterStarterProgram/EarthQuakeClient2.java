package src.model4.EarthquakeFilterStarterProgram;

import java.util.*;
import java.util.stream.Collectors;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (f.satisfies(qe)) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public void quakesWithFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "./src/model4/EarthquakeFilterStarterProgram/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

//        Filter f = new MagnitudeFilter(4.0, 5.0);
//        ArrayList<QuakeEntry> list1 = filter(list, f);
//        f = new DepthFilter(-35000.0, -12000.0);
//        ArrayList<QuakeEntry> m8 = filter(list1, f);
//        for (QuakeEntry qe : m8) {
//            System.out.println(qe);
//        }
//        System.out.println("read data for " + m8.size() + " quakes");


//        Location location = new Location(35.42, 139.43);
//        Filter f = new DistanceFilter(location,10000000 );
//        ArrayList<QuakeEntry> list1 = filter(list, f);
//        f = new PhraseFilter("end","Japan");
//        ArrayList<QuakeEntry> list2 = filter(list1, f);
//        for (QuakeEntry qe : list2) {
//            System.out.println(qe);
//        }
//        System.out.println("read data for " + list2.size() + " quakes");

//        Location denver = new Location(39.7392, -104.9903);
//        Filter f = new DistanceFilter(denver,1000000);
//        ArrayList<QuakeEntry> list1 = filter(list, f);
//        f = new PhraseFilter("end","a");
//        ArrayList<QuakeEntry> list2 = filter(list1, f);
//        for (QuakeEntry qe : list2) {
//            System.out.println(qe);
//        }
//        System.out.println("read data for " + list2.size() + " quakes");

//        Filter f = new MagnitudeFilter(3.5, 4.5);
//        ArrayList<QuakeEntry> list1 = filter(list, f);
//        f = new DepthFilter(-55000.0, -20000.0);
//        ArrayList<QuakeEntry> m8 = filter(list1, f);
//        for (QuakeEntry qe : m8) {
//            System.out.println(qe);
//        }
//        System.out.println("read data for " + m8.size() + " quakes");

        Filter f = new MagnitudeFilter(0.0,5.0);
        ArrayList<QuakeEntry> list1 = filter(list, f);
        Location denmark = new Location(55.7308, 9.1153);
        f = new DistanceFilter(denmark,3000000);
        ArrayList<QuakeEntry> list2 = filter(list1, f);
        f = new PhraseFilter("any","e");
        ArrayList<QuakeEntry> list3 = filter(list2, f);
        for (QuakeEntry qe : list3) {
            System.out.println(qe);
        }
        System.out.println("read data for " + list3.size() + " quakes");

    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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

    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "./src/model4/EarthquakeFilterStarterProgram/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        //MatchAllFilter maf = new MatchAllFilter();
        //maf.addFilter(new )
        ArrayList<QuakeEntry> list2 = list.stream()
                .sorted((qe1,qe2)->-Double.compare(qe1.getMagnitude(),qe2.getMagnitude()))
                .collect(Collectors.toCollection(()->new ArrayList<>()));
        int i = 0;
        for(QuakeEntry qe: list2){
            System.out.println("i = "+i+" "+qe);
            if(i==50){
                break;
            }
            i++;
        }
    }


    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "./src/model4/EarthquakeFilterStarterProgram/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();
        //maf.addFilter(new PhraseFilter("start","Quarry Blast"));
        //maf.addFilter(new PhraseFilter("end","Alaska"));
        maf.addFilter(new PhraseFilter("any","Can"));
        ArrayList<QuakeEntry> list1 = filter(list,maf);
        for(QuakeEntry qe : list1){
            System.out.println(qe);
        }
        System.out.println("read data for "+list1.size()+" quakes");
    }


    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "./src/model4/EarthquakeFilterStarterProgram/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();
        //maf.addFilter(new DepthFilter(-12000.0 , -10000.0));
        maf.addFilter(new DepthFilter(-4000.0 , -2000.0));

        ArrayList<QuakeEntry> list1 = filter(list,maf);
        for(QuakeEntry qe : list1){
            System.out.println(qe);
        }
        System.out.println("read data for "+list1.size()+" quakes");
    }


    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "./src/model4/EarthquakeFilterStarterProgram/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);

        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0,2.0));
        maf.addFilter(new DepthFilter(-100000.0, -10000.0));
        maf.addFilter(new PhraseFilter("any","a"));
        ArrayList<QuakeEntry> list1 = filter(list,maf);
        for(QuakeEntry qe : list1){
            System.out.println(qe);
        }
        System.out.println("read data for "+list1.size()+" quakes");
    }

    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "./src/model4/EarthquakeFilterStarterProgram/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);

        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0,3.0));
        Location tulsa = new Location(36.1314, -95.9372);
        maf.addFilter(new DistanceFilter(tulsa,10000000));
        maf.addFilter(new PhraseFilter("any","Ca"));
        ArrayList<QuakeEntry> list1 = filter(list,maf);

        for(QuakeEntry qe : list1){
            System.out.println(qe);
        }
        System.out.println("read data for "+list1.size()+" quakes");

        for(Filter filter : maf.getFilters()){
            System.out.println(filter.getName());
        }
    }

    public static void main(String[] args) {
        EarthQuakeClient2 eqc = new EarthQuakeClient2();
        eqc.quakesWithFilter();
        //eqc.testMatchAllFilter();
        //eqc.testMatchAllFilter2();
        //eqc.quakesOfDepth();
        //eqc.quakesByPhrase();
        //eqc.findLargestQuakes();
    }

}
