package com.codewithanshuman;

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        FileResource resource = new FileResource(filename);
        for (String str : resource.lines() ) {
            LogEntry le = WebLogParser.parseEntry(str);
            records.add(le);
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records){
            String ipAddr = le.getIpAddress();
            if(!uniqueIPs.contains(ipAddr)){
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num){
        for (LogEntry le : records){
            int statusCode = le.getStatusCode();
            if (statusCode >num){
                System.out.println(le);
            }
        }
    }

    public ArrayList uniqueIPVisitsOnDay(String someday){
        ArrayList<String> date = new ArrayList<String>();
        for (LogEntry le : records){
            Date d =new Date();
            d = le.getAccessTime();
            String str = d.toString();
            if (str.contains(someday)){
                String ipAddr = le.getIpAddress();
                if(!date.contains(ipAddr)) {
                    date.add(ipAddr);
                }
            }
        }
        return date;
    }

    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records){
            int statusCode = le.getStatusCode();
            if (statusCode >= low && statusCode <= high){
                String ipAddr = le.getIpAddress();
                if(!uniqueIPs.contains(ipAddr)) {
                    uniqueIPs.add(ipAddr);
                }
            }
        }
        return uniqueIPs.size();
    }

    public HashMap<String,Integer> countVisitsPerIP(){
        HashMap<String,Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le : records){
            String ip = le.getIpAddress();
            if (! counts.containsKey(ip)){
                counts.put(ip,1);
            }
            else {
                counts.put(ip,counts.get(ip)+1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
        int max  = 0;
        for (String s : counts.keySet()){
            int tmp = counts.get(s);
            if (tmp>max){
                max = tmp;
            }
        }
        return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
        ArrayList<String> maxVisitIPs = new ArrayList<String>();
        int max = mostNumberVisitsByIP(counts);
        for (String str : counts.keySet()){
            if (max == counts.get(str)){
                maxVisitIPs.add(str);
            }
        }
        return maxVisitIPs;
    }

    public HashMap<String,ArrayList<String>> iPsForDays(){
        HashMap<String,ArrayList<String>> iPsforDays = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
            ArrayList<String> ips = new ArrayList<String>();
            Date d =new Date();
            d = le.getAccessTime();
            String str = d.toString();
            str = str.substring(4,10);
            if (!iPsforDays.containsKey(str)){
                ips.add(le.getIpAddress());
                iPsforDays.put(str,ips);
            }
            else{
                ips = iPsforDays.get(str);
                ips.add(le.getIpAddress());
                iPsforDays.put(str,ips);
            }
        }
        return iPsforDays;
    }

    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> iPsforDays){
        String maxIpsDays = "";
        int maxi = 0;
        for ( String s : iPsforDays.keySet()){
            int tmp = iPsforDays.get(s).size();
            if(tmp>maxi){
                maxi = tmp;
                maxIpsDays = s;
            }
        }
        return maxIpsDays;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> iPsforDays, String date ){
        ArrayList<String> ips = iPsforDays.get(date);
        HashMap<String,Integer> counts = new HashMap<String, Integer>();
        for (int i=0; i<ips.size();i++){
            String ip = ips.get(i);
            if(! counts.containsKey(ip)){
                counts.put(ip,1);
            }
            else {
                counts.put(ip,counts.get(ip)+1);
            }
        }
        return iPsMostVisits(counts);
    }

}