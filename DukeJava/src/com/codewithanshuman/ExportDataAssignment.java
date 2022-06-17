package com.codewithanshuman;

import edu.duke.*;
import org.apache.commons.csv.*;

import java.text.NumberFormat;
import java.util.Locale;

public class ExportDataAssignment {

    public String countryInfo(CSVParser parser,String country){
        for (CSVRecord record : parser){
            String fCountry = record.get("Country");
            if (fCountry.contains(country)){
                String info = record.get("Country") + " : " + record.get("Exports") + " : " + record.get("Value (dollars)");
                return info;
            }
        }
        return "Not Found";
    }

    public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem){
        int countCountries = 0;
        for (CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportItem)){
                countCountries++;
            }
        }
        return countCountries;
    }

    public void bigExporters(CSVParser parser,String amount){
        for (CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            int currValue = Integer.parseInt(value);
            // NumberFormat currValue = NumberFormat.getCurrencyInstance(Locale.US);
            int max = Integer.parseInt(amount);
            if (currValue>max){
                System.out.println( record.get("Country") + " " + record.get("Value (dollars)") );
            }
        }
    }

    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String infoCountry = countryInfo(parser,"Germany");
        System.out.println(infoCountry);
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"cotton", "flowers");
        parser = fr.getCSVParser();
        int noOfCountries = numberOfExporters(parser,"gold");
        System.out.println("Number Of Exporters : " + noOfCountries);
        parser = fr.getCSVParser();
        //bigExporters(parser,"$400,000,000");
    }

}
