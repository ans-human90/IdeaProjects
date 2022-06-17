package com.codewithanshuman;

import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String s1 = qe1.getInfo();
        String s2 = qe2.getInfo();
        if (s1.compareTo(s2) > 0){
            return 1;
        }
        else if (s1.compareTo(s2) < 0){
            return -1;
        }
        else if (s1.compareTo(s2) == 0){
            if (qe1.getDepth() < qe2.getDepth()){
                return -1;
            }
            if (qe1.getDepth() > qe2.getDepth()){
                return 1;
            }
        }
        return 0;
    }

}