package com.codewithanshuman;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String s1 = qe1.getInfo();
        String s2 = qe2.getInfo();
        String lastWord1 = s1.substring(s1.lastIndexOf(" ")+1);
        String lastWord2 = s2.substring(s2.lastIndexOf(" ")+1);
        if (lastWord1.compareTo(lastWord2) > 0){
            return 1;
        }
        else if (lastWord1.compareTo(lastWord2) < 0){
            return -1;
        }
        else if (lastWord1.compareTo(lastWord2) == 0){
            if (qe1.getMagnitude() < qe2.getMagnitude()){
                return -1;
            }
            if (qe1.getMagnitude() > qe2.getMagnitude()){
                return 1;
            }
        }
        return 0;
    }

}
