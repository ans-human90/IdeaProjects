package com.codewithanshuman;

public class MinMagFilter implements Filter
{
    private double magMin;

    public MinMagFilter(double min) {
        magMin = min;
    }

    @Override
    public String getName() {
        return "MinMagnitude ";
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= magMin;
    }

}
