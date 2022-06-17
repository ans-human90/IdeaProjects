package com.codewithanshuman;

public class DepthFilter implements Filter {
    private double minDepth;
    private double maxDepth;
    public DepthFilter(double min , double max){
        minDepth = min;
        maxDepth = max;
    }

    @Override
    public String getName() {
        return "Depth ";
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth()>=minDepth && qe.getDepth()<=maxDepth;
    }
}
