package src.model3.WebLogProgram;
/**
 * Write a description of class LogAnalyzer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogAnalyzer {

    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        // complete constructor
        records = new ArrayList<>();
    }

    public void readFile(String filename) {
        // complete method
        FileResource fr = new FileResource(filename);

        for (String str : fr.lines()) {
            LogEntry log = WebLogParser.parseEntry(str);
            records.add(log);
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for (LogEntry le : records) {
            String ipAddress = le.getIpAddress();
            if(!uniqueIPs.contains(ipAddress)){
                uniqueIPs.add(ipAddress);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num){
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if(statusCode>num){
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> ips = new ArrayList<>();
        for(LogEntry le : records){
            String date = le.getAccessTime().toString();
            String ipAddress = le.getIpAddress();
            if(date.contains(someday) && !ips.contains(ipAddress)){
                ips.add(ipAddress);
            }
        }

        return ips;
    }

    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for (LogEntry le : records) {
            String ipAddress = le.getIpAddress();
            int statusCode =le.getStatusCode();
            if(!uniqueIPs.contains(ipAddress) && (statusCode>=low && statusCode<=high) ){
                uniqueIPs.add(ipAddress);
            }
        }
        return uniqueIPs.size();
    }

    public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> counts = new HashMap<>();
        for (LogEntry le : records) {
            String ipAddress = le.getIpAddress();
            if(!counts.containsKey(ipAddress)){
                counts.put(ipAddress,1);
            }else{
                counts.put(ipAddress,counts.get(ipAddress)+1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
        int max = 0;
        for(String ip : counts.keySet()){
            Integer count = counts.get(ip);
            max = Math.max(count,max);
        }
        System.out.println("most num visits :"+ max);
        return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
        ArrayList<String> mostIPs = new ArrayList<>();
        int max = mostNumberVisitsByIP(counts);
        for(String ip : counts.keySet()){
            Integer count = counts.get(ip);
            if(count==max){
                mostIPs.add(ip);
            }
        }
        System.out.println("iPs Most Visits :"+ mostIPs);
        return mostIPs;
    }

    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> ipsForDays = new HashMap<>();
        int start =4;
        int end = start+6;
        for(LogEntry le: records){

            String date = le.getAccessTime().toString().substring(start,end);

            if(!ipsForDays.containsKey(date)){
                ArrayList<String> ipsList = new ArrayList<>();
                ipsList.add(le.getIpAddress());
                ipsForDays.put(date, ipsList);
            }else{
                ipsForDays.get(date).add(le.getIpAddress());
            }
        }
        return ipsForDays;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsForDays){
        int max = 0;
        String dayWithMost = "";
        for(String date : iPsForDays.keySet()){
             int count = iPsForDays.get(date).size();
             if(count>max){
                 max=count;
                 dayWithMost = date;
             }

        }
        return String.format("the day most visited as %s.",dayWithMost);
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dayWithMostIPVisits, String day){
        ArrayList<String> iPsWithMostVisitsOnDay = new ArrayList<>();
        if(dayWithMostIPVisits.containsKey(day)){
            ArrayList<String> ipsList = dayWithMostIPVisits.get(day);
            for(String ip : ipsList){
                if(!iPsWithMostVisitsOnDay.contains(ip)){
                    iPsWithMostVisitsOnDay.add(ip);
                }
            }

        }
        return iPsWithMostVisitsOnDay;
    }


}
