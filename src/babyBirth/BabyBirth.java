package src.babyBirth;

import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class BabyBirth {
    final static String PATH = "./src/babyBirth/testing";

    public static void totalBirths(CSVParser parser){
        int totalOfBoy = 0;
        int totalOfGirl = 0;
        int totalBirth = 0;

        for(CSVRecord record :parser){
            String gender = record.get(1);
            int amount =  Integer.parseInt(record.get(2));
            switch (gender){
                case "F":
                    totalOfGirl +=amount;
                    break;
                case "M":
                    totalOfBoy +=amount;
                    break;
            }
            totalBirth+=amount;
        }
        System.out.println("Boy: "+ totalOfBoy);
        System.out.println("Girl: "+ totalOfGirl);
        System.out.println("Total: "+ totalBirth);
    }
    /*
    format filename and get file.
     */
    public static CSVParser getParser(int year){
        String fileName = String.format("yob%sshort.csv",year);
        File file = new File(PATH+"/"+fileName);
        FileResource fr = new FileResource(file);

        return fr.getCSVParser();
    };

    public static int getRank(int year, String name, String gender){
        CSVParser parser = getParser(year);
        int rank = 0;
        for(CSVRecord row : parser){
            String nameInRow = row.get(0);
            if(row.get(1).equals(gender)){
                rank++;
                if(nameInRow.equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }

    public static String getName(int year, int rank, String gender){
        CSVParser parser = getParser(year);
        int index = 0;
        for(CSVRecord row : parser){
            if(row.get(1).equals(gender)){
                index++;
                if(index==rank){
                    return row.get(0);
                }
            }
        }
        return "NO NAME";
    }

    /*
    example:Isabella born in 2012 would be Sophia if she was born in 2014.
     */
    public static void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year,name,gender);
        if(rank!=-1){
            String nameA = getName(year,rank,gender);
            String nameB = getName(newYear,rank,gender);
            if(!nameA.equals("NO NAME") && !nameB.equals("NO NAME") ){
                String output = String.format("%s born in %s would be %s if she was born in %s.",nameA,year,nameB,newYear);
                System.out.println(output);
            }
        }

    }
    public static int getYearByFile(File f){
        String fileName = f.getName();
        int index = fileName.indexOf("yob");
        if(index==-1){
            return -1;
        }
        return Integer.parseInt(fileName.substring(index+3,index+7));
    }

    public static int yearOfHighestRank(String name,String gender){
        DirectoryResource dr = new DirectoryResource();
        int bestRank = Integer.MAX_VALUE;
        for(File f : dr.selectedFiles()){
            int year = getYearByFile(f);
            int rank = getRank(year,name,gender);
            if(rank!=-1 && rank<bestRank){
                bestRank = rank;

            }

        }
        return bestRank==Integer.MAX_VALUE?-1:bestRank;
    }

    public static void testTotalBirths(){
        CSVParser parser = getParser(2012);
        //totalBirths(parser);
    }

    public static void main(String[] args) {
        //testTotalBirths();
//        int rank = getRank(2012,"Mason","M");
//        System.out.println(rank);
//        String name = getName(2012, 99,"F");
//        System.out.println(name);
        //whatIsNameInYear("Isabella",2012,2014,"F");
        int rankH = yearOfHighestRank("Mason","M");
        System.out.println(rankH);
    }
}
