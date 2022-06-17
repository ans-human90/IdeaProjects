package com.codewithanshuman;

public class Main {

    public static void main(String[] args){
        //QuakeSortInPlace qd =new QuakeSortInPlace();
        //qd.testSort();
        DifferentSorters ds = new DifferentSorters();
        //ds.sortWithCompareTo();
        //ds.sortByTitleAndDepth();
        ds.sortByLastWordInTitleThenByMagnitude();
    }

    /*public static void main(String[] args) {
        //EarthQuakeClient eqc = new EarthQuakeClient();
        //eqc.bigQuakes();
        //eqc.closeToMe();
        //eqc.quakeOfDepth();
        //eqc.quakeByPharse();
        //ClosestQuakes cq = new ClosestQuakes();
        //cq.findClosestQuakes();
        //LargestQuakes lq = new LargestQuakes();
        //lq.findLargestQuakes();

        //EarthQuakeClient2 eq = new EarthQuakeClient2();
        //eq.quakesWithFilter();
        //eq.testMatchAllFilter();
        //eq.testMatchAllFilter2();

        //QuakeSortInPlace qs = new QuakeSortInPlace();
        //qs.testSort();
    }*/
}
