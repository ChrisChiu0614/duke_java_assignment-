package src.model2.babyBirth;

import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class BabyBirth {
    //final static String PATH = "./src/babyBirth/testing";
    final static String PATH = ".//src/babyBirth/us_babynames/us_babynames_by_year";
    public static void totalBirths(CSVParser parser) {
        int totalOfBoy = 0;
        int totalOfGirl = 0;
        int totalBirth = 0;
        int numOfGirlName = 0;
        int numOfBoyName = 0;

        for (CSVRecord record : parser) {
            String gender = record.get(1);
            int amount = Integer.parseInt(record.get(2));
            switch (gender) {
                case "F":

                    totalOfGirl += amount;
                    numOfGirlName++;
                    break;
                case "M":
                    totalOfBoy += amount;
                    numOfBoyName++;
                    break;
            }
            totalBirth += amount;
        }
        System.out.println("Boy: " + totalOfBoy+",number of boys' names: "+numOfBoyName);
        System.out.println("Girl: " + totalOfGirl+",number of girls' names: "+numOfGirlName);
        System.out.println("Total: " + totalBirth);
    }

    /*
    format filename and get file.
     */
    public static CSVParser getParser(int year) {
        String fileName = String.format("yob%s.csv", year);
        File file = new File(PATH + "/" + fileName);
        FileResource fr = new FileResource(file);

        return fr.getCSVParser(false);
    }

    ;

    public static int getRank(int year, String name, String gender) {
        CSVParser parser = getParser(year);
        int rank = 0;
        for (CSVRecord row : parser) {
            String nameInRow = row.get(0);
            if (row.get(1).equals(gender)) {
                rank++;
                if (nameInRow.equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }

    public static String getName(int year, int rank, String gender) {
        CSVParser parser = getParser(year);
        int index = 0;
        for (CSVRecord row : parser) {
            if (row.get(1).equals(gender)) {
                index++;
                if (index == rank) {
                    return row.get(0);
                }
            }
        }
        return "NO NAME";
    }

    public static int calculateTotalBirth(int stopRank, CSVParser parser, String gender) {
        int sum = 0;
        int index = 0;
        for (CSVRecord row : parser) {
            String genderInRow = row.get(1);
            if (genderInRow.equals(gender)) {
                index++;
                if (index == stopRank) {
                    return sum;
                }

                int num = Integer.parseInt(row.get(2));
                sum += num;


            }
        }
        return -1;
    }

    /*
    example:Isabella born in 2012 would be Sophia if she was born in 2014.
     */
    public static void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        if (rank != -1) {
            String nameA = getName(year, rank, gender);
            String nameB = getName(newYear, rank, gender);
            if (!nameA.equals("NO NAME") && !nameB.equals("NO NAME")) {
                String output = String.format("%s born in %s would be %s if she was born in %s.", nameA, year, nameB, newYear);
                System.out.println(output);
            }
        }

    }

    public static int getYearByFile(File f) {
        String fileName = f.getName();
        int index = fileName.indexOf("yob");
        if (index == -1) {
            return -1;
        }
        return Integer.parseInt(fileName.substring(index + 3, index + 7));
    }

    public static int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int bestRank = Integer.MAX_VALUE;
        int bestYear = -1;
        for (File f : dr.selectedFiles()) {
            int year = getYearByFile(f);
            int rank = getRank(year, name, gender);
            if (rank != -1 && rank < bestRank) {
                bestRank = rank;
                bestYear = year;
            }

        }
        return bestRank == Integer.MAX_VALUE ? -1 : bestYear;
    }

    public static double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double rankSum = 0;
        boolean isRanked = false;
        int index = 0;
        for (File f : dr.selectedFiles()) {
            int year = getYearByFile(f);
            int rank = getRank(year, name, gender);
            if (rank != -1) {
                rankSum += rank;
                index++;
            }
            if (index != 0) {
                isRanked = true;
            }

        }
        return isRanked ? rankSum / index : -1;
    }

    public static int getTotalBirthsRankedHigher(int year, String name, String gender) {

        CSVParser parser = getParser(year);
        int rank = getRank(year, name, gender);
        if (rank != -1) {
            return calculateTotalBirth(rank, parser, gender);
        }
        return -1;
    }

    public static void tester() {
//        CSVParser parser = getParser(1900);
//        totalBirths(parser);

//        int rank = getRank(1960,"Emily","F");
//        System.out.println(rank);

//        String name = getName(1980, 350,"F");
//        System.out.println(name);

        whatIsNameInYear("Susan",1972,2014,"F");
        whatIsNameInYear("Owen",1974,2014,"M");
//        int yearH = yearOfHighestRank("Genevieve","F");
//        System.out.println(yearH);

//        double avgRank = getAverageRank("Susan", "F");
//        System.out.println(avgRank);

//        int total = getTotalBirthsRankedHigher(1990, "Emily", "F");
//        System.out.println(total);

    }

    public static void main(String[] args) {
        tester();
    }
}
