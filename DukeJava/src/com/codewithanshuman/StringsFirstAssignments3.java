package com.codewithanshuman;

public class StringsFirstAssignments3 {
    public Boolean twoOccurrences (String stringa, String stringb){
        int startIndex = stringb.indexOf(stringa);
        int nextIndex = stringb.indexOf(stringa,(startIndex+stringa.length()));
        if(startIndex == -1 || nextIndex == -1){
            return false;
        }
        return true;
    }

    public String lastPart(String stringa, String stringb){
        String result = "";
        int startIndex = stringb.indexOf(stringa);
        if (startIndex == -1){
            return stringb;
        }
        result = stringb.substring(startIndex+stringa.length());
        return result;
    }

    public void testOccurreneces(){
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a","banana"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
    }
}
