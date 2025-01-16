package src.model3.WebLogProgram;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

import java.util.*;

public class Tester
{
    private final String PATH = "src/model3/WebLogProgram";
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(PATH+"/short-test_log");
        //logAnalyzer.readFile(PATH+"/weblog-short_log");
        logAnalyzer.printAll();
        int ips = logAnalyzer.countUniqueIPs();
        System.out.println("ips:"+ips);
        logAnalyzer.printAllHigherThanNum(200);
        System.out.println("------");
        ArrayList<String> ipOnDay =  logAnalyzer.uniqueIPVisitsOnDay("Sep 14");
        System.out.println("Sep 14:"+ipOnDay.size());
        ipOnDay.clear();
        ipOnDay =  logAnalyzer.uniqueIPVisitsOnDay("Sep 30");
        System.out.println("Sep 30:"+ipOnDay.size());

        int ipsInRange = logAnalyzer.countUniqueIPsInRange(200,299);
        System.out.println(ipsInRange);
        ipsInRange = logAnalyzer.countUniqueIPsInRange(300,399);
        System.out.println(ipsInRange);

    }

    public void testLogAnalyzer2() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(PATH+"/weblog1_log");
        //logAnalyzer.printAllHigherThanNum(400);
        ArrayList<String> ipOnDay =  logAnalyzer.uniqueIPVisitsOnDay("Mar 24");
        System.out.println("Mar 24:"+ipOnDay.size());
        int ipsInRange = logAnalyzer.countUniqueIPsInRange(300,399);
        System.out.println(ipsInRange);
    }

    public void testCountingWebsiteVisits(){
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(PATH+"/weblog3-short_log");
        HashMap<String,Integer> countIPs = logAnalyzer.countVisitsPerIP();
        int max = logAnalyzer.mostNumberVisitsByIP(countIPs);
        //System.out.println(max);
        ArrayList<String> ismostVisits = logAnalyzer.iPsMostVisits(countIPs);
        //System.out.println(ismostVisits);
        HashMap<String, ArrayList<String>> iPsForDays = logAnalyzer.iPsForDays();
        System.out.println(iPsForDays);
        String dayWithMost = logAnalyzer.dayWithMostIPVisits(iPsForDays);
        System.out.println(dayWithMost);
        ArrayList<String> iPsWithMostVisitsOnDay = logAnalyzer.iPsWithMostVisitsOnDay(iPsForDays, "Sep 30");
        System.out.println(iPsWithMostVisitsOnDay);

    }



    public static void main(String[] args) {
        Tester test = new Tester();
        //test.testLogAnalyzer2();
        test.testCountingWebsiteVisits();
    }

}
