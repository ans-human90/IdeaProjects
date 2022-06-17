package com.codewithanshuman;
import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class BabyNameAssignment {

        public void printNames () {
            FileResource fr = new FileResource();
            for (CSVRecord rec : fr.getCSVParser(false)) {
                int numBorn = Integer.parseInt(rec.get(2));
                if (numBorn <= 100) {
                    System.out.println("Name " + rec.get(0) +
                            " Gender " + rec.get(1) +
                            " Num Born " + rec.get(2));
                }
            }
        }

        public void totalBirths (FileResource fr) {
            int totalBirths = 0;
            int totalBoys = 0;
            int totalGirls = 0;
            for (CSVRecord rec : fr.getCSVParser(false)) {
                int numBorn = Integer.parseInt(rec.get(2));
                totalBirths += numBorn;
                if (rec.get(1).equals("M")) {
                    totalBoys += numBorn;
                }
                else {
                    totalGirls += numBorn;
                }
            }
            System.out.println("total births = " + totalBirths);
            System.out.println("female girls = " + totalGirls);
            System.out.println("male boys = " + totalBoys);
        }

        public void testTotalBirths () {
           // FileResource fr = new FileResource("dataFile/BabyNameTotal/data/yob2014.csv");
            FileResource fr = new FileResource("dataFile/us_babynames_by_year/yob1905.csv");
            totalBirths(fr);
        }

        public int getRank (int year, String name, String gender){
            int rank = -1;
            int boyRank = 0;
            int girlRank = 0;
            FileResource fr = new FileResource("dataFile/us_babynames_test/yob"+year+"short.csv");
            CSVParser parser = fr.getCSVParser(false);
            for ( CSVRecord record : parser ) {
                if (record.get(1).equals("M")) {
                    boyRank++;
                    if(record.get(0).equals(name) && record.get(1).equals(gender)){
                        return rank = boyRank;
                    }
                }
                else{
                    girlRank++;
                    if(record.get(0).equals(name) && record.get(1).equals(gender) ){
                        return rank = girlRank;
                    }
                }
            }
            return rank;
        }

        public void testGetRank(){
            String name = "Mason";
            String gender = "M";
            int year = 2012;
            /*DirectoryResource dr = new DirectoryResource();
            for (File f : dr.selectedFiles()){
                String fName = f.getName();
                String year = fName.substring(3,7);
                int yr = Integer.parseInt(year); */
            int rank = getRank( year , name, gender );
            System.out.println( " Rank: " + rank );
            System.out.println("+++++++++++++++++++++++");
            int nyear = 2012;
            String fname = getName(nyear, rank, gender);
            System.out.println( " Name: " + fname );
        }

        public String getName(int year, int rank, String gender){
            String reqName = "NO NAME";
            int boyRank = 0;
            int girlRank = 0;
            FileResource fr = new FileResource("dataFile/us_babynames_test/yob"+year+"short.csv");
            CSVParser parser = fr.getCSVParser(false);
            for ( CSVRecord record : parser ) {
                if (record.get(1).equals("M")) {
                    boyRank++;
                    if( boyRank == rank && record.get(1).equals(gender)){
                        return reqName = record.get(0);
                    }
                }
                else{
                    girlRank++;
                    if( girlRank == rank && record.get(1).equals(gender) ){
                        return reqName = record.get(0);
                    }
                }
            }
            return reqName;
        }

        public void whatIsNameInYear (String name, Integer year, Integer newYear, String gender){
            int rank = getRank(year, name, gender);
            String fname = getName(newYear,rank,gender);
            String gen = null;
            if(gender == "M"){
                gen = "he";
            }
            else {
                gen = "she";
            }
            System.out.println(name+" born in "+year+" would be "+fname+" if "+gen+" was born in "+newYear+".");
        }

        public void testWhatIsNameInYear (){
            whatIsNameInYear("Isabella", 2012, 2014, "F");
        }

        public int yearOfHighestRank (String name, Integer year){
            CSVRecord highest_so_far = null;

            return 0;
        }

}
