package src.csv;

import edu.duke.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class Part2 {
    public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0;
        int size = 0;
        for (CSVRecord record: parser){
            double temperature = Double.parseDouble(record.get("TemperatureF"));
            String humidity = record.get("Humidity");
            if(humidity.equals("N/A") || Double.parseDouble(humidity) < value){
                continue;
            }
            double humidityValue = Double.parseDouble(humidity);
            sum+=value;
            size++;
        }
        return sum/size;
    }

    public static double averageTemperatureInFile(CSVParser parser){
        return averageValue(parser,"TemperatureF");
    }



    public static double averageValue(CSVParser parser, String averageColum){
        double sum = 0;
        int size = 0;
        for (CSVRecord record: parser){
            String str = record.get(averageColum);
            double value = Double.parseDouble(str);
            sum+=value;
            size++;
        }
        return sum/size;
    }

    public static CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidity = null;

        for(File file : dr.selectedFiles()){
            FileResource fr = new FileResource(file);
            CSVRecord currRecord = lowestHumidityInFile(fr.getCSVParser());
            lowestHumidity = lowestHumidityOfTwo(lowestHumidity,currRecord);

        }
        return lowestHumidity;
    }

    public static CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidity = null;
        for (CSVRecord record: parser){
            lowestHumidity = lowestHumidityOfTwo(lowestHumidity,record);
        }
        return lowestHumidity;
    }
    public static CSVRecord lowestHumidityOfTwo(CSVRecord a, CSVRecord b){
        String humidityOfb = b.get("Humidity");
        if(humidityOfb.equals("N/A")){
            return a;
        } else if (a==null) {
            return b;
        }else{
            double ahumidity = Double.parseDouble(a.get("Humidity"));
            double bhumidity = Double.parseDouble(b.get("Humidity"));
            return ahumidity<=bhumidity?a:b;
        }
    }


    public static String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestRecord = null;
        String coldestFileName = "";
        for(File file : dr.selectedFiles()){
            FileResource fr = new FileResource(file);
            CSVRecord currRecord = coldestHourInFile(fr.getCSVParser());
            coldestRecord = coldestOfTwo(coldestRecord,currRecord);
            if(coldestRecord.get("TemperatureF").equals(currRecord.get("TemperatureF"))){
                coldestFileName = file.getName();
            }

        }

        return coldestFileName;
    }

    public static CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestRecord = null;
        for (CSVRecord record: parser){
            coldestRecord = coldestOfTwo(coldestRecord,record);
        }
        return coldestRecord;
    }

    public static CSVRecord coldestOfTwo(CSVRecord a, CSVRecord b){
        if(a==null){
            return b;
        }else{
            double aTemperature = Double.parseDouble(a.get("TemperatureF"));
            double bTemperature = Double.parseDouble(b.get("TemperatureF"));
            return aTemperature<bTemperature?a:b;
        }
    }

    public static void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord coldestRecord = coldestHourInFile(fr.getCSVParser());
        String output =  String.format("the coldest day is %s and occur in %s",coldestRecord.get("TemperatureF"),coldestRecord.get("TimeEST"));
        System.out.println(output);
    }

    public static void testFileWithColdestTemperature(){
        System.out.println(fileWithColdestTemperature());
    }

    public static void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        String output =  String.format("Lowest Humidity was %s at %s",csv.get("Humidity"),csv.get("DateUTC"));
        System.out.println(output);
    }

    public static void testLowestHumidityInManyFiles(){
        CSVRecord csv = lowestHumidityInManyFiles();
        String output =  String.format("Lowest Humidity was %s at %s",csv.get("Humidity"),csv.get("DateUTC"));
        System.out.println(output);
    }

    public static void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg =  averageTemperatureInFile(parser);
        String output =  String.format("Average temperature in file is %.15f",avg);
        System.out.println(output);
    }

    public static void main(String[] args) {
        //testColdestHourInFile();
        //testFileWithColdestTemperature();
        //testLowestHumidityInFile();
        //testLowestHumidityInManyFiles();
        testAverageTemperatureInFile();
    }
}
