package com.codewithanshuman;

import java.lang.reflect.Array;
import java.util.*;

public class LargestQuakes {

    public int indexOfLargest(ArrayList<QuakeEntry> data){
        double max = 0;
        int index = 0;
        int i =0;
        for (QuakeEntry qe: data){
            if (qe.getMagnitude() > max) {
                max = qe.getMagnitude();
                index = i;
            }
            i++;
        }
        return index;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (int i=0; i<howMany; i++) {
            int dex = indexOfLargest(copy);
            answer.add(copy.get(dex));
            copy.remove(dex);
        }
        return answer;
    }

    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size());
        /* for (QuakeEntry qe : list) {
            System.out.println(qe);
        } */
        //int maxMag = indexOfLargest(list);
        //System.out.println("\nIndex: "+ maxMag+"\n"+list.get(maxMag));
        ArrayList<QuakeEntry> largestList = getLargest(list, 50);
        for (QuakeEntry qe: largestList){
            System.out.println(qe);
        }
    }

}
