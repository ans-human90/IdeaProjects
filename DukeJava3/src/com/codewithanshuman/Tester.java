package com.codewithanshuman;

import java.util.*;

public class Tester{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/short-test_log");
        la.printAll();
    }

    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are "+ uniqueIPs +" IPs");
    }

    public void testPrintAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog1_log");
        la.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        ArrayList<String> uIP = new ArrayList<String>();
        uIP = la.uniqueIPVisitsOnDay("Sep 24");
        int count =0;
        for (String ip : uIP){
            count++;
            //System.out.println(ip);
        }
        //System.out.println("There are "+ uIP +" IPs");
        System.out.println(count);
    }

    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        int uniqueIPs = la.countUniqueIPsInRange(200,299);
        //System.out.println("There are "+ uniqueIPs +" IPs");
        //int uniqueIPs = la.countUniqueIPsInRange(300,399);
        System.out.println("There are "+ uniqueIPs +" IPs");
        System.out.println("\n------------------\n");
    }

    public void testCounts(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        int max  = la.mostNumberVisitsByIP(counts);
        System.out.println("Most number of visit by IP: "+max);
        System.out.println("\n------------------\n");
        ArrayList<String> maxVisitIPs = la.iPsMostVisits(counts);
        System.out.println(maxVisitIPs);
        HashMap<String,ArrayList<String>> ipsForDays = la.iPsForDays();
        /*for (String s : ipsForDays.keySet()) {
            System.out.println("Date: " +s + "  " + " IPs: " + ipsForDays.get(s));
        }*/
        String maxIpsDays = la.dayWithMostIPVisits(ipsForDays);
        System.out.println("\nDay with most IP visits: "+maxIpsDays);
        System.out.println("\n------------------\n");
        ArrayList<String> iPsWithMostVisitsOnDay = la.iPsWithMostVisitsOnDay(ipsForDays,"Sep 29");
        System.out.println("Most Visits On Day:  "+iPsWithMostVisitsOnDay);
    }

}
