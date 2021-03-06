package com.codewithanshuman;

public class MagnitudeFilter implements Filter {
    private double minMag;
    private double maxMag;
    public MagnitudeFilter(double min, double max){
        minMag = min;
        maxMag = max;
    }

    @Override
    public String getName() {
        return "Magnitude ";
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude()>=minMag && qe.getMagnitude()<=maxMag;
    }
}
