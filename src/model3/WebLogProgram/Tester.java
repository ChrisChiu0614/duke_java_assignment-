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
        logAnalyzer.printAll();
    }

    public static void main(String[] args) {
        Tester test = new Tester();
        test.testLogAnalyzer();
    }
}
