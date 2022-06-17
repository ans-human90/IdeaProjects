package com.codewithanshuman;

public class DistanceFilter implements Filter {
    private Location currLoc;
    private double maxDict;
    public DistanceFilter(Location loc , double max){
        currLoc = loc;
        maxDict = max;
    }

    @Override
    public String getName() {
        return "Distance ";
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(currLoc) < maxDict;
    }
}
