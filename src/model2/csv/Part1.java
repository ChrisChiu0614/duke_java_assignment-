package src.model2.csv;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Part1 {
    public static void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String info = countryInfo(parser,"Nauru");
        System.out.println(info);

        listExportersTwoProducts(parser,"cotton","flowers");
        parser = fr.getCSVParser();
        int num = numberOfExporters(parser,"cocoa");
        System.out.println(num);
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");



    }
    public static String countryInfo(CSVParser parser, String country){
        for(CSVRecord record:parser){
            String countryRecord = record.get("Country");
            if(countryRecord.equals(country)){
                String exportsRecord = record.get("Exports");
                String ValueRecord = record.get("Value (dollars)");
                return String.format("%s: %s: %s",countryRecord,exportsRecord,ValueRecord);
            }
        }
        return "NOT FOUND";
    }

    public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record:parser){

            String exportsRecord = record.get("Exports");
            if(exportsRecord.contains(exportItem1) && exportsRecord.contains(exportItem2)){
                String countryRecord = record.get("Country");
                System.out.println(countryRecord);
            }
        }

    }
    /*
    4. Write a method named numberOfExporters,
    which has two parameters, parser is a CSVParser,
     and exportItem is a String. This method returns the number of countries that export exportItem.
     For example, using the file exports_small.csv, this method called with
     the item “gold” would return 3.
     */
    public static int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record: parser){
            String exportsRecord = record.get("Exports");
            if(exportsRecord.contains(exportItem)){
                count++;
            }

        }
        return count;
    }
    /*
    5. Write a void method named bigExporters that has two parameters,
    parser is a CSVParser, and amount is a String in the format of a dollar sign,
    followed by an integer number with a comma separator every three digits from the right.
    An example of such a string might be “$400,000,000”.
    This method prints the names of countries and their Value amount for all countries whose
    Value (dollars) string is longer than the amount string.
    You do not need to parse either string value as an integer,
    just compare the lengths of the strings. For example,
    if bigExporters is called with the file exports_small.csv
    and amount with the string $999,999,999, then this method would print eight countries
    and their export values shown here:
     */
    public static void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record:parser){

            String ValueRecord = record.get("Value (dollars)");

            if(ValueRecord.length() > amount.length()){
                String countryRecord = record.get("Country");
                System.out.println(countryRecord+" "+ValueRecord);
            }
        }
    }

    public static void main(String[] args) {
        tester();
    }
}
