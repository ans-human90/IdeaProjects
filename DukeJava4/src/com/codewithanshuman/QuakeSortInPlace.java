package com.codewithanshuman;

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public void sortByMagnitude(ArrayList<QuakeEntry> in) {

        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }

    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){
        int maxIdx = from;
        for (int i = from+1;i<quakes.size();i++){
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()){
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {

        for (int i=0; i< 50; i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }

    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        for (int i=0;i< quakeData.size()-numSorted;i++){
            if (quakeData.get(i).getMagnitude()>quakeData.get(i+1).getMagnitude()){
                QuakeEntry qi = quakeData.get(i);
                QuakeEntry qi2 = quakeData.get(i+1);
                quakeData.set(i,qi2);
                quakeData.set(i+1,qi);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        for (int k=1; k<in.size();k++){
            onePassBubbleSort(in,k);
            //dumpCSV(in);
            //System.out.println("Printing Quakes after pass "+ k);
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quake){
        for (int j=0; j<quake.size()-1;j++){
            if(!(quake.get(j).getMagnitude()<quake.get(j+1).getMagnitude())){
                return false;
            }
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        for (int k=1; k<in.size();k++) {
            onePassBubbleSort(in, k);
            System.out.println("Pass "+ k);
            if (checkInSortedOrder(in)){
                break;
            }
        }
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            System.out.println("Pass "+ (i+1));
            if (checkInSortedOrder(in)){
                break;
            }
        }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);

        System.out.println("read data for "+list.size()+" quakes");
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //dumpCSV(list);
        //System.out.println("Printing Quakes after pass 0");
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        /*System.out.println("EarthQuakes in sorted order: ");
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }*/

    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }

    }
}
