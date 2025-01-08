package src.babyBirth;

import edu.duke.*;
import org.apache.commons.csv.*;

public class BabyBirth {

    public static void totalBirths(CSVParser parser){
        for(CSVRecord record :parser){
            String name = record.get(0);
            String sex = record.get(1);
            String amount = record.get(2);
            System.out.printf("%s %s %s\r\n",name,sex,amount);
        }
    }

    public static void testTotalBirths(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        totalBirths(parser);
    }

    public static void main(String[] args) {
        testTotalBirths();
    }
}
