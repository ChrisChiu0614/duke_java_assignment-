package src.model3.WebLogProgram;
/**
 * Write a description of class LogAnalyzer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

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



}
